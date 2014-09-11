package bg.tusofia.cs.drm.wms.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Ivan on 6/8/2014.
 */
@Entity
public class Job extends BaseEntity {
    @Column(nullable = false)
    private String userId;
    @Column(nullable = true)
    private String drmId;
    @Column(nullable = true)
    private String name;
    @Column(nullable = true)
    private String command;
    @Column(nullable = true)
    private String inputPath;
    @Column(nullable = true)
    private String outputPath;
    @Column(nullable = true)
    private String errorPath;
    @Column(nullable = true)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> args;
    @Column(nullable = true)
    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String, String> envVars;
    @Column(nullable = true)
    private String workingDirectory;
    @Column(nullable = true)
    private Date startTime;
    @Embedded
    private JobAllocation jobAllocation;
    @Embedded
    private JobInfo jobInfo;
    @OneToMany(fetch = FetchType.EAGER)
    private List<JobTask> tasks;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDrmId() {
        return drmId;
    }

    public void setDrmId(String drmId) {
        this.drmId = drmId;
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

    public List<JobTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<JobTask> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Job job = (Job) o;

        if (args != null ? !args.equals(job.args) : job.args != null) return false;
        if (command != null ? !command.equals(job.command) : job.command != null) return false;
        if (drmId != null ? !drmId.equals(job.drmId) : job.drmId != null) return false;
        if (!userId.equals(job.userId)) return false;
        if (envVars != null ? !envVars.equals(job.envVars) : job.envVars != null) return false;
        if (errorPath != null ? !errorPath.equals(job.errorPath) : job.errorPath != null) return false;
        if (inputPath != null ? !inputPath.equals(job.inputPath) : job.inputPath != null) return false;
        if (jobAllocation != null ? !jobAllocation.equals(job.jobAllocation) : job.jobAllocation != null) return false;
        if (jobInfo != null ? !jobInfo.equals(job.jobInfo) : job.jobInfo != null) return false;
        if (name != null ? !name.equals(job.name) : job.name != null) return false;
        if (outputPath != null ? !outputPath.equals(job.outputPath) : job.outputPath != null) return false;
        if (startTime != null ? !startTime.equals(job.startTime) : job.startTime != null) return false;
        if (tasks != null ? !tasks.equals(job.tasks) : job.tasks != null) return false;
        if (workingDirectory != null ? !workingDirectory.equals(job.workingDirectory) : job.workingDirectory != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + userId.hashCode();
        result = 31 * result + (drmId != null ? drmId.hashCode() : 0);
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
        return "Job{" +
                "userId='" + userId + '\'' +
                ", drmId='" + drmId + '\'' +
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
                '}';
    }
}
