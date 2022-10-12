package iob.data;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="INSTANCES")
public class InstanceEntity {
	private String instanceId;
	private String type;
	private String name;
	private boolean active;
	private Date instanceTimestamp;
	private String userIdDomain;
	private String userIdEmail;
	private float lat;
	private float lng;
	private String instanceAttributes;

	public InstanceEntity() {
	}

	@Id
	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
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

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getInstanceTimestamp() {
		return instanceTimestamp;
	}

	public void setInstanceTimestamp(Date instanceTimestamp) {
		this.instanceTimestamp = instanceTimestamp;
	}

	public String getUserIdDomain() {
		return userIdDomain;
	}

	public void setUserIdDomain(String userIdDomain) {
		this.userIdDomain = userIdDomain;
	}

	public String getUserIdEmail() {
		return userIdEmail;
	}

	public void setUserIdEmail(String userIdEmail) {
		this.userIdEmail = userIdEmail;
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLng() {
		return lng;
	}

	public void setLng(float lng) {
		this.lng = lng;
	}

	@Lob
	public String getInstanceAttributes() {
		return instanceAttributes;
	}

	public void setInstanceAttributes(String instanceAttributes) {
		this.instanceAttributes = instanceAttributes;
	}
}
