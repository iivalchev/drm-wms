package bg.tusofia.cs.drm.wms.entities;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.Map;

/**
 * Created by Ivan on 6/8/2014.
 */
@Embeddable
public class JobInfo {
    @Column(nullable = true)
    private JobState jobState;
    @Column(nullable = true)
    private boolean hasCoreDump;
    @Column(nullable = true)
    private int exitStatus;
    @Column(nullable = true)
    private String terminationSignal;
    @Column(nullable = true)
    @ElementCollection
    private Map<String, String> resourceUsage;

    public JobState getJobState() {
        return jobState;
    }

    public void setJobState(JobState jobState) {
        this.jobState = jobState;
    }

    public boolean isHasCoreDump() {
        return hasCoreDump;
    }

    public void setHasCoreDump(boolean hasCoreDump) {
        this.hasCoreDump = hasCoreDump;
    }

    public int getExitStatus() {
        return exitStatus;
    }

    public void setExitStatus(int exitStatus) {
        this.exitStatus = exitStatus;
    }

    public String getTerminationSignal() {
        return terminationSignal;
    }

    public void setTerminationSignal(String terminationSignal) {
        this.terminationSignal = terminationSignal;
    }

    public Map<String, String> getResourceUsage() {
        return resourceUsage;
    }

    public void setResourceUsage(Map<String, String> resourceUsage) {
        this.resourceUsage = resourceUsage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobInfo jobInfo = (JobInfo) o;

        if (exitStatus != jobInfo.exitStatus) return false;
        if (hasCoreDump != jobInfo.hasCoreDump) return false;
        if (jobState != jobInfo.jobState) return false;
        if (resourceUsage != null ? !resourceUsage.equals(jobInfo.resourceUsage) : jobInfo.resourceUsage != null)
            return false;
        if (terminationSignal != null ? !terminationSignal.equals(jobInfo.terminationSignal) : jobInfo.terminationSignal != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = jobState != null ? jobState.hashCode() : 0;
        result = 31 * result + (hasCoreDump ? 1 : 0);
        result = 31 * result + exitStatus;
        result = 31 * result + (terminationSignal != null ? terminationSignal.hashCode() : 0);
        result = 31 * result + (resourceUsage != null ? resourceUsage.hashCode() : 0);
        return result;
    }
}
