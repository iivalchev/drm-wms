package bg.tusofia.cs.drm.wms.magent.rest.assemblers;

import bg.tusofia.cs.drm.wms.entities.Job;
import bg.tusofia.cs.drm.wms.magent.rest.JobCommandsController;
import bg.tusofia.cs.drm.wms.resources.JobAllocation;
import bg.tusofia.cs.drm.wms.resources.JobInfo;
import bg.tusofia.cs.drm.wms.resources.JobResource;
import bg.tusofia.cs.drm.wms.resources.JobState;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Ivan on 9/1/2014.
 */
public class JobResourceAssembler extends ResourceAssemblerSupport<Job, JobResource> {
    /**
     * Creates a new {@link org.springframework.hateoas.mvc.ResourceAssemblerSupport} using the given controller class and resource type.
     *
     * @param controllerClass must not be {@literal null}.
     * @param resourceType    must not be {@literal null}.
     */
    public JobResourceAssembler(Class<?> controllerClass, Class<JobResource> resourceType) {
        super(controllerClass, resourceType);
    }

    @Override
    public JobResource toResource(Job job) {
        JobResource jobResource = createResourceWithId(job.getId(), job);
        jobResource.setJobId(job.getId());
        jobResource.setUserId(job.getUserId());
        jobResource.setName(job.getName());
        jobResource.setCommand(job.getCommand());
        jobResource.setInputPath(job.getInputPath());
        jobResource.setOutputPath(job.getOutputPath());
        jobResource.setErrorPath(job.getErrorPath());
        jobResource.setArgs(job.getArgs());
        jobResource.setEnvVars(job.getEnvVars());
        jobResource.setWorkingDirectory(job.getWorkingDirectory());
        jobResource.setStartTime(job.getStartTime());

        if (job.getJobAllocation() != null) {
            JobAllocation jobAllocation = new JobAllocation();
            jobAllocation.setNodes(job.getJobAllocation()
                                      .getNodes());
            jobResource.setJobAllocation(jobAllocation);
        }

        if (job.getJobInfo() != null) {
            JobInfo jobInfo = new JobInfo();
            if (job.getJobInfo()
                   .getJobState() != null) {
                jobInfo.setJobState(JobState.valueOf(job.getJobInfo()
                                                        .getJobState()
                                                        .name()));
            }
            Boolean hasCoreDump = job.getJobInfo()
                                     .isHasCoreDump();
            jobInfo.setHasCoreDump(hasCoreDump!=null?hasCoreDump:false);
            Integer exitStatus = job.getJobInfo()
                                    .getExitStatus();
            jobInfo.setExitStatus(exitStatus!=null?exitStatus:0);
            jobInfo.setTerminationSignal(job.getJobInfo()
                                            .getTerminationSignal());
            jobInfo.setResourceUsage(job.getJobInfo()
                                        .getResourceUsage());
            jobResource.setJobInfo(jobInfo);
        }
        jobResource.add(linkTo(JobCommandsController.class).slash("run").slash(job.getId()).withRel("run"));
        return jobResource;
    }
}
