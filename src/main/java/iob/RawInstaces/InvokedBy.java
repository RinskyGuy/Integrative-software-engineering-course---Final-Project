package iob.RawInstaces;

public class InvokedBy {
	private UserId userId;
	
	
	public InvokedBy() {
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
		return "InvokedBy [userId=" + userId + "]";
	}
	
}
