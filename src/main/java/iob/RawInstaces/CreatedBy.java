package iob.RawInstaces;

public class CreatedBy {
	private UserId userId;
	
	
	public CreatedBy() {
		super();
	}

	public UserId getUserId() {
		return userId;
	}

	public void setUserId(UserId user) {
		this.userId = user;
	}

	@Override
	public String toString() {
		return "CreatedBy [userId=" + userId + "]";
	}
	
}
