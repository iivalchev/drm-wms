package bg.tusofia.cs.drm.wms.magent.domain.exceptions;

/**
 * Created by Ivan on 9/6/2014.
 */
public class JobServiceException extends RuntimeException {

    public JobServiceException() {
        super();
    }

    public JobServiceException(String message) {
        super(message);
    }

    public JobServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public JobServiceException(Throwable cause) {
        super(cause);
    }

}
