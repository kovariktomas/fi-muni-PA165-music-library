package cz.fi.muni.pa165.musiclibrary.dao;

import cz.fi.muni.pa165.musiclibrary.entity.ApplicationUser;
import cz.fi.muni.pa165.musiclibrary.exceptions.UserAlreadyExistsException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Kovarik Tomas
 */
@Repository
public class ApplicationUserDaoImpl implements ApplicationUserDao {

	@PersistenceContext
	private EntityManager em;


	@Override
	public void create(ApplicationUser user) throws UserAlreadyExistsException {
		if (emailAlreadyExists(user.getEmail())) {
			throw new UserAlreadyExistsException("User with this e-mail already exists.");
		}
		em.persist(user);
	}

	private boolean emailAlreadyExists(String email) {
		Long count = em.createQuery("SELECT COUNT(u) FROM ApplicationUser u WHERE u.email = :email", Long.class)
			.setParameter("email", email)
			.getSingleResult();
		return count > 0;
	}

	@Override
	public void delete(ApplicationUser user) {
		em.remove(findById(user.getId()));
	}

	@Override
	public void update(ApplicationUser user) {
		em.merge(user);
	}

	@Override
	public ApplicationUser findById(Long id) {
		return em.find(ApplicationUser.class, id);
	}

	@Override
	public ApplicationUser findByEmail(String email) {
		return em.createQuery("SELECT u FROM ApplicationUser u WHERE u.email = :email", ApplicationUser.class)
				.setParameter("email", email)
				.getSingleResult();
	}

	@Override
	public List<ApplicationUser> findAll() {
		return em.createQuery("SELECT u FROM ApplicationUser u", ApplicationUser.class)
			.getResultList();
	}
}
