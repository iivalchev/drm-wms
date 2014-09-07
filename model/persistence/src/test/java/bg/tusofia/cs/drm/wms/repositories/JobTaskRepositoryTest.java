package bg.tusofia.cs.drm.wms.repositories;

import bg.tusofia.cs.drm.wms.entities.Job;
import bg.tusofia.cs.drm.wms.entities.JobTask;
import bg.tusofia.cs.drm.wms.entities.TaskState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by Ivan on 6/10/2014.
 */
@ContextConfiguration(classes = TestConfig.class)
public class JobTaskRepositoryTest extends AbstractTransactionalTestNGSpringContextTests {
    @Autowired
    private JobTaskRepository jobTaskRepository;

    @Test
    public void testSaveFindTask() {
        JobTask expectedJobTask = new JobTask();

        expectedJobTask.setTaskState(TaskState.COMPLETED);

        long id = jobTaskRepository.save(expectedJobTask).getId();
        JobTask actualJobTask = jobTaskRepository.findOne(id);

        assertThat(actualJobTask, equalTo(expectedJobTask));
    }
}
