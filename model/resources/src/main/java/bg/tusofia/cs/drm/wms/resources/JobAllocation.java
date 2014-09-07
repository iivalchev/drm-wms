package bg.tusofia.cs.drm.wms.resources;

import javax.validation.constraints.Min;

/**
 * Created by Ivan on 6/8/2014.
 */
public class JobAllocation {
    @Min(1)
    private int nodes;

    public int getNodes() {
        return nodes;
    }

    public void setNodes(int nodes) {
        this.nodes = nodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobAllocation that = (JobAllocation) o;

        if (nodes != that.nodes) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return nodes;
    }
}
