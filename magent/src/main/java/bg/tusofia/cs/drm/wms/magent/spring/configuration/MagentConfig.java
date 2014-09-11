package bg.tusofia.cs.drm.wms.magent.spring.configuration;

import bg.tusofia.cs.drm.wms.magent.rest.JobQueriesController;
import bg.tusofia.cs.drm.wms.magent.rest.RestComponents;
import bg.tusofia.cs.drm.wms.magent.rest.assemblers.ResourceAssemblersFactory;
import bg.tusofia.cs.drm.wms.magent.service.JobService;
import bg.tusofia.cs.drm.wms.magent.service.SlurmJobService;
import bg.tusofia.cs.drm.wms.repositories.RepositoryComponents;
import bg.tusofia.cs.drm.wms.spring.configuration.PersistenceConfig;
import org.ggf.drmaa.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

/**
 * Created by Ivan on 9/7/2014.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = {RestComponents.class})
//@Import({PersistenceConfig.class})
public class MagentConfig {

    static {
        System.setProperty("org.ggf.drmaa.SessionFactory", "bg.tusofia.cs.drmaa.SessionFactory");
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
                                            .build();
    }

    @Bean
    public ResourceAssemblersFactory resourceAssemblersFactory() {
        return new ResourceAssemblersFactory(JobQueriesController.class);
    }

    @Bean
    public SlurmJobService.DrmaaSessionHolder drmaaSessionHolder() {
        return new SlurmJobService.DrmaaSessionHolder(SessionFactory.getFactory());
    }

    @Bean
    public JobService jobService() {
        return new SlurmJobService();
    }
}
