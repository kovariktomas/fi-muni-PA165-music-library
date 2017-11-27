package cz.fi.muni.pa165.musiclibrary.exceptions;

public class MusicLibraryServiceException extends RuntimeException {

	public MusicLibraryServiceException() {
		super();
	}

	public MusicLibraryServiceException(String message, Throwable cause,
										boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MusicLibraryServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public MusicLibraryServiceException(String message) {
		super(message);
	}

	public MusicLibraryServiceException(Throwable cause) {
		super(cause);
	}

}
