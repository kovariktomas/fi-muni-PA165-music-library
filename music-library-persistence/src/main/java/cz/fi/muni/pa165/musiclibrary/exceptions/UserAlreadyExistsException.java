package cz.fi.muni.pa165.musiclibrary.exceptions;

import org.springframework.dao.DataAccessException;

public class UserAlreadyExistsException extends DataAccessException {

	public UserAlreadyExistsException(String message) {
		super(message);
	}
}
