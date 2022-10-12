package iob.RawInstaces;

public class UserId {
	private String email;
	private String domain;
	
	public UserId() {
		super();
	}

	public UserId(String domain, String email) {
		super();
		this.domain = domain;
		this.email = email;
	}
	
	public UserId(String other) {
		String[] fractured = other.split("@@");
		System.out.println(other);
		this.domain = fractured[0];
		this.email = fractured[1];
	}

	public String getDomain() {
		return domain;
	}
	
	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

	@Override
	public String toString() {
		return "IdDetails [domain=" + domain + ", email=" + email + "]";
	}
}
