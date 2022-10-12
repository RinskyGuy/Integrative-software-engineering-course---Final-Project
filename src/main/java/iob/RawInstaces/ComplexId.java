package iob.RawInstaces;

public class ComplexId {
	private String id;
	private String domain;
	
	
	public ComplexId() {
		super();
	}


	public ComplexId(String id, String domain) {
		super();
		this.id = id;
		this.domain = domain;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
}
