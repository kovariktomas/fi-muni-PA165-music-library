package cz.fi.muni.pa165.musiclibrary.web.forms;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Iva Liberova
 * @author Jan-Sebastian Fabik
 */
public class AlbumCreateFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return AlbumCreateForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AlbumCreateForm albumCreate = (AlbumCreateForm) target;

		if (albumCreate.getTitle() == null || albumCreate.getTitle().isEmpty()) {
			errors.rejectValue("title", "AlbumCreateFormValidator.title.required");
		}

		if (albumCreate.getReleaseDate() == null) {
			errors.rejectValue("releaseDate", "AlbumCreateFormValidator.releaseDate.required");
		}

		if (albumCreate.getAlbumArt() == null || albumCreate.getAlbumArt().length == 0) {
			errors.rejectValue("albumArt", "AlbumCreateFormValidator.albumArt.required");
		}
	}
}
