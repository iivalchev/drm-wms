package bg.tusofia.cs.drm.wms.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Ivan on 6/8/2014.
 */
@Entity
public class JobTask extends Task {
    @ManyToOne
    private Job job;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
