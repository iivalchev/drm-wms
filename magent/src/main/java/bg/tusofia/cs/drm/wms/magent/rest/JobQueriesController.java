package bg.tusofia.cs.drm.wms.magent.rest;

import bg.tusofia.cs.drm.wms.magent.domain.events.JobResourceEvent;
import bg.tusofia.cs.drm.wms.magent.domain.events.RequestJobResourceEvent;
import bg.tusofia.cs.drm.wms.magent.domain.events.RequestUserJobResourcesEvent;
import bg.tusofia.cs.drm.wms.magent.domain.events.UserJobResourcesEvent;
import bg.tusofia.cs.drm.wms.magent.service.JobService;
import bg.tusofia.cs.drm.wms.resources.JobResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

/**
 * Created by Ivan on 6/17/2014.
 */
@Controller
public class JobQueriesController extends AbstractJobController {

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<JobResource> viewJob(@PathVariable String id) {
        JobResourceEvent jobResourceEvent = jobService.requestJobResource(new RequestJobResourceEvent(Long.valueOf(id)));
        ResponseEntity<JobResource> response;
        if (!jobResourceEvent.isFound()) {
            response = new ResponseEntity<JobResource>(HttpStatus.NOT_FOUND);
        } else {
            response = new ResponseEntity<JobResource>(jobResourceEvent.getJobResource(), HttpStatus.OK);
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{userId}")
    public ResponseEntity<Collection<JobResource>> viewUserJobs(@PathVariable String userId) {
        UserJobResourcesEvent userJobResourcesEvent = jobService.requestUserJobResources(new RequestUserJobResourcesEvent(userId));
        ResponseEntity<Collection<JobResource>> response;
        if (!userJobResourcesEvent.isFound()) {
            response = new ResponseEntity<Collection<JobResource>>(HttpStatus.NOT_FOUND);
        } else {
            response = new ResponseEntity<Collection<JobResource>>(userJobResourcesEvent.getJobResources(), HttpStatus.OK);
        }
        return response;
    }
}
