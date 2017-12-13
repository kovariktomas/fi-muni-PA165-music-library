package cz.fi.muni.pa165.musiclibrary.service.facade;


import cz.fi.muni.pa165.musiclibrary.dto.ApplicationUserCreateDTO;
import cz.fi.muni.pa165.musiclibrary.dto.ApplicationUserDTO;
import cz.fi.muni.pa165.musiclibrary.entity.ApplicationUser;
import cz.fi.muni.pa165.musiclibrary.facade.ApplicationUserFacade;
import cz.fi.muni.pa165.musiclibrary.service.BeanMappingService;
import cz.fi.muni.pa165.musiclibrary.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Kovarik Tomas
 */
@Service
@Transactional
public class ApplicationUserFacadeImpl implements ApplicationUserFacade {

	@Autowired
	private ApplicationUserService applicationUserService;

	@Autowired
	private BeanMappingService beanMappingService;

	@Override
	public Long create(ApplicationUserCreateDTO u) {
		ApplicationUser mappedUser = beanMappingService.mapTo(u, ApplicationUser.class);
		//save user
		ApplicationUser newUser = applicationUserService.create(mappedUser);
		return newUser.getId();
	}

	@Override
	public void delete(Long id) {
		applicationUserService.delete(applicationUserService.findById(id));
	}

	@Override
	public void update(ApplicationUserDTO u) {
		applicationUserService.update(beanMappingService.mapTo(u, ApplicationUser.class));
	}

	@Override
	public ApplicationUserDTO findById(Long id) {
		ApplicationUser user  = applicationUserService.findById(id);
		return (user == null) ? null : beanMappingService.mapTo(user, ApplicationUserDTO.class);
	}

	@Override
	public ApplicationUserDTO findByEmail(String emial) {
		ApplicationUser user = applicationUserService.findByEmail(emial);
		return (user == null) ? null : beanMappingService.mapTo(user, ApplicationUserDTO.class);
	}

	@Override
	public List<ApplicationUserDTO> findAll() {
		return beanMappingService.mapTo(applicationUserService.findAll(), ApplicationUserDTO.class);
	}
	
	@Override
	public void setPassword(ApplicationUserDTO user, String password){
		applicationUserService.setPassword(user.getId(), password);
	}
}
