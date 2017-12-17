package cz.fi.muni.pa165.musiclibrary.web.forms;

import cz.fi.muni.pa165.musiclibrary.dto.MusicianCreateDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Jan-Sebastian Fabik
 */
public class MusicianCreateDTOValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MusicianCreateDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		MusicianCreateDTO musicianCreateDTO = (MusicianCreateDTO) target;

		if (musicianCreateDTO.getName() == null || musicianCreateDTO.getName().isEmpty()) {
			errors.rejectValue("name", "MusicianCreateDTOValidator.name.required");
		}
	}
}
