package bg.tusofia.cs.drm.wms.repositories;

import bg.tusofia.cs.drm.wms.entities.Job;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Ivan on 6/10/2014.
 */
public interface JobRepository extends PagingAndSortingRepository<Job, Long> {
}
