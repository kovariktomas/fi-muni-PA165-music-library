package cz.fi.muni.pa165.musiclibrary.service.facade;


import cz.fi.muni.pa165.musiclibrary.dto.AplicationUserCreateDTO;
import cz.fi.muni.pa165.musiclibrary.dto.AplicationUserDTO;
import cz.fi.muni.pa165.musiclibrary.entity.AplicationUser;
import cz.fi.muni.pa165.musiclibrary.facade.AplicationUserFacade;
import cz.fi.muni.pa165.musiclibrary.service.BeanMappingService;
import cz.fi.muni.pa165.musiclibrary.service.AplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Kovarik Tomas
 */
@Service
@Transactional
public class AplicationUserFacadeImpl implements AplicationUserFacade {

	@Autowired
	private AplicationUserService userService;

	@Autowired
	private BeanMappingService beanMappingService;

	@Override
	public Long create(AplicationUserCreateDTO u) {
		AplicationUser mappedUser = beanMappingService.mapTo(u, AplicationUser.class);
		//save user
		AplicationUser newUser = userService.create(mappedUser);
		return newUser.getId();
	}

	@Override
	public void delete(Long id) {
		userService.delete(userService.findById(id));
	}

	@Override
	public void update(AplicationUserDTO u) {
		userService.update(beanMappingService.mapTo(u, AplicationUser.class));
	}

	@Override
	public AplicationUserDTO findById(Long id) {
		AplicationUser user  = userService.findById(id);
		return (user == null) ? null : beanMappingService.mapTo(user, AplicationUserDTO.class);
	}

	@Override
	public AplicationUserDTO findByEmail(String emial) {
		AplicationUser user = userService.findByEmail(emial);
		return (user == null) ? null : beanMappingService.mapTo(user, AplicationUserDTO.class);
	}

	@Override
	public List<AplicationUserDTO> findAll() {
		return beanMappingService.mapTo(userService.findAll(), AplicationUserDTO.class);
	}
	
	@Override
	public void setPassword(AplicationUserDTO user, String password){
		userService.setPassword(user.getId(), password);
	}
}
