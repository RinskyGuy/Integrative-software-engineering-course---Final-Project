package iob.Daos;


import java.util.List;

import org.springframework.data.domain.Pageable;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import iob.data.InstanceEntity;

public interface InstanceDao extends PagingAndSortingRepository<InstanceEntity, String>{
	
	public List<InstanceEntity> findAllByName(
			@Param("name") String name,
			Pageable pageable);
	
	public List<InstanceEntity> findAllByType(
			@Param("type") String type, 
			Pageable pageable);
	
	public List<InstanceEntity> findAllByLatAndLng(
			@Param("lat") float lat, 
			@Param("lng") float lng, 
			Pageable pageable);
	
	public List<InstanceEntity> findAllByActive(
			@Param("active") boolean active,
			Pageable pageable);
	
	public List<InstanceEntity> 
	findAllByLatBetweenAndLngBetween(
		@Param("minLat") float minLat, 
		@Param("maxLat") float maxLat,
		@Param("minLng") float minLng, 
		@Param("maxLng") float maxLng,
		Pageable pageable);
}
