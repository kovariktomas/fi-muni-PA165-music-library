package cz.fi.muni.pa165.musiclibrary.service.facade;

import cz.fi.muni.pa165.musiclibrary.dto.*;
import cz.fi.muni.pa165.musiclibrary.entity.Album;
import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.entity.Musician;
import cz.fi.muni.pa165.musiclibrary.entity.Song;
import cz.fi.muni.pa165.musiclibrary.facade.AlbumFacade;
import cz.fi.muni.pa165.musiclibrary.service.config.ServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Tests for AlbumFacadeImpl
 *
 * @author Iva Liberova
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class AlbumFacadeImplTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private AlbumFacade albumFacade;

	@PersistenceContext
	private EntityManager em;

	private Album album22;
	private Album albumReputation;
	private Musician taylor;
	private Genre jazz;
	private Song delicate;

	@BeforeMethod
	public void setUp() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2016, 1, 1);
		Date fabricatedTime = calendar.getTime();

		album22 = new Album();
		album22.setTitle("22");
		album22.setReleaseDate(fabricatedTime);

		albumReputation = new Album();
		albumReputation.setTitle("Reputation");
		albumReputation.setReleaseDate(fabricatedTime);

		taylor = new Musician();
		taylor.setName("Taylor Swift");
		em.persist(taylor);

		jazz = new Genre();
		jazz.setName("jazz");
		em.persist(jazz);

		delicate = new Song();
		delicate.setTitle("Delicate");
		delicate.setMusician(taylor);
		delicate.setGenre(jazz);
		delicate.setAlbum(album22);
		delicate.setBitrate(320);
		delicate.setPosition(0);
	}

	@Test
	public void testCreate() {
		albumFacade.create(createAlbumCreateDTO(album22));
		List<Album> albums = em.createQuery("SELECT a FROM Album a", Album.class).getResultList();
		Assert.assertEquals(1, albums.size());
		Assert.assertEquals(album22.getTitle(), albums.get(0).getTitle());
	}

	@Test
	public void testUpdate() {
		em.persist(album22);
		String oldName = album22.getTitle();

		AlbumDTO albumDTO = createAlbumDTO(album22);
		albumDTO.setTitle("aaaa");
		albumFacade.update(albumDTO);

		Assert.assertEquals(albumDTO, albumFacade.findById(album22.getId()));
	}

	@Test
	public void testDelete() {
		em.persist(album22);
		albumFacade.delete(album22.getId());
		List<AlbumDTO> result = albumFacade.findAll();
		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testFindById() {
		em.persist(albumReputation);
		em.persist(album22);

		Assert.assertEquals(createAlbumDTO(albumReputation), albumFacade.findById(albumReputation.getId()));
		Assert.assertEquals(createAlbumDTO(album22), albumFacade.findById(album22.getId()));
	}

	@Test
	public void testFindByMusician() {
		em.persist(album22);
		em.persist(delicate);

		List<AlbumDTO> albumDTOs = albumFacade.findByMusician(taylor.getId());
		Assert.assertEquals(1, albumDTOs.size());
		Assert.assertTrue(albumDTOs.contains(createAlbumDTO(album22)));
	}

	@Test
	public void testFindByGenre() {
		em.persist(album22);
		em.persist(delicate);

		List<AlbumDTO> albumDTOs = albumFacade.findByGenre(jazz.getId());
		Assert.assertEquals(1, albumDTOs.size());
		Assert.assertTrue(albumDTOs.contains(createAlbumDTO(album22)));
	}

	@Test
	public void testFindByTitle() {
		em.persist(albumReputation);

		List<AlbumDTO> albumDTOs = albumFacade.findByTitle("rep");
		Assert.assertEquals(1, albumDTOs.size());
		Assert.assertTrue(albumDTOs.contains(createAlbumDTO(albumReputation)));
	}

	@Test
	public void testFindAll() {
		em.persist(album22);
		em.persist(albumReputation);

		List<AlbumDTO> result = albumFacade.findAll();
		Assert.assertEquals(2, result.size());
		Assert.assertTrue(result.contains(createAlbumDTO(album22)));
		Assert.assertTrue(result.contains(createAlbumDTO(albumReputation)));
	}

	private AlbumCreateDTO createAlbumCreateDTO(Album album) {
		AlbumCreateDTO albumCreateDTO = new AlbumCreateDTO();
		albumCreateDTO.setReleaseDate(album.getReleaseDate());
		albumCreateDTO.setTitle(album.getTitle());
		albumCreateDTO.setCommentary(album.getCommentary());
		albumCreateDTO.setAlbumArt(album.getAlbumArt());
		return albumCreateDTO;
	}

	private AlbumDTO createAlbumDTO(Album album) {
		AlbumDTO albumDTO = new AlbumDTO();
		albumDTO.setId(album.getId());
		albumDTO.setReleaseDate(album.getReleaseDate());
		albumDTO.setTitle(album.getTitle());
		albumDTO.setCommentary(album.getCommentary());
		albumDTO.setAlbumArt(album.getAlbumArt());
		return albumDTO;
	}
}
