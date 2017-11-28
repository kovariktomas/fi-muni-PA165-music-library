package cz.fi.muni.pa165.musiclibrary.service.facade;

import cz.fi.muni.pa165.musiclibrary.dto.MusicianCreateDTO;
import cz.fi.muni.pa165.musiclibrary.dto.MusicianDTO;
import cz.fi.muni.pa165.musiclibrary.entity.Musician;
import cz.fi.muni.pa165.musiclibrary.facade.MusicianFacade;
import cz.fi.muni.pa165.musiclibrary.service.config.ServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Jan-Sebastian Fabík
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class MusicianFacadeImplTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private MusicianFacade musicianFacade;

	@PersistenceContext
	private EntityManager em;

	@Test
	public void testCreate() {
		MusicianCreateDTO musicianCreateDTO = new MusicianCreateDTO();
		musicianCreateDTO.setName("Parov Stelar");

		musicianFacade.create(musicianCreateDTO);

		List<Musician> musicians = getAllMusicians();
		Assert.assertEquals(1, musicians.size());
		Assert.assertEquals("Parov Stelar", musicians.get(0).getName());
	}

	@Test
	public void testUpdate() {
		Musician musician = createSampleMusician("Marcus Füreder");

		MusicianDTO musicianDTO = new MusicianDTO();
		musicianDTO.setId(musician.getId());
		musicianDTO.setName("Parov Stelar");
		musicianFacade.update(musicianDTO);

		List<Musician> musicians = getAllMusicians();
		Assert.assertEquals(1, musicians.size());
		Assert.assertEquals("Parov Stelar", musicians.get(0).getName());
	}

	@Test
	public void testDelete() {
		Musician musician = createSampleMusician("Parov Stelar");

		MusicianDTO musicianDTO = new MusicianDTO();
		musicianDTO.setId(musician.getId());
		musicianDTO.setName("Parov Stelar");
		musicianFacade.delete(musicianDTO);

		List<Musician> musicians = getAllMusicians();
		Assert.assertEquals(0, musicians.size());
	}

	@Test
	public void testFindById() {
		Musician musician = createSampleMusician("Parov Stelar");

		MusicianDTO musicianDTO = musicianFacade.findById(musician.getId());

		Assert.assertEquals(musician.getId(), musicianDTO.getId());
		Assert.assertEquals("Parov Stelar", musicianDTO.getName());
	}

	@Test
	public void testFindNonexistentById() {
		MusicianDTO musicianDTO = musicianFacade.findById(1L);

		Assert.assertEquals(null, musicianDTO);
	}

	@Test
	public void testFindByName() {
		Musician musician1 = createSampleMusician("Red Hot Chilli Peppers");
		Musician musician2 = createSampleMusician("Alabama Red Peppers");
		createSampleMusician("Red Velvet");

		List<MusicianDTO> musicianDTOs = musicianFacade.findByName("red peppers");

		Assert.assertEquals(2, musicianDTOs.size());
		Assert.assertEquals(musician1.getId(), musicianDTOs.get(0).getId());
		Assert.assertEquals(musician1.getName(), musicianDTOs.get(0).getName());
		Assert.assertEquals(musician2.getId(), musicianDTOs.get(1).getId());
		Assert.assertEquals(musician2.getName(), musicianDTOs.get(1).getName());
	}

	@Test
	public void testFindByNameWithEmptyQuery() {
		Musician musician1 = createSampleMusician("Parov Stelar");
		Musician musician2 = createSampleMusician("Caravan Palace");

		List<MusicianDTO> musicianDTOs = musicianFacade.findByName("");

		Assert.assertEquals(2, musicianDTOs.size());
		Assert.assertEquals(musician1.getId(), musicianDTOs.get(0).getId());
		Assert.assertEquals(musician1.getName(), musicianDTOs.get(0).getName());
		Assert.assertEquals(musician2.getId(), musicianDTOs.get(1).getId());
		Assert.assertEquals(musician2.getName(), musicianDTOs.get(1).getName());
	}

	@Test
	public void testFindAll() {
		Musician musician1 = createSampleMusician("Parov Stelar");
		Musician musician2 = createSampleMusician("Caravan Palace");

		List<MusicianDTO> musicianDTOs = musicianFacade.findAll();

		Assert.assertEquals(2, musicianDTOs.size());
		Assert.assertEquals(musician1.getId(), musicianDTOs.get(0).getId());
		Assert.assertEquals(musician1.getName(), musicianDTOs.get(0).getName());
		Assert.assertEquals(musician2.getId(), musicianDTOs.get(1).getId());
		Assert.assertEquals(musician2.getName(), musicianDTOs.get(1).getName());
	}

	private Musician createSampleMusician(String name) {
		Musician musician = new Musician();
		musician.setName(name);
		em.persist(musician);
		return musician;
	}

	private List<Musician> getAllMusicians() {
		return em.createQuery("SELECT m FROM Musician m", Musician.class).getResultList();
	}
}
