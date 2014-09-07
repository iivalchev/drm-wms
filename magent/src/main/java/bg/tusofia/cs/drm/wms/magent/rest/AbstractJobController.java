package bg.tusofia.cs.drm.wms.magent.rest;

import bg.tusofia.cs.drm.wms.magent.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Ivan on 8/4/2014.
 */
@Controller
@RequestMapping("/job")
public class AbstractJobController {
    @Autowired
    protected JobService jobService;
}
