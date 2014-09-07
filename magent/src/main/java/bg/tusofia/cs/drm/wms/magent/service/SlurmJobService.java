package bg.tusofia.cs.drm.wms.magent.service;

import bg.tusofia.cs.drm.wms.builders.JobBuilder;
import bg.tusofia.cs.drm.wms.builders.JobInfoBuilder;
import bg.tusofia.cs.drm.wms.entities.Job;
import bg.tusofia.cs.drm.wms.entities.JobAllocation;
import bg.tusofia.cs.drm.wms.entities.JobState;
import bg.tusofia.cs.drm.wms.magent.domain.events.*;
import bg.tusofia.cs.drm.wms.magent.domain.exceptions.JobNotFoundException;
import bg.tusofia.cs.drm.wms.magent.domain.exceptions.JobServiceException;
import bg.tusofia.cs.drm.wms.magent.rest.assemblers.ResourceAssemblersFactory;
import bg.tusofia.cs.drm.wms.repositories.JobRepository;
import bg.tusofia.cs.drm.wms.resources.JobResource;
import bg.tusofia.cs.drm.wms.resources.JobTaskResource;
import bg.tusofia.cs.drmaa.JobTemplateBuilder;
import org.ggf.drmaa.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Ivan on 8/31/2014.
 */
public class SlurmJobService implements JobService {

    @Autowired
    private DrmaaSessionHolder drmaaSessionHolder;

    @Autowired
    private ResourceAssemblersFactory resourceAssemblerFactory;

    @Autowired
    private JobRepository jobRepository;

    @Override
    public JobResourceEvent requestJobResource(RequestJobResourceEvent event) {
        //ask DRMAA for the result if not already set of the job execution with no waiting timeout
        Session session = drmaaSessionHolder.getSession();
        try {
            Job job = jobRepository.findOne(event.getJobId());
            if (job.getJobInfo() == null) {
                JobInfo jobInfo = session.wait(job.getDrmId(), Session.TIMEOUT_NO_WAIT);
                if (jobInfo != null) {
                    //job has finished execution
                    //update state in db
                    job.setJobInfo(new JobInfoBuilder().status(jobInfo.hasExited() ? JobState.DONE : JobState.FAILED)
                                                       .hasCoreDump(jobInfo.hasCoreDump())
                                                       .exitStatus(jobInfo.hasExited() ? jobInfo.getExitStatus() : -1)
                                                       .terminationSignal(jobInfo.hasSignaled() ? jobInfo.getTerminatingSignal() : "")
                                                       .resourceUsage(jobInfo.getResourceUsage())
                                                       .build());
                    jobRepository.save(job);
                }
            }
        } catch (DrmaaException e) {
            throw new JobServiceException(e);
        }
        return new JobResourceEvent(resourceAssemblerFactory.newJobreJobResourceAssembler()
                                                            .toResource(jobRepository.findOne(event.getJobId())));
    }

    @Override
    public UserJobResourcesEvent requestUserJobResources(RequestUserJobResourcesEvent event) {
        return null;
    }

    @Override
    public JobCreatedEvent createJob(CreateJobEvent event) {
        JobResource jr = event.getJobResource();
        return new JobCreatedEvent(
                resourceAssemblerFactory
                        .newJobreJobResourceAssembler()
                        .toResource(jobRepository.save(
                                new JobBuilder()
                                        .userId(jr.getUserId())
                                        .name(jr.getName())
                                        .command(jr.getCommand())
                                        .inputPath(jr.getInputPath())
                                        .outputPath(jr.getOutputPath())
                                        .errorPath(jr.getErrorPath())
                                        .envVars(jr.getEnvVars())
                                        .workingDirectory(jr.getWorkingDirectory())
                                        .jobAllocation(new JobAllocation(jr.getJobAllocation()
                                                                           .getNodes()))
                                        .build()
                        ))
        );
    }

    @Override
    public JobRunEvent runJob(RunJobEvent event) throws JobNotFoundException {
        Job job = jobRepository.findOne(event.getJobId());
        if (job == null) {
            throw new JobNotFoundException(event.getJobId());
        }
        Session session = drmaaSessionHolder.getSession();
        try {
            JobTemplate jt = new JobTemplateBuilder(session.createJobTemplate())
                    .jobName(job.getName())
                    .remoteCommand(job.getCommand())
                    .inputPath(job.getInputPath())
                    .outputPath(job.getOutputPath())
                    .errorPath(job.getErrorPath())
                    .args(job.getArgs())
                    .jobEnvironment(job.getEnvVars())
                    .workingDirectory(job.getWorkingDirectory())
                    .nativeSpecification(job.getJobAllocation() != null ? String.format("-N %d", job.getJobAllocation()
                                                                                                    .getNodes()) : "")
                    .build();
            job.setDrmId(session.runJob(jt));
        } catch (DrmaaException e) {
            throw new JobServiceException(e);
        }
        jobRepository.save(job);
        //TODO fix task resources
        return new JobRunEvent(new JobTaskResource());
    }

    public static class DrmaaSessionHolder {

        private final SessionFactory drmaaSessionFactory;

        private volatile Session currentSession;

        public DrmaaSessionHolder(SessionFactory drmaaSessionFactory) {
            this.drmaaSessionFactory = drmaaSessionFactory;
        }

        public Session getSession() {
            if (currentSession == null) {
                synchronized (this) {
                    if (currentSession == null) {
                        currentSession = drmaaSessionFactory.getSession();
                        try {
                            currentSession.init("");
                        } catch (DrmaaException e) {
                            throw new JobServiceException(e);
                        }
                    }
                }
            }
            return currentSession;
        }
    }
}
