package bg.tusofia.cs.drm.wms.magent.service;

import bg.tusofia.cs.drm.wms.magent.spring.configuration.MagentConfig;
import bg.tusofia.cs.drm.wms.resources.JobAllocation;
import bg.tusofia.cs.drm.wms.resources.JobResource;
import bg.tusofia.cs.drm.wms.spring.configuration.PersistenceConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.Test;

import javax.management.StringValueExp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Ivan on 9/10/2014.
 */
@WebAppConfiguration
@ContextConfiguration(classes = {MagentConfig.class, PersistenceConfig.class})
public class SlurmJobServiceTest extends AbstractTransactionalTestNGSpringContextTests {
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext wac;

    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                                 .build();
    }

    //@Test
    public void testCreateJob() throws Exception {
        setup();

        JobResource jr = new JobResource();
        jr.setUserId("someUser");
        jr.setJobAllocation(new JobAllocation() {{
            setNodes(1);
        }});
        jr.setCommand("/usr/bin/ls");

        ObjectMapper om = new ObjectMapper();
        String jrInJson = om.writeValueAsString(jr);

        System.out.println("JOBResource: " + jrInJson);

        MvcResult result = mockMvc.perform(post("/job")
                .content(jrInJson)
                .contentType(MediaType.APPLICATION_JSON))
                                  .andDo(print())
                                  .andExpect(status().isOk())
                                  .andReturn();

        result = mockMvc.perform(get("/job/{id}", String.valueOf(1))
                .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn();

        System.out.println("debug");
    }
}
