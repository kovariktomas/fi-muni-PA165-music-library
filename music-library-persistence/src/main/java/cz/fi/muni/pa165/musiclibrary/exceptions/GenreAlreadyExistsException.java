package cz.fi.muni.pa165.musiclibrary.exceptions;

import org.springframework.dao.DataAccessException;

public class GenreAlreadyExistsException extends DataAccessException {

	public GenreAlreadyExistsException(String message) {
		super(message);
	}
}
