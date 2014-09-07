package bg.tusofia.cs.drm.wms.entities;

/**
 * Created by Ivan on 6/8/2014.
 */
public enum JobState {
    UNDETERMINED,
    QUEUED_ACTIVE,
    SYSTEM_ON_HOLD,
    USER_ON_HOLD,
    USER_SYSTEM_ON_HOLD,
    RUNNING,
    SYSTEM_SUSPENDED,
    USER_SUSPENDED,
    USER_SYSTEM_SUSPENDED,
    DONE,
    FAILED
}
