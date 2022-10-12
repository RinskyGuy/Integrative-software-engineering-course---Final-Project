package iob.Daos;

import java.util.List;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import iob.data.ActivityEntity;

public interface ActivitiesDao extends 
	PagingAndSortingRepository<ActivityEntity, String>{
	
	public List<ActivityEntity> findAllByActivityId(
			@Param("pattern") String pattern, 
			Pageable pageable);
}
