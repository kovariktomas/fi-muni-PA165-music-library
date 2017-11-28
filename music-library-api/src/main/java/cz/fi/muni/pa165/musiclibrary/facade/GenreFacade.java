package cz.fi.muni.pa165.musiclibrary.facade;

import cz.fi.muni.pa165.musiclibrary.dto.GenreCreateDTO;
import cz.fi.muni.pa165.musiclibrary.dto.GenreDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Kovarik Tomas
 */
@Service
public interface GenreFacade {

	Long create(GenreCreateDTO g);

	void update(GenreDTO genre);

	void delete(Long id);

	GenreDTO findById(Long id);

	List<GenreDTO> findByName(String query);

	List<GenreDTO> findAll();
}
