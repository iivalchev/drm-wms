package bg.tusofia.cs.drm.wms.magent.rest.assemblers;

import bg.tusofia.cs.drm.wms.resources.JobResource;

/**
 * Created by Ivan on 9/6/2014.
 */
public class ResourceAssemblersFactory {

    private final Class<?> jobControllerClass;

    public ResourceAssemblersFactory(Class<?> jobControllerClass) {
        this.jobControllerClass = jobControllerClass;
    }

    public JobResourceAssembler newJobreJobResourceAssembler() {
        return new JobResourceAssembler(jobControllerClass, JobResource.class);
    }

    public JobResourceAssembler newJobreJobResourceAssembler(Class<?> controllerClass) {
        return new JobResourceAssembler(controllerClass, JobResource.class);
    }
}
