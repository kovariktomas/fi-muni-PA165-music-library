package cz.fi.muni.pa165.musiclibrary.dao;

import java.util.List;

import cz.fi.muni.pa165.musiclibrary.entity.Genre;
/**
 *
 * @author Kovarik Tomas
 */
public interface GenreDao {
	public void create(Genre g);
	public void delete(Genre s) throws IllegalArgumentException;
	public void update(Genre s) throws IllegalArgumentException;
	public Genre findById(Long id);
	public List<Genre> findByName(String namePattern);
	public List<Genre> findAll();
}

