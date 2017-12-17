package cz.fi.muni.pa165.musiclibrary.web.forms;

import cz.fi.muni.pa165.musiclibrary.dto.MusicianDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Jan-Sebastian Fabik
 */
public class MusicianDTOValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MusicianDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		MusicianDTO musicianDTO = (MusicianDTO) target;

		if (musicianDTO.getName() == null || musicianDTO.getName().isEmpty()) {
			errors.rejectValue("name", "MusicianDTOValidator.name.required");
		}
	}
}
