package cz.fi.muni.pa165.musiclibrary.web.forms;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Jan-Sebastian Fabik
 */
public class AlbumEditFormDataValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return AlbumEditFormData.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AlbumEditFormData albumData = (AlbumEditFormData) target;

		if (albumData.getTitle() == null || albumData.getTitle().isEmpty()) {
			errors.rejectValue("title", "AlbumEditFormDataValidator.title.required");
		}

		if (albumData.getReleaseDate() == null) {
			errors.rejectValue("releaseDate", "AlbumEditFormDataValidator.releaseDate.required");
		}
	}
}
