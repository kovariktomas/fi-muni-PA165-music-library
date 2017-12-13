package cz.fi.muni.pa165.musiclibrary.dao;

import cz.fi.muni.pa165.musiclibrary.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.exceptions.GenreAlreadyExistsException;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author Jan-Sebastian Fabík
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class GenreDaoImplTest extends AbstractTestNGSpringContextTests {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Autowired
	private GenreDao genreDao;


	private static final int GENRES_COUNT = 3;

	private Genre rockGenre;
	private Genre swingGenre;
	private Genre electroSwingGenre;

	@BeforeMethod
	public void initializeEntities() {
		rockGenre = new Genre();
		rockGenre.setName("Rock");
		genreDao.create(rockGenre);

		swingGenre = new Genre();
		swingGenre.setName("Swing");
		genreDao.create(swingGenre);

		electroSwingGenre = new Genre();
		electroSwingGenre.setName("Electro Swing");
		genreDao.create(electroSwingGenre);
	}

	@Test
	public void testCreate() {
		Genre popGenre = new Genre();
		popGenre.setName("Pop");
		genreDao.create(popGenre);

		Assert.assertEquals(GENRES_COUNT + 1, genreDao.findAll().size());
		Assert.assertSame(popGenre, genreDao.findById(popGenre.getId()));
	}

	@Test(expectedExceptions = GenreAlreadyExistsException.class,
		expectedExceptionsMessageRegExp = "Genre with this name already exists.")
	public void testCreateWithDuplicateName() {
		Genre swingGenreCopy = new Genre();
		swingGenreCopy.setName("Swing");
		genreDao.create(swingGenreCopy);
	}

	@Test
	public void testDelete() {
		genreDao.delete(rockGenre);
		Assert.assertEquals(GENRES_COUNT - 1, genreDao.findAll().size());
	}

	@Test
	public void testUpdate() {
		rockGenre.setName("Rock 2");
		genreDao.update(rockGenre);

		Assert.assertEquals(GENRES_COUNT, genreDao.findAll().size());
		Assert.assertSame(rockGenre, genreDao.findById(rockGenre.getId()));
	}

	@Test
	public void testUpdateUnchanged() {
		genreDao.update(rockGenre);

		Assert.assertEquals(GENRES_COUNT, genreDao.findAll().size());
		Assert.assertSame(rockGenre, genreDao.findById(rockGenre.getId()));
	}


	@Test
	public void testFindById() {
		Assert.assertEquals(rockGenre, genreDao.findById(rockGenre.getId()));
	}

	@Test
	public void testFindByNonexistentId() {
		Assert.assertEquals(null, genreDao.findById(999L));
	}

	@Test
	public void testFindByName() {
		List<Genre> genres = genreDao.findByName(Arrays.asList("%Swing%"));
		Assert.assertEquals(2, genres.size());
		Assert.assertTrue(genres.contains(swingGenre));
		Assert.assertTrue(genres.contains(electroSwingGenre));
	}

	@Test
	public void testFindAll() {
		List<Genre> genres = genreDao.findAll();
		Assert.assertEquals(GENRES_COUNT, genres.size());
		Assert.assertTrue(genres.contains(rockGenre));
		Assert.assertTrue(genres.contains(swingGenre));
		Assert.assertTrue(genres.contains(electroSwingGenre));
	}
}
