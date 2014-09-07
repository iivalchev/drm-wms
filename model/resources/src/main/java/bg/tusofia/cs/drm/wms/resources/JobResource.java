package bg.tusofia.cs.drm.wms.resources;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Ivan on 6/8/2014.
 */
public class JobResource extends ResourceSupport {
    @NotEmpty
    private String userId;
    private long jobId;
    private String name;
    private String command;
    private String inputPath;
    private String outputPath;
    private String errorPath;
    private List<String> args;
    private Map<String, String> envVars;
    private String workingDirectory;
    private Date startTime;
    @Valid
    @NotNull
    private JobAllocation jobAllocation;
    private JobInfo jobInfo;
    private List<JobTaskResource> tasks;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getInputPath() {
        return inputPath;
    }

    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public String getErrorPath() {
        return errorPath;
    }

    public void setErrorPath(String errorPath) {
        this.errorPath = errorPath;
    }

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }

    public Map<String, String> getEnvVars() {
        return envVars;
    }

    public void setEnvVars(Map<String, String> envVars) {
        this.envVars = envVars;
    }

    public String getWorkingDirectory() {
        return workingDirectory;
    }

    public void setWorkingDirectory(String workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public JobAllocation getJobAllocation() {
        return jobAllocation;
    }

    public void setJobAllocation(JobAllocation jobAllocation) {
        this.jobAllocation = jobAllocation;
    }

    public JobInfo getJobInfo() {
        return jobInfo;
    }

    public void setJobInfo(JobInfo jobInfo) {
        this.jobInfo = jobInfo;
    }

    public List<JobTaskResource> getTasks() {
        return tasks;
    }

    public void setTasks(List<JobTaskResource> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        JobResource that = (JobResource) o;

        if (jobId != that.jobId) return false;
        if (args != null ? !args.equals(that.args) : that.args != null) return false;
        if (command != null ? !command.equals(that.command) : that.command != null) return false;
        if (envVars != null ? !envVars.equals(that.envVars) : that.envVars != null) return false;
        if (errorPath != null ? !errorPath.equals(that.errorPath) : that.errorPath != null) return false;
        if (inputPath != null ? !inputPath.equals(that.inputPath) : that.inputPath != null) return false;
        if (jobAllocation != null ? !jobAllocation.equals(that.jobAllocation) : that.jobAllocation != null)
            return false;
        if (jobInfo != null ? !jobInfo.equals(that.jobInfo) : that.jobInfo != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (outputPath != null ? !outputPath.equals(that.outputPath) : that.outputPath != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (tasks != null ? !tasks.equals(that.tasks) : that.tasks != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (workingDirectory != null ? !workingDirectory.equals(that.workingDirectory) : that.workingDirectory != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (int) (jobId ^ (jobId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (command != null ? command.hashCode() : 0);
        result = 31 * result + (inputPath != null ? inputPath.hashCode() : 0);
        result = 31 * result + (outputPath != null ? outputPath.hashCode() : 0);
        result = 31 * result + (errorPath != null ? errorPath.hashCode() : 0);
        result = 31 * result + (args != null ? args.hashCode() : 0);
        result = 31 * result + (envVars != null ? envVars.hashCode() : 0);
        result = 31 * result + (workingDirectory != null ? workingDirectory.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (jobAllocation != null ? jobAllocation.hashCode() : 0);
        result = 31 * result + (jobInfo != null ? jobInfo.hashCode() : 0);
        result = 31 * result + (tasks != null ? tasks.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "JobResource{" +
                "userId='" + userId + '\'' +
                ", jobId=" + jobId +
                ", name='" + name + '\'' +
                ", command='" + command + '\'' +
                ", inputPath='" + inputPath + '\'' +
                ", outputPath='" + outputPath + '\'' +
                ", errorPath='" + errorPath + '\'' +
                ", args=" + args +
                ", envVars=" + envVars +
                ", workingDirectory='" + workingDirectory + '\'' +
                ", startTime=" + startTime +
                ", jobAllocation=" + jobAllocation +
                ", jobInfo=" + jobInfo +
                ", tasks=" + tasks +
                "} " + super.toString();
    }
}
