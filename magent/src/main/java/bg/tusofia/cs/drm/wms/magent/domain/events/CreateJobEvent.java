package bg.tusofia.cs.drm.wms.magent.domain.events;

import bg.tusofia.cs.drm.wms.resources.JobResource;

/**
 * Created by Ivan on 8/17/2014.
 */
public class CreateJobEvent {

    private final JobResource jobResource;

    public CreateJobEvent(JobResource jobResource) {
        this.jobResource = jobResource;
    }

    public JobResource getJobResource() {
        return jobResource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreateJobEvent that = (CreateJobEvent) o;

        if (!jobResource.equals(that.jobResource)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return jobResource.hashCode();
    }
}
