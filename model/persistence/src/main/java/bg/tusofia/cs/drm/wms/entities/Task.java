package bg.tusofia.cs.drm.wms.entities;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

/**
 * Created by Ivan on 5/20/2014.
 */
@MappedSuperclass
public class Task extends BaseEntity {
    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private TaskState taskState;

    public TaskState getTaskState() {
        return taskState;
    }

    public void setTaskState(TaskState taskState) {
        this.taskState = taskState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Task task = (Task) o;

        if (taskState != task.taskState) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (taskState != null ? taskState.hashCode() : 0);
        return result;
    }
}
