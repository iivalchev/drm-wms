package bg.tusofia.cs.drm.wms.magent.domain.events;

import bg.tusofia.cs.drm.wms.resources.JobTaskResource;

/**
 * Created by Ivan on 8/23/2014.
 */
public class JobRunEvent {
    private final JobTaskResource taskResource;

    public JobRunEvent(JobTaskResource taskResource) {
        this.taskResource = taskResource;
    }

    public JobTaskResource getTaskResource() {
        return taskResource;
    }
}
