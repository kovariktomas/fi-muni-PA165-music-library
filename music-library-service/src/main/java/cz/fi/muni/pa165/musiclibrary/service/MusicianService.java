package cz.fi.muni.pa165.musiclibrary.service;

import cz.fi.muni.pa165.musiclibrary.entity.Musician;

import java.util.List;

/**
 * @author Jan-Sebastian Fabík
 */
public interface MusicianService {

	void create(Musician musician);

	void update(Musician musician);

	void delete(Musician musician);

	Musician findById(Long id);

	List<Musician> findByName(List<String> patterns);

	List<Musician> findAll();
}
