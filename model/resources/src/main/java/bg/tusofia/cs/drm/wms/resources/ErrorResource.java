package bg.tusofia.cs.drm.wms.resources;

import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Ivan on 8/30/2014.
 */
public class ErrorResource extends ResourceSupport {
    private long jobId;

    private String description;

    public ErrorResource() {
    }

    public ErrorResource(long jobId, String description) {
        this.jobId = jobId;
        this.description = description;
    }

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
