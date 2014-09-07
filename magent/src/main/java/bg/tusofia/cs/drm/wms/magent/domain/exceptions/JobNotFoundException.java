package bg.tusofia.cs.drm.wms.magent.domain.exceptions;

/**
 * Created by Ivan on 8/30/2014.
 */
public class JobNotFoundException extends RuntimeException {
    private final long jobId;

    public JobNotFoundException(long jobId) {
        this.jobId = jobId;
    }

    public long getJobId() {
        return jobId;
    }
}
