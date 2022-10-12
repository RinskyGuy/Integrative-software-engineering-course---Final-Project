package iob.Boundaries;

public class TagsBoundary {
	private long id;
	private String tag;
	
	public TagsBoundary() {}
	
	public TagsBoundary(long id, String tag) {
		super();
		this.id = id;
		this.tag = tag;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
}
