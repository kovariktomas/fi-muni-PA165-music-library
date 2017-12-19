package cz.fi.muni.pa165.musiclibrary.dao;

import cz.fi.muni.pa165.musiclibrary.entity.Album;
import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.entity.Musician;
import cz.fi.muni.pa165.musiclibrary.entity.Song;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Locale;

/**
 * @author Jan-Sebastian Fabík
 */
@Repository
public class SongDaoImpl implements SongDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void create(Song song) {
		em.persist(song);
	}

	@Override
	public void update(Song song) {
		em.merge(song);
	}

	@Override
	public void delete(Song song) {
		em.remove(song);
	}

	@Override
	public Song findById(Long id) {
		return em.find(Song.class, id);
	}

	@Override
	public List<Song> findByMusician(Musician musician) {
		return em.createQuery("SELECT s FROM Song s WHERE s.musician = :musician", Song.class)
			.setParameter("musician", musician)
			.getResultList();
	}

	@Override
	public List<Song> findByAlbum(Album album) {
		return em.createQuery("SELECT s FROM Song s WHERE s.album = :album", Song.class)
			.setParameter("album", album)
			.getResultList();
	}

	@Override
	public List<Song> findByGenre(Genre genre) {
		return em.createQuery("SELECT s FROM Song s WHERE s.genre = :genre", Song.class)
			.setParameter("genre", genre)
			.getResultList();
	}

	@Override
	public List<Song> findByTitle(List<String> patterns) {
		StringBuilder queryBuilder = new StringBuilder("SELECT s FROM Song s");

		for (int i = 0; i < patterns.size(); i++) {
			if (i == 0) {
				queryBuilder.append(" WHERE");
			} else {
				queryBuilder.append(" AND");
			}
			queryBuilder.append(" LOWER(s.title) LIKE :pattern").append(i);
		}

		TypedQuery<Song> query = em.createQuery(queryBuilder.toString(), Song.class);

		for (int i = 0; i < patterns.size(); i++) {
			query.setParameter("pattern" + i, patterns.get(i).toLowerCase(Locale.ENGLISH));
		}

		return query.getResultList();
	}

	@Override
	public List<Song> findAll() {
		return em.createQuery("SELECT s from Song s", Song.class)
			.getResultList();
	}
}
