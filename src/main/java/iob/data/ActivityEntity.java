package iob.data;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ACTIVITIES")
public class ActivityEntity {
	private String activityId;
	private String type;
	private String instanceIdDomain;
	private String instanceIdUUID;
	private Date activityTimestamp;
	private String userIdDomain;
	private String userIdEmail;
	private String activityAttributes;
	
	
	public ActivityEntity() {
	}

	@Id
	public String getActivityId() {
		return activityId;
	}


	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getInstanceIdDomain() {
		return instanceIdDomain;
	}


	public void setInstanceIdDomain(String instanceIdDomain) {
		this.instanceIdDomain = instanceIdDomain;
	}


	public String getInstanceIdUUID() {
		return instanceIdUUID;
	}


	public void setInstanceIdUUID(String instanceIdUUID) {
		this.instanceIdUUID = instanceIdUUID;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getActivityTimestamp() {
		return activityTimestamp;
	}


	public void setActivityTimestamp(Date activityTimestamp) {
		this.activityTimestamp = activityTimestamp;
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

	@Lob
	public String getActivityAttributes() {
		return activityAttributes;
	}


	public void setActivityAttributes(String activityAttributes) {
		this.activityAttributes = activityAttributes;
	}
	
	
}
