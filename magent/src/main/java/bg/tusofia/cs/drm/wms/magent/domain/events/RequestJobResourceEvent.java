package bg.tusofia.cs.drm.wms.magent.domain.events;

/**
 * Created by Ivan on 7/19/2014.
 */
public class RequestJobResourceEvent {
    private final Long jobId;

    public RequestJobResourceEvent(Long jobId) {
        this.jobId = jobId;
    }

    public Long getJobId() {
        return jobId;
    }

    public String getJobIdAsString() {
        return String.valueOf(jobId);
    }
}
