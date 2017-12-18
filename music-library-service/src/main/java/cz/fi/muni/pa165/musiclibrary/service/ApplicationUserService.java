package cz.fi.muni.pa165.musiclibrary.service;

import cz.fi.muni.pa165.musiclibrary.entity.ApplicationUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Kovarik Tomas
 */

@Service
public interface ApplicationUserService {

	void create(ApplicationUser u, String password);

	void update(ApplicationUser u);

	void delete(ApplicationUser u);

	ApplicationUser findById(Long id);

	ApplicationUser findByEmail(String email);

	List<ApplicationUser> findAll();

	void setPassword(Long userId, String password);

	boolean verifyPassword(Long userId, String rawPassword);

}
