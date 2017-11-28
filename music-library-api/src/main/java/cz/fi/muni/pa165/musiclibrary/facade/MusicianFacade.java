package cz.fi.muni.pa165.musiclibrary.facade;

import cz.fi.muni.pa165.musiclibrary.dto.MusicianCreateDTO;
import cz.fi.muni.pa165.musiclibrary.dto.MusicianDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jan-Sebastian Fabík
 */
@Service
public interface MusicianFacade {

	void create(MusicianCreateDTO musician);

	void update(MusicianDTO musician);

	void delete(Long id);

	MusicianDTO findById(Long id);

	List<MusicianDTO> findByName(String query);

	List<MusicianDTO> findAll();
}
