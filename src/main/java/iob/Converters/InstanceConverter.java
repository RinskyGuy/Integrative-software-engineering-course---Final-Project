package iob.Converters;

import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import iob.Boundaries.InstanceBoundary;
import iob.RawInstaces.ComplexId;
import iob.RawInstaces.CreatedBy;
import iob.RawInstaces.Location;
import iob.RawInstaces.UserId;
import iob.data.InstanceEntity;

@Component
public class InstanceConverter {
	private ObjectMapper jackson;
	
	@PostConstruct
	public void init() {
		this.jackson = new ObjectMapper();
	}
	
	public InstanceEntity boundaryToEntityConvertion(InstanceBoundary boundary) {
		InstanceEntity entity = new InstanceEntity();
		
		if(boundary.getInstanceId() != null) {
			String uniqeId = "";
			if(boundary.getInstanceId().getDomain() != null) {
				uniqeId = boundary.getInstanceId().getDomain();
			}
			
			if(boundary.getInstanceId().getId() != null) {
				uniqeId += "@@" + boundary.getInstanceId().getId();
			}
			
			entity.setInstanceId(uniqeId);
		}
		
		if(boundary.getType() != null)	
			entity.setType(boundary.getType());
		else {
			throw new RuntimeException("Incorrect type");
		}
		
		if(boundary.getName() != null)
			entity.setName(boundary.getName());
		else {
			throw new RuntimeException("Name not found");
		}
		
		if(boundary.getActive() != null)
			entity.setActive(boundary.getActive());
		else {
			throw new RuntimeException("Active not found");
		}
		
		// TimeStamp created by the system thus can't be null
		entity.setInstanceTimestamp(boundary.getCreatedTimestamp());
		
		if(boundary.getCreatedBy() != null) {
			if(boundary.getCreatedBy().getUserId() != null) {
				if(boundary.getCreatedBy().getUserId().getDomain() != null) {
					entity.setUserIdDomain(boundary.getCreatedBy().getUserId().getDomain());
				}else {
					throw new RuntimeException("User Domain not found");
				}
				if(boundary.getCreatedBy().getUserId().getEmail() != null) {
					entity.setUserIdEmail(boundary.getCreatedBy().getUserId().getEmail());				
				}else {
					throw new RuntimeException("User Email not found");
				}
			}
		}else {
			throw new RuntimeException("User not found");
		}
		
		if(boundary.getLocation() != null) {
			if(boundary.getLocation().getLat() != null)
				entity.setLat(boundary.getLocation().getLat());
			else {
				throw new RuntimeException("location latitude not found");
			}
			if(boundary.getLocation().getLng() != null)
				entity.setLng(boundary.getLocation().getLng());
			else {
				throw new RuntimeException("location longitude not found");
			}
		}else {
			throw new RuntimeException("Location not found");
		}
		
		Map<String, Object> map = boundary.getInstanceAttributes();
		entity.setInstanceAttributes(this.toEntity(map));
		return entity;
	}
	
	
	public InstanceBoundary entityToBoundaryConvertion(InstanceEntity entity) {
		InstanceBoundary boundary = new InstanceBoundary();
		ComplexId complexId = new ComplexId();
		complexId.setDomain(entity.getUserIdDomain());
		
		String[] id_Domain_array = entity.getInstanceId().split("@@");
		complexId.setId(id_Domain_array[1]);
		
		boundary.setInstanceId(complexId);
		
		boundary.setType(entity.getType());
		boundary.setName(entity.getName());
		boundary.setActive(entity.getActive());
		boundary.setCreatedTimestamp(entity.getInstanceTimestamp());
		UserId userId = new UserId();
		userId.setDomain(entity.getUserIdDomain());
		userId.setEmail(entity.getUserIdEmail());
		CreatedBy createdBy = new CreatedBy();
		createdBy.setUserId(userId);
		boundary.setCreatedBy(createdBy);
		Location location = new Location(entity.getLat(), entity.getLng());
		boundary.setLocation(location);
		
		if(entity.getInstanceAttributes() != null) {
			try {
				Map<String, Object> boundaryAttributes
					= this.jackson.readValue(
							entity.getInstanceAttributes(),
							Map.class);
				boundary.setInstanceAttributes(boundaryAttributes);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}else {
			boundary.setInstanceAttributes(null);
		}
		return boundary;
	}

	public String toEntity (Map<String, Object> instanceAttirbute) {
		if (instanceAttirbute != null) {
			try {
				String json = this.jackson.writeValueAsString(instanceAttirbute);
				return json;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}else {
			return null;
		}
	}
}
