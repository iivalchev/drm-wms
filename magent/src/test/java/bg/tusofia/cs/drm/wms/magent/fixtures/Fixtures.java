package bg.tusofia.cs.drm.wms.magent.fixtures;

import bg.tusofia.cs.drm.wms.magent.domain.events.JobResourceEvent;
import bg.tusofia.cs.drm.wms.magent.domain.events.UserJobResourcesEvent;
import bg.tusofia.cs.drm.wms.resources.JobResource;

import java.util.Arrays;

/**
 * Created by Ivan on 7/20/2014.
 */
public class Fixtures {
    public static JobResourceEvent notFound() {
        return new JobResourceEvent(null);
    }

    public static JobResourceEvent jobResource(long jobId) {
        JobResource jobResource = new JobResource();
        jobResource.setJobId(jobId);
        return new JobResourceEvent(jobResource);
    }

    public static UserJobResourcesEvent userJobsNotFound() {
        return new UserJobResourcesEvent(null);
    }

    public static UserJobResourcesEvent userJobs(String userId, Long jobId0, Long jobId1) {
        JobResource jobResource0 = new JobResource();
        jobResource0.setUserId(userId);
        jobResource0.setJobId(jobId0);

        JobResource jobResource1 = new JobResource();
        jobResource1.setUserId(userId);
        jobResource1.setJobId(jobId1);

        return new UserJobResourcesEvent(Arrays.asList(new JobResource[]{jobResource0, jobResource1}));
    }


}
