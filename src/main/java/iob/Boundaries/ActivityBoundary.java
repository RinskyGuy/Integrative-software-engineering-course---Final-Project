package iob.Boundaries;

import java.util.Date;
import java.util.Map;
import iob.RawInstaces.ComplexId;
import iob.RawInstaces.Instance;
import iob.RawInstaces.InvokedBy;

public class ActivityBoundary {
	private ComplexId activityId;
	private String type;
	private Instance instance;
	private Date createdTimestamp;
	private InvokedBy invokedBy;
	private Map<String, Object> activityAttributes;
	
	public ActivityBoundary() {
		super();
	}
	public ActivityBoundary(ComplexId id, String type, Instance instance, Date createdTimestamp,
			InvokedBy invokedBy, Map<String, Object> activityAtteributes) {
		super();
		this.activityId = id;
		this.type = type;
		this.instance = instance;
		this.createdTimestamp = createdTimestamp;
		this.invokedBy = invokedBy;
		this.activityAttributes = activityAtteributes;
	}
	
	public ComplexId getActivityId() {
		return activityId;
	}
	public void setActivityId(ComplexId id) {
		this.activityId = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Instance getInstance() {
		return instance;
	}
	public void setInstance(Instance instance) {
		this.instance = instance;
	}
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	public InvokedBy getInvokedBy() {
		return invokedBy;
	}
	public void setInvokedBy(InvokedBy invokedBy) {
		this.invokedBy = invokedBy;
	}
	public Map<String, Object> getActivityAttributes() {
		return activityAttributes;
	}
	public void setActivityAttributes(Map<String, Object> activityAttributes) {
		this.activityAttributes = activityAttributes;
	}
}
