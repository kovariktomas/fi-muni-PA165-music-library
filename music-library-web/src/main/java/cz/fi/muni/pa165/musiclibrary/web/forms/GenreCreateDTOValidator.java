package cz.fi.muni.pa165.musiclibrary.web.forms;

import cz.fi.muni.pa165.musiclibrary.dto.GenreCreateDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Tomas Kovarik
 */
public class GenreCreateDTOValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return GenreCreateDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		GenreCreateDTO genreCreateDTO = (GenreCreateDTO) target;
		if (genreCreateDTO.getName() == null) return;
	}
}
