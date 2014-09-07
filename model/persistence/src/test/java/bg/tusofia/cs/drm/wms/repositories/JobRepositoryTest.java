package bg.tusofia.cs.drm.wms.repositories;

import bg.tusofia.cs.drm.wms.entities.Job;
import bg.tusofia.cs.drm.wms.entities.JobAllocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by Ivan on 6/10/2014.
 */
@ContextConfiguration(classes = TestConfig.class)
public class JobRepositoryTest extends AbstractTransactionalTestNGSpringContextTests {
    @Autowired
    private JobRepository jobRepository;

    @Test
    public void testSaveFindJob() {
        Map<String, String> env = new HashMap<String, String>();
        env.put("key", "value");

        JobAllocation jobAllocation = new JobAllocation();
        jobAllocation.setNodes(2);

        Job expectedJob = new Job();
        expectedJob.setUserId("student@tusofia.bg");
        expectedJob.setDrmId("drmId");
        expectedJob.setName("jobName");
        expectedJob.setCommand("command");
        expectedJob.setInputPath("input");
        expectedJob.setOutputPath("output");
        expectedJob.setErrorPath("error");
        expectedJob.setArgs(Arrays.asList(new String[]{"arg9"}));
        expectedJob.setEnvVars(env);
        expectedJob.setWorkingDirectory("workDir");
        expectedJob.setStartTime(new Date());
        expectedJob.setJobAllocation(jobAllocation);

        long id = jobRepository.save(expectedJob).getId();
        Job actualJob = jobRepository.findOne(id);

        assertThat(actualJob, equalTo(expectedJob));
    }
}
