package cz.fi.muni.pa165.musiclibrary.service.facade;

import cz.fi.muni.pa165.musiclibrary.dto.MusicianDTO;
import cz.fi.muni.pa165.musiclibrary.entity.Musician;
import cz.fi.muni.pa165.musiclibrary.facade.MusicianFacade;
import cz.fi.muni.pa165.musiclibrary.service.BeanMappingService;
import cz.fi.muni.pa165.musiclibrary.service.MusicianService;
import cz.fi.muni.pa165.musiclibrary.utils.SearchHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MusicianFacadeImpl implements MusicianFacade {

	@Autowired
	private MusicianService musicianService;

	@Autowired
	private BeanMappingService beanMappingService;

	public void create(MusicianDTO musician) {
		musicianService.create(beanMappingService.mapTo(musician, Musician.class));
	}

	public void update(MusicianDTO musician) {
		musicianService.update(beanMappingService.mapTo(musician, Musician.class));
	}

	public void delete(MusicianDTO musician) {
		musicianService.delete(beanMappingService.mapTo(musician, Musician.class));
	}

	public MusicianDTO findById(Long id) {
		Musician musician = musicianService.findById(id);
		return (musician != null) ? beanMappingService.mapTo(musician, MusicianDTO.class) : null;
	}

	public List<MusicianDTO> findByName(String query) {
		List<String> patterns = SearchHelper.splitSearchQuery(query);
		List<Musician> musicians = musicianService.findByName(patterns);
		return beanMappingService.mapTo(musicians, MusicianDTO.class);
	}

	public List<MusicianDTO> findAll() {
		List<Musician> musicians = musicianService.findAll();
		return beanMappingService.mapTo(musicians, MusicianDTO.class);
	}
}
