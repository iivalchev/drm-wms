package bg.tusofia.cs.drm.wms.magent.service;

import bg.tusofia.cs.drm.wms.magent.domain.events.*;

/**
 * Created by Ivan on 7/19/2014.
 */
public interface JobService {
    JobResourceEvent requestJobResource(RequestJobResourceEvent event);

    UserJobResourcesEvent requestUserJobResources(RequestUserJobResourcesEvent event);

    JobCreatedEvent createJob(CreateJobEvent event);

    JobRunEvent runJob(RunJobEvent event);
}
