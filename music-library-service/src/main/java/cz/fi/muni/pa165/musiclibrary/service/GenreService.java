package cz.fi.muni.pa165.musiclibrary.service;

import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Kovarik Tomas
 */

@Service
public interface GenreService {
	Genre findById(Long id);

	List<Genre> findByName(String query);

	List<Genre> findAll();

	Genre create(Genre g);

	void delete(Genre g);
	
	void update(Genre g);
	
}
