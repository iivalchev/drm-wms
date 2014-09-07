package bg.tusofia.cs.drm.wms.magent.domain.events;

import bg.tusofia.cs.drm.wms.resources.JobResource;

/**
 * Created by Ivan on 7/19/2014.
 */
public class JobResourceEvent {

    private final JobResource jobResource;

    public JobResourceEvent(JobResource jobResource) {
        this.jobResource = jobResource;
    }

    public JobResource getJobResource() {
        return jobResource;
    }

    public boolean isFound() {
        return jobResource != null;
    }
}
