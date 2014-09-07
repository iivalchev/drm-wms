package bg.tusofia.cs.drm.wms.magent.rest;

import bg.tusofia.cs.drm.wms.magent.service.JobService;
import bg.tusofia.cs.drm.wms.magent.stubs.TestStubs;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Ivan on 8/30/2014.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = {RestComponents.class, TestStubs.class})
public class TestWebConfig {

    @Bean
    public JobService jobServiceMock(){
        return Mockito.mock(JobService.class);
    }
}
