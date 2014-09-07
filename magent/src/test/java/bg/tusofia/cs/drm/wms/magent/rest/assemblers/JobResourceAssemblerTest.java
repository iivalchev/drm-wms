package bg.tusofia.cs.drm.wms.magent.rest.assemblers;

import bg.tusofia.cs.drm.wms.entities.Job;
import bg.tusofia.cs.drm.wms.entities.JobAllocation;
import bg.tusofia.cs.drm.wms.entities.JobInfo;
import bg.tusofia.cs.drm.wms.entities.JobState;
import bg.tusofia.cs.drm.wms.magent.rest.JobQueriesController;
import bg.tusofia.cs.drm.wms.resources.JobResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.testng.annotations.ExpectedExceptions;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.Callable;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by Ivan on 9/2/2014.
 */
public class JobResourceAssemblerTest {

    MockMvc mockMvc;

    JobResourceAssembler underTest = new JobResourceAssembler(JobQueriesController.class, JobResource.class);

    RunInController<JobResource> runInController = new RunInController<JobResource>();

    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(runInController)
                                 .build();
    }

    @Test
    @ExpectedExceptions(NullPointerException.class)
    public void testToResourceForNullEntity() {
        underTest.toResource(null);
    }

    @Test
    public void testToResource() throws Exception {
        setup();

        assertThat(runInController, notNullValue());

        final Holder<JobResource> jobResourceHolder = new Holder<JobResource>();
        final Job job = new Job();
        job.setId(1L);
        job.setUserId("userId");
        job.setName("jobName");
        job.setCommand("/usr/bin/ls");
        job.setInputPath("/home/user/someInfile");
        job.setOutputPath("/home/user/someOutfile");
        job.setErrorPath("/home/user/someErrorfile");
        job.setArgs(asList(new String[]{"arg0", "arg1"}));
        job.setEnvVars(new HashMap<String, String>() {{
            put("evnVarKey", "envVarValue");
        }});
        job.setWorkingDirectory("/home/user");
        job.setStartTime(new Date());
        job.setJobAllocation(new JobAllocation() {{
            setNodes(2);
        }});
        job.setJobInfo(new JobInfo() {{
            setJobState(JobState.RUNNING);
            setHasCoreDump(true);
            setExitStatus(0);
            setTerminationSignal("SIGTERM");
            setResourceUsage(new HashMap<String, String>() {{
                put("cpuTime", "10000");
            }});
        }});

        runInController.callable = new Callable<JobResource>() {
            @Override
            public JobResource call() throws Exception {
                return jobResourceHolder.instance = underTest.toResource(job);
            }
        };

        mockMvc.perform(MockMvcRequestBuilders.get("/"));

        JobResource jobResource = jobResourceHolder.instance;

        assertThat(jobResource, notNullValue());
        assertThat(jobResource.getLink("self")
                              .getHref(), is("http://localhost/job/" + job.getId()));
        assertThat(jobResource.getUserId(), is(job.getUserId()));
        assertThat(jobResource.getName(), is(job.getName()));
        assertThat(jobResource.getCommand(), is(job.getCommand()));
        assertThat(jobResource.getInputPath(), is(job.getInputPath()));
        assertThat(jobResource.getOutputPath(), is(job.getOutputPath()));
        assertThat(jobResource.getErrorPath(), is(job.getErrorPath()));
        assertThat(jobResource.getArgs(), is(job.getArgs()));
        assertThat(jobResource.getEnvVars(), is(job.getEnvVars()));
        assertThat(jobResource.getWorkingDirectory(), is(job.getWorkingDirectory()));
        assertThat(jobResource.getStartTime(), is(job.getStartTime()));
        assertThat(jobResource.getJobAllocation()
                              .getNodes(), is(job.getJobAllocation()
                                                 .getNodes()));

        assertThat(jobResource.getJobInfo()
                              .getJobState()
                              .name(), is(job.getJobInfo()
                                             .getJobState()
                                             .name()));
        assertThat(jobResource.getJobInfo()
                              .isHasCoreDump(), is(job.getJobInfo()
                                                      .isHasCoreDump()));
        assertThat(jobResource.getJobInfo()
                              .getExitStatus(), is(job.getJobInfo()
                                                      .getExitStatus()));
        assertThat(jobResource.getJobInfo()
                              .getTerminationSignal(), is(job.getJobInfo()
                                                             .getTerminationSignal()));
        assertThat(jobResource.getJobInfo()
                              .getResourceUsage(), is(job.getJobInfo()
                                                         .getResourceUsage()));


    }

    @Controller
    @RequestMapping(value = "/")
    public static class RunInController<T> {
        Callable<T> callable;

        @RequestMapping(method = RequestMethod.GET)
        public ResponseEntity<T> run() throws Exception {
            return new ResponseEntity<T>(callable.call(), HttpStatus.OK);
        }
    }

    public static class Holder<T> {
        T instance;
    }
}
