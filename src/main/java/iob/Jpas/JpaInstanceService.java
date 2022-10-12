package iob.Jpas;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import iob.Boundaries.InstanceBoundary;
import iob.Converters.InstanceConverter;
import iob.Daos.InstanceDao;
import iob.RawInstaces.ComplexId;
import iob.data.InstanceEntity;
import iob.data.UserRole;
import iob.logic.AdvanceInstancesService;
import iob.logic.AdvancedUsersService;

@Service
public class JpaInstanceService implements AdvanceInstancesService{
	private InstanceDao instanceDao;
	private InstanceConverter instanceConverter;
	private AdvancedUsersService usersService;
	
	private String domain;
	
	@Autowired
	public JpaInstanceService(InstanceDao instanceDao, InstanceConverter instanceConverter, AdvancedUsersService usersService) {
		this.instanceDao = instanceDao;
		this.instanceConverter = instanceConverter;
		this.usersService = usersService;
	}
	
	@Value("${domain:2022b.Hen.Shiryon}")
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	@Override
	@Transactional
	public InstanceBoundary createInstance(InstanceBoundary instance) {
		if(instance.getName() != null && !instance.getName().isEmpty()) {
			if(instance.getType() != null && !instance.getType().isEmpty()) {
				// Set UUID by server
				instance.setInstanceId(new ComplexId(UUID.randomUUID().toString(), this.domain));
				// Set timeStamp by server
				instance.setCreatedTimestamp(new Date());
				
				InstanceEntity instanceEntity = this.instanceConverter.boundaryToEntityConvertion(instance);
				instanceEntity = this.instanceDao.save(instanceEntity);
				
				return this.instanceConverter.entityToBoundaryConvertion(instanceEntity);				
			}else { // Type is null or empty string
				throw new RuntimeException("Instance type is not valid");
			}
		}else { // Name is null or empty string
			throw new RuntimeException("Instance name is not valid");
		}
	}
	
	@Override
	@Transactional //(readOnly = false)
	public InstanceBoundary updateInstance(String instanceDomain, String instanceId, InstanceBoundary update) {
		InstanceEntity entity = this.instanceConverter.boundaryToEntityConvertion(getSpecificInstance(instanceDomain, instanceId));
		if(update.getActive() != null)
			entity.setActive(update.getActive());
		
		if(update.getCreatedTimestamp() != null)
			// do nothing
			
		if(update.getInstanceAttributes() != null) {
			entity.setInstanceAttributes(this.instanceConverter.toEntity(update.getInstanceAttributes()));			
		}
		
		if(update.getInstanceId() != null)
			// do nothing
		
		if(update.getLocation() != null) {
			if(update.getLocation().getLat() != null) {
				entity.setLat(update.getLocation().getLat());				
			}
			if(update.getLocation().getLng() != null) {
				entity.setLng(update.getLocation().getLng());
			}
		}
		
		if(update.getName() != null) 
			entity.setName(update.getName());
		
		if(update.getType() != null)
			entity.setType(update.getType());
		
		entity = this.instanceDao.save(entity);
		
		return this.instanceConverter.entityToBoundaryConvertion(entity);
	}
	
	@Override
	@Transactional(readOnly = true) // can be read together
	public InstanceBoundary getSpecificInstance(String instanceDomain, String instanceId) {
		Optional<InstanceEntity> entityOp = this.instanceDao.findById(instanceDomain + "@@" + instanceId);
		if(entityOp.isPresent()) {
			InstanceEntity entity = entityOp.get();
			return this.instanceConverter.entityToBoundaryConvertion(entity);
		}else {
			throw new InstanceNotFoundException("No such instance, with id: " + instanceDomain + "@@" + instanceId);
		}
	}
	
	

	@Override
	@Deprecated
	public List<InstanceBoundary> getAllInstances() {
		throw new RuntimeException("deprecated operation, use paginated data retrieval instead");
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<InstanceBoundary> getAllInstances(int page, int size) {
		return StreamSupport
				.stream(
						this.instanceDao
						.findAll(PageRequest.of(page, size, Direction.ASC, "instanceTimestamp", "instanceId")).spliterator(),
						false)  				   		  					 // Stream<InstanceEntity>
				.map(this.instanceConverter::entityToBoundaryConvertion)    // Stream<InstanceBoundary>
				.collect(Collectors.toList()); 							   	 // List<InstanceBoundary>
	}

	@Override
	@Transactional
	public void deleteAllInstances() {
		this.instanceDao.deleteAll();
	}
	
	public boolean checkRole(String id, UserRole expectedRole) {
		return this.usersService.checkRole(id, expectedRole);
	}


	@Override
	@Transactional(readOnly = true)
	public List<InstanceBoundary> getAllByNameAttribute(String name, int size, int page) {
		return this.instanceDao
				.findAllByName(name, PageRequest.of(page, size, Direction.DESC, "instanceTimestamp", "instanceId"))
				.stream()
				.map(this.instanceConverter::entityToBoundaryConvertion)
				.collect(Collectors.toList());
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<InstanceBoundary> getAllByNameAttributeAndActive(String name, int size, int page) {
		return this.instanceDao
				.findAllByName(name, PageRequest.of(page, size, Direction.DESC, "instanceTimestamp", "instanceId"))
				.stream()
				.map(this.instanceConverter::entityToBoundaryConvertion)
				.filter(obj-> obj.getActive())
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public List<InstanceBoundary> getAllByTypeAttribute(String type, int size, int page) {
		return this.instanceDao
				.findAllByType(type, PageRequest.of(page, size, Direction.DESC, "instanceTimestamp", "instanceId"))
				.stream()
				.map(this.instanceConverter::entityToBoundaryConvertion)
				.collect(Collectors.toList());
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<InstanceBoundary> getAllByTypeAttributeAndActive(String type, int size, int page) {
		return this.instanceDao
				.findAllByType(type, PageRequest.of(page, size, Direction.DESC, "instanceTimestamp", "instanceId"))
				.stream()
				.map(this.instanceConverter::entityToBoundaryConvertion)
				.filter(obj-> obj.getActive())
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public List<InstanceBoundary> getAllByLatAndLngAttributes(float lat, float lng, float distance, int size, int page) {
		float tempDistance = Math.abs(distance);
		return this.instanceDao
				.findAllByLatBetweenAndLngBetween(lat - tempDistance, lat + tempDistance, lng - tempDistance, lng + tempDistance ,PageRequest.of(page, size, Direction.DESC, "instanceTimestamp", "instanceId"))
				.stream()
				.map(this.instanceConverter::entityToBoundaryConvertion)
				.collect(Collectors.toList());
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<InstanceBoundary> getAllByLatAndLngAttributesAndActive(float lat, float lng, float distance, int size, int page) {
		float tempDistance = Math.abs(distance);
		return this.instanceDao
				.findAllByLatBetweenAndLngBetween(lat - tempDistance, lat + tempDistance, lng - tempDistance, lng + tempDistance ,PageRequest.of(page, size, Direction.DESC, "instanceTimestamp", "instanceId"))
				.stream()
				.map(this.instanceConverter::entityToBoundaryConvertion)
				.filter(obj-> obj.getActive())
				.collect(Collectors.toList());
	}
	
	@Override
	public boolean latLngEligibility(InstanceEntity obj, float lat, float lng, float distance) {
		float minLat,maxLat,minLng,maxLng, checkLat, checkLng;
		checkLat = obj.getLat();
		checkLng = obj.getLng();
		System.out.println(checkLat);
		System.out.println(checkLng);
		minLat = lat - distance;
		maxLat = lat + distance;
		minLng = lng - distance;
		maxLng = lng + distance;
		if(checkLat>= minLat &&  checkLat<= maxLat && checkLng >= minLng && checkLng <= maxLng) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * This method check eligibility of the user and execute accordingly
	 * MANAGER - return boundary
	 * PLAYER - return boundary ONLY if instance is active
	 * otherwise, return an empty boundary 
	 */
	@Override
	public InstanceBoundary checkEligibilityAndExecuteSpecific(String instanceDomain, String instanceId, String id, int page, int size) {
		if(this.checkRole(id, UserRole.MANAGER)) {
			InstanceBoundary boundary = getSpecificInstance(instanceDomain, instanceId);
			return boundary;
		}else if(this.checkRole(id, UserRole.PLAYER)){
//			if(boundary.getActive()) {
			List<InstanceBoundary> boundaryLst = getAllInstancesByActive(page, size);
//			if(boundaryLst.size() > 0) {
			return boundaryLst.toArray(new InstanceBoundary[0])[0];
//			}else {
//				throw new EligibilityException("You don't have authority to execute the function");
//			}
		}else {
			throw new EligibilityException("You don't have authority to execute the function");
		}
	}
	
	/**
	 * This method check eligibility of the user and execute accordingly
	 * MANAGER - return boundary
	 * PLAYER - return boundary ONLY if instance is active
	 * otherwise, return an empty boundary 
	 */
	@Override
	public InstanceBoundary[] checkEligibilityAndExecuteAll(String id, int page, int size) {
		if(this.checkRole(id, UserRole.MANAGER)) {
			List<InstanceBoundary> boundaryLst = getAllInstances(page, size);
			return boundaryLst.toArray(new InstanceBoundary[0]);
		}else if(this.checkRole(id, UserRole.PLAYER)){
			List<InstanceBoundary> boundaryLst = getAllInstancesByActive(page, size);
			return boundaryLst.toArray(new InstanceBoundary[0]);								
		}else {
			throw new EligibilityException("You don't have authority to execute the function");
		}
	}
	
	@Override
	public InstanceBoundary[] checkEligibilityAndExecuteAllByName(String name, String id, int page, int size) {
		if(this.checkRole(id, UserRole.MANAGER)) {
			List<InstanceBoundary> boundaryLst = getAllByNameAttribute(name, size, page);
			return boundaryLst.toArray(new InstanceBoundary[0]);
		}else if(this.checkRole(id, UserRole.PLAYER)){
			List<InstanceBoundary> boundaryLst = getAllByNameAttributeAndActive(name, size, page);
			return boundaryLst.toArray(new InstanceBoundary[0]);								
		}else {
			throw new EligibilityException("You don't have authority to execute the function");
		}
	}
	
	@Override
	public InstanceBoundary[] checkEligibilityAndExecuteAllByType(String type, String id, int page, int size) {
		if(this.checkRole(id, UserRole.MANAGER)) {
			List<InstanceBoundary> boundaryLst = getAllByTypeAttribute(type, size, page);
			return boundaryLst.toArray(new InstanceBoundary[0]);
		}else if(this.checkRole(id, UserRole.PLAYER)){
			List<InstanceBoundary> boundaryLst = getAllByTypeAttributeAndActive(type, size, page);
			return boundaryLst.toArray(new InstanceBoundary[0]);
		}else {
			throw new EligibilityException("You don't have authority to execute the function");
		}
	}
	
	@Override
	public InstanceBoundary[] checkEligibilityAndExecuteAllByLatAndLng(float lat, float lng, float distance, String id,
			int page, int size) {
		if(this.checkRole(id, UserRole.MANAGER)) {
			List<InstanceBoundary> boundaryLst = getAllByLatAndLngAttributes(lat, lng, distance, size, page);
			return boundaryLst.toArray(new InstanceBoundary[0]);
		}else if(this.checkRole(id, UserRole.PLAYER)){
			List<InstanceBoundary> boundaryLst = getAllByLatAndLngAttributesAndActive(lat, lng, distance, size, page);
			return boundaryLst.toArray(new InstanceBoundary[0]);
		}else {
			throw new EligibilityException("You don't have authority to execute the function");
		}
	}

	@Override
	public void checkEligibilityAndExecuteUpdate(String id, InstanceBoundary update, String instanceDomain, String instanceId) {
		if(this.checkRole(id, UserRole.MANAGER)) {
			if (update == null) {
				throw new RuntimeException("update must not be null");
			}else {
				// check if instance is existed
				this.getSpecificInstance(instanceDomain, instanceId);
				// updating instance
				this.updateInstance(instanceDomain, instanceId, update);
			}	
		}else {
			throw new EligibilityException("You don't have authority to execute the function");
		}
	}

	@Override
	public List<InstanceBoundary> getAllInstancesByActive(int page, int size) {
		return StreamSupport
				.stream(
						this.instanceDao
						.findAll(PageRequest.of(page, size, Direction.ASC, "instanceTimestamp", "instanceId")).spliterator(),
						false)  				   		  					 // Stream<InstanceEntity>
				.map(this.instanceConverter::entityToBoundaryConvertion)    // Stream<InstanceBoundary>
				.filter(obj-> obj.getActive())								// ONLY active instances
				.collect(Collectors.toList()); 							   	 // List<InstanceBoundary>
	}
}
