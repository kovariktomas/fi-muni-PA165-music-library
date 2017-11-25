package cz.fi.muni.pa165.musiclibrary.exceptions;

public class GenreAlreadyExistsException extends RuntimeException {

	public GenreAlreadyExistsException(String message) {
		super(message);
	}
}
