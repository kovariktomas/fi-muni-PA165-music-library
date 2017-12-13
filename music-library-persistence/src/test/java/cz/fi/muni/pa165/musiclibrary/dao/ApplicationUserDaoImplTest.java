package cz.fi.muni.pa165.musiclibrary.dao;

import cz.fi.muni.pa165.musiclibrary.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.musiclibrary.entity.ApplicationUser;
import cz.fi.muni.pa165.musiclibrary.exceptions.UserAlreadyExistsException;
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

import java.util.List;

/**
 * @author Kovarik Tomas
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ApplicationUserDaoImplTest extends AbstractTestNGSpringContextTests {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Autowired
	private ApplicationUserDao userDao;


	private static final int USERS_COUNT = 3;

	private ApplicationUser admin1;
	private ApplicationUser admin2;
	private ApplicationUser admin3;

	@BeforeMethod
	public void initializeEntities() {
		admin1 = new ApplicationUser();
		admin1.setName("admin1");
		admin1.setEmail("admin1@muni.cz");
		admin1.setPassHash("samplehash");
		admin1.setRole("admin");
		userDao.create(admin1);

		admin2 = new ApplicationUser();
		admin2.setName("admin2");
		admin2.setEmail("admin2@muni.cz");
		admin2.setPassHash("samplehash");
		admin2.setRole("admin");
		userDao.create(admin2);
		
		admin3 = new ApplicationUser();
		admin3.setName("admin3");
		admin3.setEmail("admin3@muni.cz");
		admin3.setPassHash("samplehash");
		admin3.setRole("admin");
		userDao.create(admin3);
	}

	@Test
	public void testCreate() {
		ApplicationUser admin4 = new ApplicationUser();
		admin4.setName("admin4");
		admin4.setEmail("admin4@muni.cz");
		admin4.setPassHash("samplehash");
		admin4.setRole("admin");
		userDao.create(admin4);

		Assert.assertEquals(USERS_COUNT + 1, userDao.findAll().size());
		Assert.assertSame(admin4, userDao.findById(admin4.getId()));
	}

	@Test(expectedExceptions = UserAlreadyExistsException.class,
		expectedExceptionsMessageRegExp = "User with this e-mail already exists.")
	public void testCreateWithDuplicateEmail() {
		ApplicationUser admin33 = new ApplicationUser();
		admin33.setName("admin1");
		admin33.setEmail("admin1@muni.cz");
		admin33.setPassHash("samplehash");
		admin33.setRole("admin");
		userDao.create(admin33);
	}

	@Test
	public void testDelete() {
		userDao.delete(admin3);
		Assert.assertEquals(USERS_COUNT - 1, userDao.findAll().size());
	}

	@Test
	public void testUpdate() {
		admin2.setName("admin22");
		userDao.update(admin2);

		Assert.assertEquals(USERS_COUNT, userDao.findAll().size());
		Assert.assertSame(admin2, userDao.findById(admin2.getId()));
	}

	@Test
	public void testUpdateUnchanged() {
		userDao.update(admin2);

		Assert.assertEquals(USERS_COUNT, userDao.findAll().size());
		Assert.assertSame(admin2, userDao.findById(admin2.getId()));
	}


	@Test
	public void testFindById() {
		Assert.assertSame(admin2, userDao.findById(admin2.getId()));
	}

	@Test
	public void testFindByNonexistentId() {
		Assert.assertEquals(null, userDao.findById(999L));
	}

	@Test
	public void testFindByEmail() {
		Assert.assertSame(admin2, userDao.findByEmail(admin2.getEmail()));
	}

	@Test
	public void testFindAll() {
		List<ApplicationUser> genres = userDao.findAll();
		Assert.assertEquals(USERS_COUNT, genres.size());
		Assert.assertTrue(genres.contains(admin1));
		Assert.assertTrue(genres.contains(admin2));
		Assert.assertTrue(genres.contains(admin3));
	}
}