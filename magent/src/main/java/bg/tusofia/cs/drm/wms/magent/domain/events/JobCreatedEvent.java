package bg.tusofia.cs.drm.wms.magent.domain.events;

import bg.tusofia.cs.drm.wms.resources.JobResource;

/**
 * Created by Ivan on 8/17/2014.
 */
public class JobCreatedEvent {
    private final JobResource jobResource;

    public JobCreatedEvent(JobResource jobResource) {
        this.jobResource = jobResource;
    }

    public JobResource getJobResource() {
        return jobResource;
    }
}
