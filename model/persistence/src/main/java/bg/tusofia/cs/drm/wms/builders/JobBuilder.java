package bg.tusofia.cs.drm.wms.builders;

import bg.tusofia.cs.drm.wms.entities.Job;
import bg.tusofia.cs.drm.wms.entities.JobAllocation;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Ivan on 6/11/2014.
 */
public class JobBuilder {
    private final Job job = new Job();

    public Job build() {
        return job;
    }

    public JobBuilder userId(String userId){
        job.setUserId(userId);
        return this;
    }

    public JobBuilder id(long id) {
        job.setId(id);
        return this;
    }

    public JobBuilder drmId(String drmId) {
        job.setDrmId(drmId);
        return this;
    }

    public JobBuilder name(String name) {
        job.setName(name);
        return this;
    }

    public JobBuilder command(String command) {
        job.setCommand(command);
        return this;
    }

    public JobBuilder inputPath(String inputPath) {
        job.setInputPath(inputPath);
        return this;
    }

    public JobBuilder outputPath(String outputPath) {
        job.setOutputPath(outputPath);
        return this;
    }

    public JobBuilder errorPath(String errorPath) {
        job.setErrorPath(errorPath);
        return this;
    }

    public JobBuilder args(List<String> args) {
        job.setArgs(args);
        return this;
    }


    public JobBuilder envVars(Map<String, String> envVars) {
        job.setEnvVars(envVars);
        return this;
    }

    public JobBuilder workingDirectory(String workingDirectory) {
        job.setWorkingDirectory(workingDirectory);
        return this;
    }

    public JobBuilder startTime(Date startTime) {
        job.setStartTime(startTime);
        return this;
    }

    public JobBuilder jobAllocation(JobAllocation jobAllocation) {
        job.setJobAllocation(jobAllocation);
        return this;
    }
}
