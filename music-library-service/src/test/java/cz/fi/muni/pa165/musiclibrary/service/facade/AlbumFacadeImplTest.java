package cz.fi.muni.pa165.musiclibrary.service.facade;

import cz.fi.muni.pa165.musiclibrary.dto.AlbumDTO;
import cz.fi.muni.pa165.musiclibrary.dto.GenreDTO;
import cz.fi.muni.pa165.musiclibrary.dto.MusicianDTO;
import cz.fi.muni.pa165.musiclibrary.dto.SongDTO;
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
import java.util.List;

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
	private Musician daya;
	private Genre jazz;
	private Genre pop;
	private Song delicate;

	private AlbumDTO albumDTO22;
	private AlbumDTO albumDTOReputation;

	private GenreDTO jazzDTO;
	private GenreDTO popDTO;
	private SongDTO delicateDTO;
	private MusicianDTO dayaDTO;
	private MusicianDTO taylorDTO;

	@BeforeMethod
	public void setUp() {
		taylor = new Musician();
		taylor.setName("Taylor Swift");
		em.persist(taylor);

		daya = new Musician();
		daya.setName("Daya");
		em.persist(daya);

		jazz = new Genre();
		jazz.setName("jazz");
		em.persist(jazz);

		pop = new Genre();
		pop.setName("pop");
		em.persist(pop);

		delicate = new Song();
		delicate.setTitle("Delicate");
		delicate.setPosition(5);
		delicate.setGenre(jazz);
		delicate.setMusician(taylor);
		em.persist(delicate);

		album22 = new Album();
		album22.setTitle("22");
		album22.addSong(delicate);
		em.persist(album22);

		albumReputation = new Album();
		albumReputation.setTitle("Reputation");
		em.persist(albumReputation);

		albumDTO22 = new AlbumDTO();
		albumDTO22.setId(album22.getId());
		albumDTO22.setTitle(album22.getTitle());

		albumDTOReputation = new AlbumDTO();
		albumDTOReputation.setId(albumReputation.getId());
		albumDTOReputation.setTitle(albumReputation.getTitle());

		taylorDTO = new MusicianDTO();
		taylorDTO.setId(taylor.getId());
		taylorDTO.setName(taylor.getName());

		jazzDTO = new GenreDTO();
		jazzDTO.setId(jazz.getId());
		jazzDTO.setName(jazz.getName());
	}

	@Test
	public void testCreate() {
		albumFacade.create(albumDTO22);
		List<Album> albums = em.createQuery("SELECT a FROM Album a", Album.class).getResultList();
		Assert.assertEquals(1, albums.size());
		Assert.assertEquals(albumDTO22.getTitle(), albums.get(0).getTitle());
	}

	@Test
	public void testUpdate() {
		String oldName = albumDTO22.getTitle();
		albumFacade.create(albumDTO22);
		albumDTO22.setTitle("aaaa");
		albumFacade.update(albumDTO22);
		AlbumDTO updatedDto = albumFacade.findById(albumDTO22.getId());
		Assert.assertNotEquals(oldName, updatedDto.getTitle());
		Assert.assertEquals(albumDTO22, updatedDto);
		Assert.assertEquals(albumDTO22.getTitle(), updatedDto.getTitle());
	}

	@Test
	public void testRemove() {
		albumFacade.create(albumDTO22);
		albumFacade.remove(albumDTO22);
		List<AlbumDTO> result = albumFacade.findAll();
		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testFindById() {
		albumFacade.create(albumDTO22);
		albumFacade.create(albumDTOReputation);
		Assert.assertEquals(albumFacade.findById(albumDTO22.getId()), albumDTO22);
		Assert.assertEquals(albumFacade.findById(albumDTOReputation.getId()), albumDTOReputation);
	}

	@Test
	public void testFindByMusician() {
		albumFacade.create(albumDTO22);
		Assert.assertEquals(albumFacade.findByMusician(taylorDTO).get(0), albumDTO22);
	}

	@Test
	public void testFindByGenre() {
		albumFacade.create(albumDTO22);
		Assert.assertEquals(albumFacade.findByGenre(jazzDTO).get(0), albumDTO22);
	}

	@Test
	public void testFindByTitle() {
		albumFacade.create(albumDTOReputation);
		Assert.assertEquals(albumDTOReputation, albumFacade.findByTitle(albumDTOReputation.getTitle()).get(0));
	}

	@Test
	public void testFindAll() {
		albumFacade.create(albumDTO22);
		albumFacade.create(albumDTOReputation);
		List<AlbumDTO> result = albumFacade.findAll();
		Assert.assertEquals(2, result.size());
		Assert.assertEquals(result.get(0), albumDTO22);
		Assert.assertEquals(result.get(1), albumDTOReputation);
	}
}
