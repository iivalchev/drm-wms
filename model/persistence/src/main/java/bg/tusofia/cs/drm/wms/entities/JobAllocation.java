package bg.tusofia.cs.drm.wms.entities;

import javax.persistence.Embeddable;

/**
 * Created by Ivan on 6/8/2014.
 */
@Embeddable
public class JobAllocation {
    private int nodes;

    public JobAllocation() {
    }

    public JobAllocation(int nodes) {
        this.nodes = nodes;
    }

    public int getNodes() {
        return nodes;
    }

    public void setNodes(int nodes) {
        this.nodes = nodes;
    }
}
