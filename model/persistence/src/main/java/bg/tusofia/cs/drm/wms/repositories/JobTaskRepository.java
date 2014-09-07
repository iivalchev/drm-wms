package bg.tusofia.cs.drm.wms.repositories;

import bg.tusofia.cs.drm.wms.entities.JobTask;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Ivan on 6/10/2014.
 */
public interface JobTaskRepository extends PagingAndSortingRepository<JobTask, Long> {
}
