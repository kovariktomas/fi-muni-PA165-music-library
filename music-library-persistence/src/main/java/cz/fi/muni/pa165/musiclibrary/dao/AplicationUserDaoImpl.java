package cz.fi.muni.pa165.musiclibrary.dao;

import cz.fi.muni.pa165.musiclibrary.entity.AplicationUser;
import cz.fi.muni.pa165.musiclibrary.exceptions.UserAlreadyExistsException;
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
public class AplicationUserDaoImpl implements AplicationUserDao {

	@PersistenceContext
	private EntityManager em;


	@Override
	public void create(AplicationUser user) throws UserAlreadyExistsException {
		if (emailAlreadyExists(user.getEmail())) {
			throw new UserAlreadyExistsException("User with this e-mail already exists.");
		}
		em.persist(user);
	}

	private boolean emailAlreadyExists(String email) {
		Long count = em.createQuery("SELECT COUNT(u) FROM AplicationUser u WHERE u.email = :email", Long.class)
			.setParameter("email", email)
			.getSingleResult();
		return count > 0;
	}

	@Override
	public void delete(AplicationUser user) {
		em.remove(findById(user.getId()));
	}

	@Override
	public void update(AplicationUser user) {
		em.merge(user);
	}

	@Override
	public AplicationUser findById(Long id) {
		return em.find(AplicationUser.class, id);
	}

	@Override
	public AplicationUser findByEmail(String email) {
		return em.createQuery("SELECT u FROM AplicationUser u WHERE u.email = :email", AplicationUser.class)
				.setParameter("email", email)
				.getSingleResult();
	}

	@Override
	public List<AplicationUser> findAll() {
		return em.createQuery("SELECT u FROM AplicationUser u", AplicationUser.class)
			.getResultList();
	}
}
