package cz.fi.muni.pa165.musiclibrary.facade;

import cz.fi.muni.pa165.musiclibrary.dto.GenreDTO;
import cz.fi.muni.pa165.musiclibrary.dto.GenreCreateDTO;
import java.util.List;
/**
 * @author Kovarik Tomas
 */
public interface GenreFacade {
	public Long createGenre(GenreCreateDTO g);
	public void deleteGenre(Long genreId);
	public List<GenreDTO> getAllGenres();
	public GenreDTO getGenreWithId(Long id);
	public GenreDTO getGenreWithName(String name);
}