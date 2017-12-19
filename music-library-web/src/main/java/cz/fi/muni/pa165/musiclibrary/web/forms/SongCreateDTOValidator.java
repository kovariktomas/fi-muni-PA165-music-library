package cz.fi.muni.pa165.musiclibrary.web.forms;

import cz.fi.muni.pa165.musiclibrary.dto.SongCreateDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author xkoncak
 */
public class SongCreateDTOValidator implements Validator {

	@Override
	public boolean supports(Class<?> type) {
		return SongCreateDTO.class.isAssignableFrom(type);
	}

	@Override
	public void validate(Object o, Errors errors) {
		SongCreateDTO songCreateDTO = (SongCreateDTO) o;
		if (songCreateDTO.getAlbumId() == null) {
			errors.rejectValue("albumid", "SongCreateDTOValidator.AlbumId.required");
		}
		if (songCreateDTO.getMusicianId() == null){
			errors.rejectValue("musicianid", "SongCreateDTOValidator.MusicianId.required");
		}
		if (songCreateDTO.getGenreId() == null){
			errors.rejectValue("genreid", "SongCreateDTOValidator.GenreId.required");
		}
		if (songCreateDTO.getTitle() == null || songCreateDTO.getTitle().isEmpty()) {
			errors.rejectValue("title", "SongCreateDTOValidator.title.required");
		}
		// not finnished
	}

}
