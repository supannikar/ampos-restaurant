package ampos.restaurant.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1605770292496886338L;

	public BadRequestException(String paramString) {
		super(paramString);
	}

}
