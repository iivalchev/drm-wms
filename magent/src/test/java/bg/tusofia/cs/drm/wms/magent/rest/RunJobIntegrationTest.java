package bg.tusofia.cs.drm.wms.magent.rest;

import bg.tusofia.cs.drm.wms.magent.domain.events.JobRunEvent;
import bg.tusofia.cs.drm.wms.magent.domain.events.RunJobEvent;
import bg.tusofia.cs.drm.wms.magent.domain.exceptions.JobNotFoundException;
import bg.tusofia.cs.drm.wms.magent.service.JobService;
import bg.tusofia.cs.drm.wms.resources.JobTaskResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Ivan on 8/23/2014.
 */
@WebAppConfiguration
@ContextConfiguration(classes = {TestWebConfig.class})
public class RunJobIntegrationTest extends AbstractTestNGSpringContextTests {

    final long jobId = 1L;

    MockMvc mockMvc;

    @Autowired
    JobService jobService;

    @Autowired
    WebApplicationContext wac;

    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                                 .build();
    }

    @Test
    public void testRunJobForNotExistentJob() throws Exception {
        setup();

        when(jobService.runJob(any(RunJobEvent.class))).thenThrow(new JobNotFoundException(jobId));
        mockMvc.perform(post("/job/run/{jobId}", String.valueOf(jobId))
                .accept(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(jsonPath("$.jobId").value((int) jobId))
               .andExpect(jsonPath("$.description").value(not(isEmptyOrNullString())))
               .andExpect(status().isNotFound());
    }

    @Test
    public void testRunJobForExistentJob() throws Exception {
        setup();

        when(jobService.runJob(any(RunJobEvent.class))).thenReturn(new JobRunEvent(new JobTaskResource()));
        mockMvc.perform(post("/job/run/{jobId}", String.valueOf(jobId))
                .accept(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(jsonPath("$").value(notNullValue()))
               .andExpect(status().isAccepted());
    }

    //@Test
    //TODO fix this case
    public void testRunJobForInvalidJobId() throws Exception {
        setup();

        when(jobService.runJob(any(RunJobEvent.class))).thenReturn(new JobRunEvent(new JobTaskResource()));
        mockMvc.perform(post("/job/run/{jobId}", "invalidId")
                .accept(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(jsonPath("$").value(notNullValue()))
               .andExpect(status().isAccepted());
    }
}
