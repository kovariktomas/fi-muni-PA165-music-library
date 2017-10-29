package cz.fi.muni.pa165.musiclibrary.dao;

import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.entity.Musician;
import cz.fi.muni.pa165.musiclibrary.entity.Song;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

/**
 * @author Jan-Sebastian Fabík
 */
@Repository
public class SongDaoImpl implements SongDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void create(Song song) {
		if (song == null) {
			throw new IllegalArgumentException("Argument song must not be null.");
		}
		if (em.contains(song)) {
			throw new IllegalArgumentException("The given song already exists.");
		}
		em.persist(song);
	}

	@Override
	public void update(Song song) {
		if (song == null) {
			throw new IllegalArgumentException("Argument song must not be null.");
		}
		if (!em.contains(song)) {
			throw new IllegalArgumentException("The given song does not exists.");
		}
		em.merge(song);
	}

	@Override
	public void delete(Song song) {
		if (song == null) {
			throw new IllegalArgumentException("Argument song must not be null.");
		}
		em.remove(song);
	}

	@Override
	public Song findById(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("Argument id must not be null.");
		}
		return em.find(Song.class, id);
	}

	@Override
	public List<Song> findByMusician(Musician musician) {
		if (musician == null) {
			throw new IllegalArgumentException("Argument musician must not be null.");
		}
		return em.createQuery("SELECT s FROM Song s WHERE s.musician = :musician", Song.class)
				.setParameter("musician", musician)
				.getResultList();
	}

	@Override
	public List<Song> findByGenre(Genre genre) {
		if (genre == null) {
			throw new IllegalArgumentException("Argument genre must not be null.");
		}
		return em.createQuery("SELECT s FROM Song s WHERE s.genre = :genre", Song.class)
				.setParameter("genre", genre)
				.getResultList();
	}

	@Override
	public List<Song> findByTitle(String titlePattern) {
		if (titlePattern == null) {
			throw new IllegalArgumentException("Argument titlePattern must not be null.");
		}
		return em.createQuery("SELECT s FROM Song s WHERE s.title LIKE :title", Song.class)
				.setParameter("title", "%" + titlePattern + "%")
				.getResultList();
	}

	@Override
	public List<Song> findAll() {
		return em.createQuery("SELECT s from Song s", Song.class)
				.getResultList();
	}
}
