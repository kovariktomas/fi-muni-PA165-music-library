package cz.fi.muni.pa165.musiclibrary.facade;

import cz.fi.muni.pa165.musiclibrary.dto.AlbumDTO;
import cz.fi.muni.pa165.musiclibrary.dto.GenreDTO;
import cz.fi.muni.pa165.musiclibrary.dto.MusicianDTO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Facade layer interface for Album
 *
 * @author Iva Liberova
 */
@Service
public interface AlbumFacade {
	/**
	 * Create album data transfer object.
	 *
	 * @param album to be created
	 */
	void create(AlbumDTO album);

	/**
	 * Update existing album data transfer object.
	 *
	 * @param album to be updated
	 */
	void update(AlbumDTO album) throws IllegalArgumentException;

	/**
	 * Remove existing album data transfer object.
	 *
	 * @param album to be removed
	 */
	void remove(AlbumDTO album) throws IllegalArgumentException;

	/**
	 * Finds Album data transfer object by id.
	 *
	 * @param id of album dto to be found
	 * @return album dto with given id
	 */
	AlbumDTO findById(Long id);

	/**
	 * Finds list of Album data transfer object that contain songs from given musician.
	 *
	 * @param musician to be found
	 * @return list of album dto with given musician
	 */
	List<AlbumDTO> findByMusician(MusicianDTO musician);

	/**
	 * Finds list of Album data transfer object that contain songs of given genre.
	 *
	 * @param genre to be found
	 * @return list of album dtos with songs of given genre
	 */
	List<AlbumDTO> findByGenre(GenreDTO genre);

	/**
	 * Finds list of Album data objects with given title pattern.
	 *
	 * @param titlePattern to be found
	 * @return list of album dtos with given title pattern
	 */
	List<AlbumDTO> findByTitle(String titlePattern);

	/**
	 * Finds all album data transfer objects.
	 *
	 * @return list of all album dtos
	 */
	List<AlbumDTO> findAll();

	/**
	 * Finds all albums that were released between given dates.
	 *
	 * @param startDate first valid date
	 * @param endDate   last valid date
	 * @return list of album dtos that were released in the interval
	 */
	List<AlbumDTO> getAlbumsReleasedBetween(Date startDate, Date endDate);

	/**
	 * Finds all albums that were released in last month.
	 *
	 * @return list of album dtos that were released in last month
	 */
	List<AlbumDTO> getAlbumsFromLastMonth();
}
