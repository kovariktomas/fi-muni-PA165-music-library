package cz.fi.muni.pa165.musiclibrary.service;

import cz.fi.muni.pa165.musiclibrary.dao.ApplicationUserDao;
import cz.fi.muni.pa165.musiclibrary.entity.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Kovarik Tomas
 */

@Service
public class ApplicationUserServiceImpl implements ApplicationUserService {

	@Autowired
	private ApplicationUserDao userDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void create(ApplicationUser u, String password) {
		u.setPassHash(passwordEncoder.encode(password));
		userDao.create(u);
	}

	@Override
	public void update(ApplicationUser u) {
		userDao.update(u);
	}

	@Override
	public void delete(ApplicationUser u) {
		userDao.delete(u);
	}

	@Override
	public ApplicationUser findById(Long id) {
		return userDao.findById(id);
	}

	@Override
	public ApplicationUser findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public List<ApplicationUser> findAll() {
		return userDao.findAll();
	}

	@Override
	public void setPassword(Long userId, String rawPassword) {
		ApplicationUser u = userDao.findById(userId);
		u.setPassHash(passwordEncoder.encode(rawPassword));
		userDao.update(u);
	}

	@Override
	public boolean verifyPassword(Long userId, String rawPassword) {
		ApplicationUser u = userDao.findById(userId);
		return passwordEncoder.matches(rawPassword, u.getPassHash());
	}
}
