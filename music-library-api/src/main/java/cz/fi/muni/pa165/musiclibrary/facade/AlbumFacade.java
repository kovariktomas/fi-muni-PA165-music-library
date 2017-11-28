package cz.fi.muni.pa165.musiclibrary.facade;

import cz.fi.muni.pa165.musiclibrary.dto.AlbumCreateDTO;
import cz.fi.muni.pa165.musiclibrary.dto.AlbumDTO;
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
	void create(AlbumCreateDTO album);

	/**
	 * Update existing album data transfer object.
	 *
	 * @param album to be updated
	 */
	void update(AlbumDTO album);

	/**
	 * Remove existing album data transfer object.
	 *
	 * @param id the ID of the album to be removed
	 */
	void delete(Long id);

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
	 * @param musicianId the ID of the musician
	 * @return list of album dto with given musician
	 */
	List<AlbumDTO> findByMusician(Long musicianId);

	/**
	 * Finds list of Album data transfer object that contain songs of given genre.
	 *
	 * @param genreId the ID of the genre
	 * @return list of album dtos with songs of given genre
	 */
	List<AlbumDTO> findByGenre(Long genreId);

	/**
	 * Finds list of Album data objects with a title matching the given search query.
	 *
	 * @param query the search query
	 * @return list of album dtos with given title pattern
	 */
	List<AlbumDTO> findByTitle(String query);

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
