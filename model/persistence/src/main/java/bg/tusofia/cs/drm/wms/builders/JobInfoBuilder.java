package bg.tusofia.cs.drm.wms.builders;

import bg.tusofia.cs.drm.wms.entities.JobInfo;
import bg.tusofia.cs.drm.wms.entities.JobState;

import java.util.Map;

/**
 * Created by Ivan on 9/6/2014.
 */
public class JobInfoBuilder {
    private final JobInfo jobInfo = new JobInfo();

    public JobInfoBuilder status(JobState jobState) {
        jobInfo.setJobState(jobState);
        return this;
    }

    public JobInfoBuilder hasCoreDump(boolean hasCoreDump) {
        jobInfo.setHasCoreDump(hasCoreDump);
        return this;
    }

    public JobInfoBuilder exitStatus(int exitStatus) {
        jobInfo.setExitStatus(exitStatus);
        return this;
    }

    public JobInfoBuilder terminationSignal(String terminationSignal) {
        jobInfo.setTerminationSignal(terminationSignal);
        return this;
    }

    public JobInfoBuilder resourceUsage(Map<String, String> rusage) {
        jobInfo.setResourceUsage(rusage);
        return this;
    }

    public JobInfo build() {
        return jobInfo;
    }
}
