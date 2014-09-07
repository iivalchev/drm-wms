package bg.tusofia.cs.drm.wms.magent.domain.events;

/**
 * Created by Ivan on 8/23/2014.
 */
public class RunJobEvent {
    private final long jobId;

    public RunJobEvent(long jobId) {
        this.jobId = jobId;
    }

    public long getJobId() {
        return jobId;
    }
}
