package bg.tusofia.cs.drm.wms.resources;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

/**
 * Created by Ivan on 5/21/2014.
 */
public class JobTaskResource extends ResourceSupport {

    public JobTaskResource(){

    }

    public JobTaskResource(long jobId, Class<?> jobControllerClass){
        add(ControllerLinkBuilder.linkTo(jobControllerClass, new Object[0]).slash(jobId).withRel("job"));
    }
}
