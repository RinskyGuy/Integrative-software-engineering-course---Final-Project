package iob.Jpas;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import iob.Boundaries.ActivityBoundary;
import iob.Converters.ActivitiesConverter;
import iob.Daos.ActivitiesDao;
import iob.RawInstaces.ComplexId;
import iob.data.ActivityEntity;
import iob.logic.AdvanceInstancesService;
import iob.logic.AdvancedActivitiesService;

@Service
public class JpaActivitiesService implements AdvancedActivitiesService{
	private ActivitiesDao activitiesDao;
	private ActivitiesConverter activitiesConverter;
	private AdvanceInstancesService advanceInstancesService;
	
	private String domain;
	
	@Autowired
	public JpaActivitiesService(ActivitiesDao activitiesDao, ActivitiesConverter activitiesConverter, AdvanceInstancesService advanceInstancesService) {
		this.activitiesDao = activitiesDao;
		this.activitiesConverter = activitiesConverter;
		this.advanceInstancesService = advanceInstancesService;
	}
	
	@Value("${domain:2022b.Hen.Shiryon}")
	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Override
	@Transactional // (readOnly = false)
	public Object invokeActivity(ActivityBoundary activity) {
		
//		if(this.advanceInstancesService.getSpecificInstance(activity.getActivityId().getDomain(), activity.getActivityId().getId()) != null) {
//			if(this.advanceInstancesService.getSpecificInstance(activity.getActivityId().getDomain(), activity.getActivityId().getId()).getActive()) {
//				throw new RuntimeException("Instace is already exists!"); 
//			}
//		}
		
		// TODO: 1. check if instance is in database.
		if(this.advanceInstancesService.getSpecificInstance(activity.getInstance().getInstanceId().getDomain(), activity.getInstance().getInstanceId().getId()) != null) {
				// TODO: 2. check if instance is active.
				if(this.advanceInstancesService.getSpecificInstance(activity.getInstance().getInstanceId().getDomain(), activity.getInstance().getInstanceId().getId()).getActive()) {
					// TODO: 3. check activity type not null.
					if(activity.getType() != null && !activity.getType().isEmpty()) {
						// set UUID by server
						activity.setActivityId(new ComplexId(UUID.randomUUID().toString(), this.domain));
						
						// set timeStamp by server
						activity.setCreatedTimestamp(new Date());
						
						ActivityEntity entity = this.activitiesConverter.boundaryToEntityConvertion(activity);
						entity = this.activitiesDao.save(entity);
						
						return this.activitiesConverter.EntityToBoundaryConvertion(entity);									
					}else { // Type is null or empty string
						throw new RuntimeException("Activity type is not valid");
					}
				}else { // Instance is not active 
					throw new RuntimeException("Activity instance is not active");
				}
		}else { // Instance is not in database
			throw new InstanceNotFoundException("Activity instance could not be found"); 
		}
	}

	@Override
	@Deprecated
	public List<ActivityBoundary> getAllActivities() {
		throw new RuntimeException("deprecated oprtaion, use paginated data retrieval instead");
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ActivityBoundary> getAllActivities(int size, int page) {
		return this.activitiesDao
				.findAll(PageRequest.of(page, size, Direction.ASC, "activityTimestamp", "activityId"))
				.getContent()
				.stream()
				.map(this.activitiesConverter::EntityToBoundaryConvertion)
				.collect(Collectors.toList());
	}
	

	@Override
	@Transactional
	public void deleteAllActivities() {
		this.activitiesDao.deleteAll();
	}

	
}
