package cz.fi.muni.pa165.musiclibrary.service;

import cz.fi.muni.pa165.musiclibrary.entity.AplicationUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Kovarik Tomas
 */

@Service
public interface AplicationUserService {

	AplicationUser create(AplicationUser u);

	void update(AplicationUser u);

	void delete(AplicationUser u);

	AplicationUser findById(Long id);

	AplicationUser findByEmail(String email);

	List<AplicationUser> findAll();
	
	void setPassword(Long userId, String password);
	
	boolean verifyPassword(Long userId, String rawPassword);

}
