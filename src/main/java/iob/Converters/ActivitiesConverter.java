package iob.Converters;


import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import iob.Boundaries.ActivityBoundary;
import iob.Jpas.InstanceNotFoundException;
import iob.RawInstaces.ComplexId;
import iob.RawInstaces.Instance;
import iob.RawInstaces.InvokedBy;
import iob.RawInstaces.UserId;
import iob.data.ActivityEntity;

@Component
public class ActivitiesConverter {
	private ObjectMapper jackson;
	
	@PostConstruct
	public void init() {
		this.jackson = new ObjectMapper();
		
	}
	
	public ActivityEntity boundaryToEntityConvertion(ActivityBoundary boundary) {
		ActivityEntity entity = new ActivityEntity();
		
		if(boundary.getActivityId() != null) {
			String uniqeId = "";
			if(boundary.getActivityId().getId() != null) {
				uniqeId = boundary.getActivityId().getId();
			}
			
			if(boundary.getActivityId().getDomain() != null) {
				uniqeId += "@@" + boundary.getActivityId().getDomain();
			}
			
			entity.setActivityId(uniqeId);
		}
		
		if(boundary.getCreatedTimestamp() != null)
			entity.setActivityTimestamp(boundary.getCreatedTimestamp());
		
		if(boundary.getInstance() != null){
			if(boundary.getInstance().getInstanceId() != null) {
				if(boundary.getInstance().getInstanceId().getDomain() != null)
					entity.setInstanceIdDomain(boundary.getInstance().getInstanceId().getDomain());
				else {
					throw new RuntimeException("Instance Domain not found");
				}
				if(boundary.getInstance().getInstanceId().getId() != null)
					entity.setInstanceIdUUID(boundary.getInstance().getInstanceId().getId());
				else {
					throw new InstanceNotFoundException("Instance ID not found");
				}
			}
		} else {
			throw new InstanceNotFoundException("Instance not found");
		}
		
		if(boundary.getType() != null)
			entity.setType(boundary.getType());
		else {
			throw new RuntimeException("Incorrect type");
		}
		
		if(boundary.getInvokedBy() != null) {
			if(boundary.getInvokedBy().getUserId() != null) {
				if(boundary.getInvokedBy().getUserId().getDomain() != null)
					entity.setUserIdDomain(boundary.getInvokedBy().getUserId().getDomain());
				else {
						throw new RuntimeException("User Domain not found");
					}
				if(boundary.getInvokedBy().getUserId().getEmail() != null)
					entity.setUserIdEmail(boundary.getInvokedBy().getUserId().getEmail());
				else {
					throw new RuntimeException("User Email not found");
				}
			}
		}else {
			throw new RuntimeException("User not found");
		}
				
		Map<String, Object> map = boundary.getActivityAttributes();
		entity.setActivityAttributes(this.toEntity(map));
		return entity;
	}
	
	public ActivityBoundary EntityToBoundaryConvertion(ActivityEntity entity) {
		ActivityBoundary boundary = new ActivityBoundary();
		
		String[] id_Domain_array_1 = entity.getActivityId().split("@@");
		ComplexId activityId = new ComplexId(id_Domain_array_1[0], id_Domain_array_1[1]);
		boundary.setActivityId(activityId);
		
		boundary.setType(entity.getType());
		
		
		Instance instance = new Instance();
		ComplexId complexId = new ComplexId();
		complexId.setId(entity.getInstanceIdUUID());
		complexId.setDomain(entity.getInstanceIdDomain());
		instance.setInstanceId(complexId);
		boundary.setInstance(instance);
		
		boundary.setCreatedTimestamp(entity.getActivityTimestamp());
		
		UserId userId = new UserId();
		InvokedBy invokedBy = new InvokedBy();
		userId.setDomain(entity.getUserIdDomain());
		userId.setEmail(entity.getUserIdEmail());
		invokedBy.setUserId(userId);
		boundary.setInvokedBy(invokedBy);
		
		if(entity.getActivityAttributes() != null) {
			try {
				Map<String, Object> boundaryAttributes
					= this.jackson.readValue(
							entity.getActivityAttributes(),
							Map.class);
				boundary.setActivityAttributes(boundaryAttributes);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}else {
			boundary.setActivityAttributes(null);
		}
		
		return boundary;
	}
	
	public String toEntity (Map<String, Object> attributes) {
		if (attributes != null) {
			try {
				String json = this.jackson.writeValueAsString(attributes);
				return json;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}else {
			return null;
		}
	}
}
