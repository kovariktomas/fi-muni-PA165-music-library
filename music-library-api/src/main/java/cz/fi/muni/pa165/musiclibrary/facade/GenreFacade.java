package cz.fi.muni.pa165.musiclibrary.facade;

import cz.fi.muni.pa165.musiclibrary.dto.GenreCreateDTO;
import cz.fi.muni.pa165.musiclibrary.dto.GenreDTO;

import java.util.List;

/**
 * @author Kovarik Tomas
 */
public interface GenreFacade {
	Long create(GenreCreateDTO g);

	void delete(Long genreId);
	
	void update(GenreDTO genre);

	List<GenreDTO> findAll();

	GenreDTO findById(Long id);

	List<GenreDTO> findByName(String query);
}
