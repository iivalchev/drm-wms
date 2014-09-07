package bg.tusofia.cs.drm.wms.magent.rest;

import bg.tusofia.cs.drm.wms.magent.domain.exceptions.JobNotFoundException;
import bg.tusofia.cs.drm.wms.resources.ErrorResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Ivan on 8/30/2014.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorResource handleJobNotFound(JobNotFoundException e) {
        return new ErrorResource(e.getJobId(), "Job not found");
    }
}
