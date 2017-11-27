package cz.fi.muni.pa165.musiclibrary.facade;

import cz.fi.muni.pa165.musiclibrary.dto.GenreDTO;
import cz.fi.muni.pa165.musiclibrary.dto.MusicianDTO;
import cz.fi.muni.pa165.musiclibrary.dto.SongDTO;

import java.util.List;

/**
 * @author David
 */
public interface SongFacade {

	void create(SongDTO song);

	void delete(SongDTO song);

	SongDTO findById(Long id);

	List<SongDTO> findByMusician(MusicianDTO musician);

	List<SongDTO> findByGenre(GenreDTO genre);

	List<SongDTO> findByTitle(String titlePattern);

	List<SongDTO> findAll();
}
