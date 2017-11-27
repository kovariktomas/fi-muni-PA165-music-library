package cz.fi.muni.pa165.musiclibrary.facade;

import cz.fi.muni.pa165.musiclibrary.dto.MusicianDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jan-Sebastian Fabík
 */
@Service
public interface MusicianFacade {

	void create(MusicianDTO musician);

	void update(MusicianDTO musician);

	void delete(MusicianDTO musician);

	MusicianDTO findById(Long id);

	List<MusicianDTO> findByName(String query);

	List<MusicianDTO> findAll();
}
