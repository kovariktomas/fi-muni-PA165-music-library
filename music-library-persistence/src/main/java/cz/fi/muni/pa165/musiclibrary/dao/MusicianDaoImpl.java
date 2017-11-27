package cz.fi.muni.pa165.musiclibrary.dao;

import cz.fi.muni.pa165.musiclibrary.entity.Musician;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author David Koncak
 */
@Repository
public class MusicianDaoImpl implements cz.fi.muni.pa165.musiclibrary.dao.MusicianDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void create(Musician m) {
		em.persist(m);
	}

	@Override
	public void remove(Musician m) throws IllegalArgumentException {
		em.remove(findById(m.getId()));
	}

	@Override
	public void update(Musician m) {
		em.merge(m);
	}

	@Override
	public Musician findById(Long id) {
		return em.find(Musician.class, id);
	}

	@Override
	public List<Musician> findByName(List<String> patterns) {
		StringBuilder queryBuilder = new StringBuilder("SELECT m FROM Musician m");

		for (int i = 0; i < patterns.size(); i++) {
			if (i == 0) {
				queryBuilder.append(" WHERE");
			} else {
				queryBuilder.append(" AND");
			}
			queryBuilder.append(" m.name LIKE :pattern").append(i);
		}

		TypedQuery<Musician> query = em.createQuery(queryBuilder.toString(), Musician.class);

		for (int i = 0; i < patterns.size(); i++) {
			query.setParameter("pattern" + i, patterns.get(i));
		}

		return query.getResultList();
	}

	@Override
	public List<Musician> findAll() {
		return em.createQuery("select m from Musician m", Musician.class)
				.getResultList();
	}

}
