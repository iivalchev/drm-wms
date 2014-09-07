package bg.tusofia.cs.drm.wms.magent.rest;


import bg.tusofia.cs.drm.wms.magent.domain.events.RequestJobResourceEvent;
import bg.tusofia.cs.drm.wms.magent.domain.events.RequestUserJobResourcesEvent;
import bg.tusofia.cs.drm.wms.magent.fixtures.Fixtures;
import bg.tusofia.cs.drm.wms.magent.service.JobService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static bg.tusofia.cs.drm.wms.magent.fixtures.Fixtures.jobResource;
import static bg.tusofia.cs.drm.wms.magent.fixtures.Fixtures.notFound;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Ivan on 6/11/2014.
 */
public class ViewJobIntegrationTest {

    MockMvc mockMvc;

    @InjectMocks
    JobQueriesController controller;

    @Mock
    JobService jobService;

    Long jobId = 1l;

    String userId = "student";

    @BeforeTest
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                                 .build();
    }

    @Test
    public void testViewJobNotFound() throws Exception {
        when(jobService.requestJobResource(any(RequestJobResourceEvent.class)))
                .thenReturn(notFound());

        mockMvc.perform(get("/job/{id}", String.valueOf(jobId))
                .accept(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(status().isNotFound());
    }

    @Test
    public void testViewJob() throws Exception {
        when(jobService.requestJobResource(any(RequestJobResourceEvent.class)))
                .thenReturn(jobResource(jobId));

        mockMvc.perform(get("/job/{id}", String.valueOf(jobId))
                .accept(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(jsonPath("$.jobId").value(Long.valueOf(jobId)
                                                        .intValue()))
               .andExpect(status().isOk());
    }

    @Test
    public void testViewJobForUserNoJobFound() throws Exception {
        when(jobService.requestUserJobResources(any(RequestUserJobResourcesEvent.class)))
                .thenReturn(Fixtures.userJobsNotFound());

        mockMvc.perform(get("/job/user/{userId}", String.valueOf(userId))
                .accept(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(status().isNotFound());
    }

    @Test
    public void testViewJobsForUser() throws Exception {
        Long userJobId0 = 0L;
        Long userJobId1 = 1L;
        when(jobService.requestUserJobResources(any(RequestUserJobResourcesEvent.class)))
                .thenReturn(Fixtures.userJobs(userId, userJobId0, userJobId1));

        mockMvc.perform(get("/job/user/{userId}", String.valueOf(userId))
                .accept(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(jsonPath("$[0].userId").value(userId))
               .andExpect(jsonPath("$[0].jobId").value(Long.valueOf(userJobId0)
                                                           .intValue()))
               .andExpect(jsonPath("$[1].userId").value(userId))
               .andExpect(jsonPath("$[1].jobId").value(Long.valueOf(userJobId1)
                                                           .intValue()))
               .andExpect(status().isOk());
    }
}
