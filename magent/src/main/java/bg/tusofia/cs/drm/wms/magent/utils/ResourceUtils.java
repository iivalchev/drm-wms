package bg.tusofia.cs.drm.wms.magent.utils;

import bg.tusofia.cs.drm.wms.builders.JobBuilder;
import bg.tusofia.cs.drm.wms.entities.Job;
import bg.tusofia.cs.drm.wms.entities.JobAllocation;
import bg.tusofia.cs.drm.wms.resources.JobResource;

/**
 * Created by Ivan on 6/11/2014.
 */
public class ResourceUtils {
    public static Job fromResource(JobResource jr) {
        return new JobBuilder().id(jr.getJobId()).
                name(jr.getName()).
                command(jr.getCommand()).
                inputPath(jr.getInputPath()).
                outputPath(jr.getOutputPath()).
                errorPath(jr.getErrorPath()).
                args(jr.getArgs()).envVars(jr.getEnvVars()).
                workingDirectory(jr.getWorkingDirectory()).
                startTime(jr.getStartTime()).
                jobAllocation(fromResource(jr.getJobAllocation())).
                build();
    }

    public static JobAllocation fromResource(bg.tusofia.cs.drm.wms.resources.JobAllocation ja) {
        JobAllocation jobAllocation = new JobAllocation();
        jobAllocation.setNodes(ja.getNodes());
        return jobAllocation;
    }

}
