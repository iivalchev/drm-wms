package bg.tusofia.cs.drm.wms.magent.domain.events;

/**
 * Created by Ivan on 7/20/2014.
 */
public class RequestUserJobResourcesEvent {

    private final String userId;

    public RequestUserJobResourcesEvent(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
