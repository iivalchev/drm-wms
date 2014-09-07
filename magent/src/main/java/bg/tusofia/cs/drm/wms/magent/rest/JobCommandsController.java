package bg.tusofia.cs.drm.wms.magent.rest;

import bg.tusofia.cs.drm.wms.magent.domain.events.CreateJobEvent;
import bg.tusofia.cs.drm.wms.magent.domain.events.RunJobEvent;
import bg.tusofia.cs.drm.wms.resources.JobResource;
import bg.tusofia.cs.drm.wms.resources.JobTaskResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Ivan on 8/4/2014.
 */
@Controller
public class JobCommandsController extends AbstractJobController {
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<JobResource> createJob(@Valid @RequestBody JobResource jobResource) {
        return new ResponseEntity<JobResource>(jobService.createJob(new CreateJobEvent(jobResource))
                                                         .getJobResource(), HttpStatus.OK);
    }

    @RequestMapping(value = "/run/{id}", method = RequestMethod.POST)
    public ResponseEntity<JobTaskResource> runJob(@PathVariable String id) {
        return new ResponseEntity<JobTaskResource>(
                jobService.runJob(new RunJobEvent(Long.valueOf(id)))
                          .getTaskResource(), HttpStatus.ACCEPTED
        );
    }
}
