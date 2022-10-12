package iob.Jpas;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EligibilityException extends RuntimeException {
	private static final long serialVersionUID = -7635918652995181949L;

	public EligibilityException() {}

	public EligibilityException(String message) {
		super(message);
	}

	public EligibilityException(Throwable cause) {
		super(cause);
	}

	public EligibilityException(String message, Throwable cause) {
		super(message, cause);
	}
}
