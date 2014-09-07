package bg.tusofia.cs.drm.wms.magent.service;

import bg.tusofia.cs.drm.wms.magent.domain.events.JobResourceEvent;
import bg.tusofia.cs.drm.wms.magent.domain.events.RequestJobResourceEvent;
import bg.tusofia.cs.drm.wms.magent.rest.assemblers.ResourceAssemblersFactory;
import bg.tusofia.cs.drm.wms.resources.JobResource;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by Ivan on 9/1/2014.
 */
public class DrmaaJobServiceTest {

//    DrmaaJobService underTest = new DrmaaJobService(new ResourceAssemblersFactory());

    long jobId = 1L;

    @Test
    public void testGetJobResourceJobNotFound() {
        //setup
        //execute tests
//        JobResourceEvent jobResourceEvent = underTest.requestJobResource(new RequestJobResourceEvent(jobId));
        // verify results
//        assertThat(jobResourceEvent, notNullValue());
//
//        JobResource jobResource = jobResourceEvent.getJobResource();
//        assertThat(jobResource, notNullValue());

//        assertThat(jobResource.getJobId(), equalTo(jobId));
    }
}
