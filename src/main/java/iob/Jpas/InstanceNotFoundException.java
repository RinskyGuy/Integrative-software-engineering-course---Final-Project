package iob.Jpas;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class InstanceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -7635918652995181949L;

	public InstanceNotFoundException() {}

	public InstanceNotFoundException(String message) {
		super(message);
	}

	public InstanceNotFoundException(Throwable cause) {
		super(cause);
	}

	public InstanceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
