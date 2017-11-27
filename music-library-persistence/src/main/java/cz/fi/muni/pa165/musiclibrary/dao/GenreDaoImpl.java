package cz.fi.muni.pa165.musiclibrary.dao;

import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.exceptions.GenreAlreadyExistsException;
import cz.fi.muni.pa165.musiclibrary.exceptions.InvalidArgumentException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Locale;

/**
 * @author Kovarik Tomas
 */
@Repository
public class GenreDaoImpl implements GenreDao {

	@PersistenceContext
	private EntityManager em;


	@Override
	public void create(Genre genre) throws GenreAlreadyExistsException, InvalidArgumentException {
		if (genre == null) {
			throw new InvalidArgumentException("Argument genre must not be null.");
		}
		if (em.contains(genre)) {
			throw new InvalidArgumentException("The given genre already exists.");
		}
		if (nameAlreadyExists(genre.getName())) {
			throw new GenreAlreadyExistsException("Genre with this name already exists.");
		}
		em.persist(genre);
	}

	private boolean nameAlreadyExists(String name) {
		Long count = em.createQuery("SELECT COUNT(g) FROM Genre g WHERE g.name = :name", Long.class)
			.setParameter("name", name)
			.getSingleResult();
		return count > 0;
	}

	@Override
	public void delete(Genre genre) throws InvalidArgumentException {
		if (genre == null) {
			throw new InvalidArgumentException("Argument genre must not be null.");
		}
		if (!em.contains(genre)) {
			throw new InvalidArgumentException("The given genre does not exist.");
		}
		em.remove(genre);
	}

	@Override
	public void update(Genre genre) throws InvalidArgumentException {
		if (genre == null) {
			throw new InvalidArgumentException("Argument genre must not be null.");
		}
		if (!em.contains(genre)) {
			throw new InvalidArgumentException("The given genre does not exist.");
		}
		em.merge(genre);
	}

	@Override
	public Genre findById(Long id) throws InvalidArgumentException {
		if (id == null) {
			throw new InvalidArgumentException("Argument id must not be null.");
		}
		return em.find(Genre.class, id);
	}

	@Override
	public List<Genre> findByName(List<String> patterns) throws InvalidArgumentException {
		if (patterns == null) {
			throw new InvalidArgumentException("Argument name must not be null.");
		}
		StringBuilder queryBuilder = new StringBuilder("select g from Genre g");

		for (int i = 0; i < patterns.size(); i++) {
			if (i == 0) {
				queryBuilder.append(" WHERE");
			} else {
				queryBuilder.append(" AND");
			}
			queryBuilder.append(" LOWER(g.name) LIKE :pattern").append(i);
		}

		TypedQuery<Genre> query = em.createQuery(queryBuilder.toString(), Genre.class);

		for (int i = 0; i < patterns.size(); i++) {
			query.setParameter("pattern" + i, patterns.get(i).toLowerCase(Locale.ENGLISH));
		}
		return query.getResultList();
	}

	@Override
	public List<Genre> findAll() {
		return em.createQuery("select g from Genre g", Genre.class)
			.getResultList();
	}
}
