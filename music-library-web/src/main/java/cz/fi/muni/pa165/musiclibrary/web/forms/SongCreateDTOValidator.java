/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
		if (songCreateDTO.getAlbumId() == null) return;
		if (songCreateDTO.getMusicianId() == null) return;
		if (songCreateDTO.getGenreId() == null) return;
		if (songCreateDTO.getTitle() == null) return;
		// not finnished
	}

}
