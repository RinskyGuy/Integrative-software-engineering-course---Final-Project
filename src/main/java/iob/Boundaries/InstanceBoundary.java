package iob.Boundaries;

import java.util.Date;
import java.util.Map;
import javax.persistence.Lob;
import iob.RawInstaces.ComplexId;
import iob.RawInstaces.CreatedBy;
import iob.RawInstaces.Location;

public class InstanceBoundary {
	private ComplexId instanceId;
	private String type;
	private String name;
	private Boolean active;
	private Date createdTimestamp;
	private CreatedBy createdBy;
	private Location location;
	private Map<String, Object> instanceAttributes;
	
	
	public InstanceBoundary() {
		super();
	}

	public ComplexId getInstanceId() {
		return instanceId;
	}


	public void setInstanceId(ComplexId instanceId) {
		this.instanceId = instanceId;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Boolean getActive() {
		return active;
	}


	public void setActive(Boolean active) {
		this.active = active;
	}


	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}


	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	
	@Lob
	public CreatedBy getCreatedBy() {
		return this.createdBy;
	}
	
	public void setCreatedBy(CreatedBy createdBy) {
		this.createdBy = createdBy;
	}

	public Location getLocation() {
		return location;
	}


	public void setLocation(Location location) {
		this.location = location;
	}


	public Map<String, Object> getInstanceAttributes() {
		return instanceAttributes;
	}

	
	public void setInstanceAttributes(Map<String, Object> instanceAttributes) {
		this.instanceAttributes = instanceAttributes;
	}

	@Override
	public String toString() {
		return "InstanceBoundary [instanceId=" + instanceId + ", type=" + type
				+ ", name=" + name + ", active=" + active + ", createdTimeStamp=" + createdTimestamp + ", createdBy="
				+ createdBy + ", location=" + location + ", instanceAttirbute=" + instanceAttributes + "]";
	}
	
}
