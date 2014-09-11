package bg.tusofia.cs.drm.wms.magent.rest;

import bg.tusofia.cs.drm.wms.magent.domain.events.CreateJobEvent;
import bg.tusofia.cs.drm.wms.magent.domain.events.JobCreatedEvent;
import bg.tusofia.cs.drm.wms.magent.service.JobService;
import bg.tusofia.cs.drm.wms.resources.JobAllocation;
import bg.tusofia.cs.drm.wms.resources.JobResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Ivan on 8/8/2014.
 */
public class CreateJobIntegrationTest {

    MockMvc mockMvc;

    @InjectMocks
    JobCommandsController controller;

    @Mock
    JobService jobService;

    ObjectMapper om = new ObjectMapper();

    @BeforeTest
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                                 .build();
    }

    @Test
    public void testCreateJobNoRequestBody() throws Exception {
        mockMvc.perform(post("/job")
                .contentType(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(status().isBadRequest());
    }

    //@Test
    public void testCreateJobInvalidResource() throws Exception {
        JobResource jobResource = new JobResource();
        jobResource.setName("jobName");

        System.out.println("SERIALIZED:" + om.writeValueAsString(jobResource));

        mockMvc.perform(post("/job")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(jobResource)))
               .andDo(print())
               .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateJobSuccessfully() throws Exception {
        JobAllocation jobAllocation = new JobAllocation();
        jobAllocation.setNodes(1);

        JobResource jobResource = new JobResource();
        jobResource.setUserId("userId0");
        jobResource.setJobAllocation(jobAllocation);

        when(jobService.createJob(eq(new CreateJobEvent(jobResource)))).thenReturn(new JobCreatedEvent(jobResource));

        System.out.println("SERIALIZED:" + om.writeValueAsString(jobResource));

        mockMvc.perform(post("/job")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(jobResource)))
               .andDo(print())
               .andExpect(jsonPath("$.userId").value("userId0"))
               .andExpect(status().isOk());
    }
}
