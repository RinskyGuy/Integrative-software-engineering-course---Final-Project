package iob.logic;

import java.util.List;

import iob.Boundaries.InstanceBoundary;
import iob.data.InstanceEntity;

public interface AdvanceInstancesService extends InstancesService{
	public List<InstanceBoundary> getAllInstances(int page, int size);
	
	public List<InstanceBoundary> getAllInstancesByActive(int page, int size);
	
	public List<InstanceBoundary> getAllByNameAttribute(String name, int size, int page);
	public List<InstanceBoundary> getAllByTypeAttribute(String type, int size, int page);
	public List<InstanceBoundary> getAllByLatAndLngAttributes(float lat, float lng, float distance, int size, int page);

	void checkEligibilityAndExecuteUpdate(String id, InstanceBoundary update, String instanceDomain, String instanceId);

	InstanceBoundary[] checkEligibilityAndExecuteAllByLatAndLng(float lat, float lng, float distance, String id,
			int page, int size);

	InstanceBoundary[] checkEligibilityAndExecuteAllByType(String type, String id, int page, int size);

	InstanceBoundary[] checkEligibilityAndExecuteAllByName(String name, String id, int page, int size);

	/**
	 * This method check eligibility of the user and execute accordingly
	 * MANAGER - return boundary
	 * PLAYER - return boundary ONLY if instance is active
	 * otherwise, return an empty boundary 
	 */
	InstanceBoundary[] checkEligibilityAndExecuteAll(String id, int page, int size);

	/**
	 * This method check eligibility of the user and execute accordingly
	 * MANAGER - return boundary
	 * PLAYER - return boundary ONLY if instance is active
	 * otherwise, return an empty boundary 
	 */
	public InstanceBoundary checkEligibilityAndExecuteSpecific(String instanceDomain, String instanceId, String id, int page, int size);

	boolean latLngEligibility(InstanceEntity obj, float lat, float lng, float distance);
	
	public List<InstanceBoundary> getAllByNameAttributeAndActive(String name, int size, int page);
	public List<InstanceBoundary> getAllByTypeAttributeAndActive(String type, int size, int page);
	public List<InstanceBoundary> getAllByLatAndLngAttributesAndActive(float lat, float lng, float distance, int size, int page);
}
