package cz.fi.muni.pa165.musiclibrary.facade;

import cz.fi.muni.pa165.musiclibrary.dto.AplicationUserCreateDTO;
import cz.fi.muni.pa165.musiclibrary.dto.AplicationUserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Kovarik Tomas
 */
@Service
public interface AplicationUserFacade {

	Long create(AplicationUserCreateDTO u);

	void update(AplicationUserDTO user);

	void delete(Long id);

	AplicationUserDTO findById(Long id);

	AplicationUserDTO findByEmail(String email);
	
	void setPassword(AplicationUserDTO user, String password);

	List<AplicationUserDTO> findAll();
}
