package cz.fi.muni.pa165.musiclibrary.dao;

import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.exceptions.GenreAlreadyExistsException;
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
	public void create(Genre genre) throws GenreAlreadyExistsException {
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
	public void delete(Genre genre) {
		em.remove(genre);
	}

	@Override
	public void update(Genre genre) {
		em.merge(genre);
	}

	@Override
	public Genre findById(Long id) {
		return em.find(Genre.class, id);
	}

	@Override
	public List<Genre> findByName(List<String> patterns) {
		StringBuilder queryBuilder = new StringBuilder("SELECT g FROM Genre g");

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
		return em.createQuery("SELECT g FROM Genre g", Genre.class)
			.getResultList();
	}
}
