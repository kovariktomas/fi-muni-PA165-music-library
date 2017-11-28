package cz.fi.muni.pa165.musiclibrary.service.facade;

import cz.fi.muni.pa165.musiclibrary.dto.GenreCreateDTO;
import cz.fi.muni.pa165.musiclibrary.dto.GenreDTO;
import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.facade.GenreFacade;
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
 * @author Kovarik Tomas
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class GenreFacadeImplTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private GenreFacade genreFacade;

	@PersistenceContext
	private EntityManager em;

	@Test
	public void testCreate() {
		GenreCreateDTO genreCreateDTO = new GenreCreateDTO();
		genreCreateDTO.setName("Rock");

		genreFacade.create(genreCreateDTO);

		List<Genre> genres = em.createQuery("SELECT g FROM Genre g", Genre.class).getResultList();
		Assert.assertEquals(1, genres.size());
		Assert.assertEquals("Rock", genres.get(0).getName());
	}

	@Test
	public void testUpdate() {
		Genre genre = new Genre();
		genre.setName("Clasic");
		em.persist(genre);

		GenreDTO genreDTO = new GenreDTO();
		genreDTO.setId(genre.getId());
		genreDTO.setName("Classic");
		genreFacade.update(genreDTO);

		List<Genre> genres = em.createQuery("SELECT g FROM Genre g", Genre.class).getResultList();
		Assert.assertEquals(1, genres.size());
		Assert.assertEquals("Classic", genres.get(0).getName());
	}

	@Test
	public void testDelete() {
		Genre genre = new Genre();
		genre.setName("Popular");
		em.persist(genre);

		genreFacade.delete(genre.getId());

		List<Genre> genres = em.createQuery("SELECT g FROM Genre g", Genre.class).getResultList();
		Assert.assertEquals(0, genres.size());
	}

	@Test
	public void testFindById() {
		Genre genre = new Genre();
		genre.setName("Classicc");
		em.persist(genre);

		GenreDTO genreDTO = genreFacade.findById(genre.getId());

		Assert.assertEquals(genre.getId(), genreDTO.getId());
		Assert.assertEquals("Classicc", genreDTO.getName());
	}

	@Test
	public void testFindNonexistentById() {
		GenreDTO genreDTO = genreFacade.findById(1L);

		Assert.assertEquals(null, genreDTO);
	}

	@Test
	public void testFindByName() {
		Genre genre1 = createSampleGenre("Rock test");
		Genre genre2 = createSampleGenre("Classic Rock");

		List<GenreDTO> genreDTOs = genreFacade.findByName("Rock");

		Assert.assertEquals(2, genreDTOs.size());
		Assert.assertEquals(genre1.getId(), genreDTOs.get(0).getId());
		Assert.assertEquals(genre1.getName(), genreDTOs.get(0).getName());
		Assert.assertEquals(genre2.getId(), genreDTOs.get(1).getId());
		Assert.assertEquals(genre2.getName(), genreDTOs.get(1).getName());
	}

	@Test
	public void testFindByNameWithEmptyQuery() {
		Genre genre1 = createSampleGenre("Rock");
		Genre genre2 = createSampleGenre("Classic Rock");

		List<GenreDTO> genreDTOs = genreFacade.findByName("");

		Assert.assertEquals(2, genreDTOs.size());
		Assert.assertEquals(genre1.getId(), genreDTOs.get(0).getId());
		Assert.assertEquals(genre1.getName(), genreDTOs.get(0).getName());
		Assert.assertEquals(genre2.getId(), genreDTOs.get(1).getId());
		Assert.assertEquals(genre2.getName(), genreDTOs.get(1).getName());
	}

	@Test
	public void testFindAll() {
		Genre genre1 = createSampleGenre("Rocks");
		Genre genre2 = createSampleGenre("Classic Rocks");

		List<GenreDTO> genreDTOs = genreFacade.findAll();

		Assert.assertEquals(2, genreDTOs.size());
		Assert.assertEquals(genre1.getId(), genreDTOs.get(0).getId());
		Assert.assertEquals(genre1.getName(), genreDTOs.get(0).getName());
		Assert.assertEquals(genre2.getId(), genreDTOs.get(1).getId());
		Assert.assertEquals(genre2.getName(), genreDTOs.get(1).getName());
	}

	private Genre createSampleGenre(String name) {
		Genre genre = new Genre();
		genre.setName(name);
		em.persist(genre);
		return genre;
	}
}