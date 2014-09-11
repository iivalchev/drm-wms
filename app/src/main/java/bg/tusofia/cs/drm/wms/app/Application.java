package bg.tusofia.cs.drm.wms.app;

import bg.tusofia.cs.drm.wms.entities.EntitiesComponetns;
import bg.tusofia.cs.drm.wms.magent.spring.configuration.MagentConfig;
import bg.tusofia.cs.drm.wms.spring.configuration.PersistenceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Ivan on 9/10/2014.
 */
@Configuration
@Import({MagentConfig.class, PersistenceConfig.class})
@EnableAutoConfiguration
@EntityScan(basePackageClasses = {EntitiesComponetns.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
