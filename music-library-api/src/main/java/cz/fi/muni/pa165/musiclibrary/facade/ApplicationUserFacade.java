package cz.fi.muni.pa165.musiclibrary.facade;

import cz.fi.muni.pa165.musiclibrary.dto.ApplicationUserCreateDTO;
import cz.fi.muni.pa165.musiclibrary.dto.ApplicationUserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Kovarik Tomas
 */
@Service
public interface ApplicationUserFacade {

	Long create(ApplicationUserCreateDTO u);

	void update(ApplicationUserDTO user);

	void delete(Long id);

	ApplicationUserDTO findById(Long id);

	ApplicationUserDTO findByEmail(String email);

	void setPassword(ApplicationUserDTO user, String password);

	List<ApplicationUserDTO> findAll();

	boolean verifyPassword(Long userId, String rawPassword);
}
