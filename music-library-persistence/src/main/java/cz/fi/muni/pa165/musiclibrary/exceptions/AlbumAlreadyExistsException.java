package cz.fi.muni.pa165.musiclibrary.exceptions;

import org.springframework.dao.DataAccessException;

public class AlbumAlreadyExistsException extends DataAccessException {

	public AlbumAlreadyExistsException(String message) {
		super(message);
	}
}
