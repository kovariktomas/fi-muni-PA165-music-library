package cz.fi.muni.pa165.musiclibrary.exceptions;

import org.springframework.dao.DataAccessException;

public class InvalidArgumentException extends DataAccessException {

	public InvalidArgumentException(String message) {
		super(message);
	}
}
