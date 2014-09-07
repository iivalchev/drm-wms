package bg.tusofia.cs.drmaa;

import org.ggf.drmaa.DrmaaException;
import org.ggf.drmaa.FileTransferMode;
import org.ggf.drmaa.JobTemplate;
import org.ggf.drmaa.PartialTimestamp;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Ivan on 9/6/2014.
 */
public class JobTemplateBuilder {
    private final org.ggf.drmaa.JobTemplate jobTemplate;

    public JobTemplateBuilder(JobTemplate jobTemplate) {
        this.jobTemplate = jobTemplate;
    }

    public JobTemplateBuilder remoteCommand(String remoteCommand) throws DrmaaException {
        jobTemplate.setRemoteCommand(remoteCommand);
        return this;
    }

    public JobTemplateBuilder args(List<String> args) throws DrmaaException {
        jobTemplate.setArgs(args);
        return this;
    }

    public JobTemplateBuilder jobSubmissionState(int state) throws DrmaaException {
        jobTemplate.setJobSubmissionState(state);
        return this;
    }

    public JobTemplateBuilder jobEnvironment(Map<String, String> env) throws DrmaaException {
        jobTemplate.setJobEnvironment(env);
        return this;
    }

    public JobTemplateBuilder workingDirectory(String wd) throws DrmaaException {
        jobTemplate.setWorkingDirectory(wd);
        return this;
    }

    public JobTemplateBuilder jobCategory(String category) throws DrmaaException {
        jobTemplate.setJobCategory(category);
        return this;
    }

    public JobTemplateBuilder nativeSpecification(String spec) throws DrmaaException {
        jobTemplate.setNativeSpecification(spec);
        return this;
    }

    public JobTemplateBuilder email(Set<String> email) throws DrmaaException {
        jobTemplate.setEmail(email);
        return this;
    }

    public JobTemplateBuilder blockEmail(boolean blockEmail) throws DrmaaException {
        jobTemplate.setBlockEmail(blockEmail);
        return this;
    }

    public JobTemplateBuilder startTime(PartialTimestamp startTime) throws DrmaaException {
        jobTemplate.setStartTime(startTime);
        return this;
    }

    public JobTemplateBuilder jobName(String name) throws DrmaaException {
        jobTemplate.setJobName(name);
        return this;
    }

    public JobTemplateBuilder inputPath(String inputPath) throws DrmaaException {
        jobTemplate.setInputPath(inputPath);
        return this;
    }

    public JobTemplateBuilder outputPath(String outputPath) throws DrmaaException {
        jobTemplate.setOutputPath(outputPath);
        return this;
    }

    public JobTemplateBuilder errorPath(String errorPath) throws DrmaaException {
        jobTemplate.setErrorPath(errorPath);
        return this;
    }

    public JobTemplateBuilder joinFiles(boolean join) throws DrmaaException {
        jobTemplate.setJoinFiles(join);
        return this;
    }

    public JobTemplateBuilder transferFiles(FileTransferMode mode) throws DrmaaException {
        jobTemplate.setTransferFiles(mode);
        return this;
    }

    public JobTemplateBuilder deadlineTime(PartialTimestamp deadline) throws DrmaaException {
        jobTemplate.setDeadlineTime(deadline);
        return this;
    }

    public JobTemplateBuilder hardWallclockTimeLimit(long hardWallclockLimit) throws DrmaaException {
        jobTemplate.setHardWallclockTimeLimit(hardWallclockLimit);
        return this;
    }

    public JobTemplateBuilder softWallclockTimeLimit(long softWallclockLimit) throws DrmaaException {
        jobTemplate.setSoftWallclockTimeLimit(softWallclockLimit);
        return this;
    }

    public JobTemplateBuilder hardRunDurationLimit(long hardRunLimit) throws DrmaaException {
        jobTemplate.setHardRunDurationLimit(hardRunLimit);
        return this;
    }

    public JobTemplateBuilder softRunDurationLimit(long softRunLimit) throws DrmaaException {
        jobTemplate.setHardRunDurationLimit(softRunLimit);
        return this;
    }


    public JobTemplate build() {
        return jobTemplate;
    }


}
