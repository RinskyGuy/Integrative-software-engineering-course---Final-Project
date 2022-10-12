package iob.Boundaries;

import org.springframework.util.StopWatch;

public class ComplexBoundary {
	private StopWatch time;
	private PhotoBoundary photo;

	public ComplexBoundary() {
	}

	public ComplexBoundary(StopWatch time, Integer y, PhotoBoundary photo) {
		super();
		this.time = time;
		this.photo = photo;
	}

	public StopWatch getTime() {
		return time;
	}

	public void setTime(StopWatch time) {
		this.time = time;
	}

	public PhotoBoundary getName() {
		return photo;
	}

	public void setName(PhotoBoundary photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "ComplexBoundary [x=" + time + ", photo=" + photo + "]";
	}

}
