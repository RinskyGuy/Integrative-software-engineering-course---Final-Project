package iob.Boundaries;

import iob.RawInstaces.UserId;

public class UserBoundary {
	private UserId userId;
	private String username;
	private String role;
	private String avatar;

	public UserBoundary() {
		super();
	}

	public UserBoundary(UserId id, String username, String role, String avatar) {
		super();
		this.userId = id;
		this.username = username;
		this.role = role;
		this.avatar = avatar;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public UserId getUserId() {
		return userId;
	}
	public void setId(UserId id) {
		this.userId = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "UserBoundary [id=" + userId + ", userName=" + username + ", role=" + role + ", avater=" + avatar + "]";
	}
}
