package cz.fi.muni.pa165.musiclibrary.web.forms;

import cz.fi.muni.pa165.musiclibrary.dto.AlbumCreateDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Iva Liberova
 */
public class AlbumCreateDTOValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return AlbumCreateDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AlbumCreateDTO albumCreateDTO = (AlbumCreateDTO) target;
		if (albumCreateDTO.getTitle() == null || albumCreateDTO.getTitle().isEmpty()) {
			errors.rejectValue("name", "AlbumCreateDTOValidator.name.required");
		}
		if (albumCreateDTO.getReleaseDate() == null) {
			errors.rejectValue("releaseDate", "AlbumCreateDTOValidator.releaseDate.required");
		}
	}
}
