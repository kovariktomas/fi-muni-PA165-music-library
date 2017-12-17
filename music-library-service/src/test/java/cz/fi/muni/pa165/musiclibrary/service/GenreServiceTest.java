package cz.fi.muni.pa165.musiclibrary.service;

import cz.fi.muni.pa165.musiclibrary.dao.GenreDao;
import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.service.config.ServiceConfiguration;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Kovarik Tomas
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class GenreServiceTest extends AbstractTestNGSpringContextTests {

	@Mock
	private GenreDao genreDao;

	private GenreService genreService;

	@BeforeClass
	public void setUpClass() {
		MockitoAnnotations.initMocks(this);
		genreService = new GenreServiceImpl(genreDao);
	}

	@Test
	public void testCreate() {
		Genre genre = createMockGenre("Rock");
		genreService.create(genre);
		verify(genreDao).create(genre);
	}

	@Test
	public void testUpdate() {
		Genre genre = createMockGenre("Rock");
		genreService.update(genre);
		verify(genreDao).update(genre);
	}

	@Test
	public void testDelete() {
		Genre genre = createMockGenre("Rock");
		genreService.delete(genre);
		verify(genreDao).delete(genre);
	}

	@Test
	public void testFindById() {
		Genre genre = createMockGenre("Rock");
		when(genreDao.findById(1L)).thenReturn(genre);
		Assert.assertSame(genre, genreService.findById(1L));
	}

	@Test
	public void testFindNonexistentById() {
		when(genreDao.findById(1L)).thenReturn(null);
		Assert.assertEquals(null, genreService.findById(1L));
	}

	@Test
	public void testFindByName() {
		Genre genre1 = createMockGenre("Rock");
		Genre genre2 = createMockGenre("Classic Rock");

		List<Genre> genres = Arrays.asList(genre1, genre2);

		when(genreDao.findByName(Arrays.asList("%rock%"))).thenReturn(genres);

		Assert.assertSame(genres, genreService.findByName("rock"));
	}

	@Test
	public void testFindByNameWithEmptyQuery() {
		Genre genre1 = createMockGenre("Rock");
		Genre genre2 = createMockGenre("Classic Rock");

		List<Genre> genres = Arrays.asList(genre1, genre2);

		when(genreDao.findByName(new ArrayList<>())).thenReturn(genres);

		Assert.assertSame(genres, genreService.findByName(""));
	}

	@Test
	public void testFindAll() {

		Genre genre1 = createMockGenre("Rock");
		Genre genre2 = createMockGenre("Classic Rock");

		List<Genre> genres = Arrays.asList(genre1, genre2);

		when(genreDao.findAll()).thenReturn(genres);

		Assert.assertSame(genres, genreService.findAll());
	}

	private Genre createMockGenre(String name) {
		Genre genre = new Genre();
		genre.setName(name);
		return genre;
	}

}
