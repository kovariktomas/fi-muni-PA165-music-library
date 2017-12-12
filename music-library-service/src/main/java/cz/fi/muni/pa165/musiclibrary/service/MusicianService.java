package cz.fi.muni.pa165.musiclibrary.service;

import cz.fi.muni.pa165.musiclibrary.entity.Musician;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jan-Sebastian Fabík
 */
@Service
public interface MusicianService {

	Musician create(Musician musician);

	void update(Musician musician);

	void delete(Musician musician);

	Musician findById(Long id);

	List<Musician> findByName(String query);

	List<Musician> findAll();
}
