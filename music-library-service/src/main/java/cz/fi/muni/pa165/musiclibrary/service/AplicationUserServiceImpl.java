package cz.fi.muni.pa165.musiclibrary.service;

import cz.fi.muni.pa165.musiclibrary.dao.AplicationUserDao;
import cz.fi.muni.pa165.musiclibrary.entity.AplicationUser;
import cz.fi.muni.pa165.musiclibrary.utils.SearchHelper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * @author Kovarik Tomas
 */

@Service
public class AplicationUserServiceImpl implements AplicationUserService {

	@Inject
	private AplicationUserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public AplicationUser create(AplicationUser u) {
		userDao.create(u);
		return u;
	}

	@Override
	public void update(AplicationUser u) {
		userDao.update(u);
	}

	@Override
	public void delete(AplicationUser u) {
		userDao.delete(u);
	}

	@Override
	public AplicationUser findById(Long id) {
		return userDao.findById(id);
	}

	@Override
	public AplicationUser findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public List<AplicationUser> findAll() {
		return userDao.findAll();
	}
	
	@Override 
	public void setPassword(Long userId, String rawPassword){
		 AplicationUser u = userDao.findById(userId);
		 u.setPassHash(passwordEncoder.encode(rawPassword));
		 userDao.update(u);
	};
	
	@Override 
	public boolean verifyPassword(Long userId, String rawPassword){
		 AplicationUser u = userDao.findById(userId);
		 return passwordEncoder.matches(rawPassword, u.getPassHash());
	};
}
