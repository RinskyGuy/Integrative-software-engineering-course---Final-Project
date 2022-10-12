package iob.Boundaries;

public class NameBoundary {
	private String first;
	private String last;
	private String middle;

	public NameBoundary() {
	}

	public NameBoundary(String first, String last, String middle) {
		super();
		this.first = first;
		this.last = last;
		this.middle = middle;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getMiddle() {
		return middle;
	}

	public void setMiddle(String middle) {
		this.middle = middle;
	}

	@Override
	public String toString() {
		return "NameBoundary [first=" + first + ", last=" + last + ", middle=" + middle + "]";
	}

}
