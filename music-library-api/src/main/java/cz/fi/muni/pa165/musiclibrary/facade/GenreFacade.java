package cz.fi.muni.pa165.musiclibrary.facade;

import cz.fi.muni.pa165.musiclibrary.dto.GenreCreateDTO;
import cz.fi.muni.pa165.musiclibrary.dto.GenreDTO;

import java.util.List;

/**
 * @author Kovarik Tomas
 */
public interface GenreFacade {
	public Long createGenre(GenreCreateDTO g);

	public void deleteGenre(Long genreId);

	public List<GenreDTO> getAllGenres();

	public GenreDTO getGenreWithId(Long id);

	public List<GenreDTO> findGenreWithPattern(String pattern);
}
