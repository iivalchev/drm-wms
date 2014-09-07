package bg.tusofia.cs.drm.wms.magent.domain.events;

import bg.tusofia.cs.drm.wms.resources.JobResource;

import java.util.Collection;

/**
 * Created by Ivan on 7/20/2014.
 */
public class UserJobResourcesEvent {
    private final Collection<JobResource> jobResources;

    public UserJobResourcesEvent(Collection<JobResource> jobResources) {
        this.jobResources = jobResources;
    }

    public Collection<JobResource> getJobResources() {
        return jobResources;
    }

    public boolean isFound() {
        return jobResources != null;
    }
}
