package cz.fi.muni.pa165.musiclibrary.sampledata;

import cz.fi.muni.pa165.musiclibrary.entity.*;
import cz.fi.muni.pa165.musiclibrary.service.ApplicationUserService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

/**
 * Tests data loading.
 */
@ContextConfiguration(classes = {MusicLibraryWithSampleDataConfiguration.class})
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class SampleDataLoadingFacadeTest extends AbstractTestNGSpringContextTests {

	final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeTest.class);

	@Autowired
	private SampleDataLoadingFacade sampleDataLoadingFacade;

	@Autowired
	private ApplicationUserService applicationUserService;

	@PersistenceContext
	private EntityManager em;

	@Test
	public void loadData() throws IOException {
		sampleDataLoadingFacade.loadData();

		ApplicationUser user = em.createQuery("SELECT u FROM ApplicationUser u WHERE u.email = :email", ApplicationUser.class)
			.setParameter("email", "admin@example.com")
			.getSingleResult();

		Assert.assertNotNull(user);
		Assert.assertNotNull(user.getId());
		Assert.assertEquals("admin", user.getName());
		Assert.assertEquals("admin@example.com", user.getEmail());
		Assert.assertTrue(applicationUserService.verifyPassword(user.getId(), "password"));
		Assert.assertFalse(applicationUserService.verifyPassword(user.getId(), "invalid"));

		Musician musician = em.createQuery("SELECT m FROM Musician m WHERE m.name = :name", Musician.class)
			.setParameter("name", "Ed Sheeran")
			.getSingleResult();

		Assert.assertNotNull(musician);
		Assert.assertNotNull(musician.getId());
		Assert.assertEquals("Ed Sheeran", musician.getName());

		Album album = em.createQuery("SELECT a FROM Album a WHERE a.title = :title", Album.class)
			.setParameter("title", "÷")
			.getSingleResult();

		Assert.assertNotNull(album);
		Assert.assertNotNull(album.getId());
		Assert.assertEquals("2017-12-01", DateFormatUtils.format(album.getReleaseDate(), "yyyy-MM-dd", Locale.ENGLISH));
		Assert.assertEquals("÷", album.getTitle());
		Assert.assertEquals("", album.getCommentary());
		Assert.assertNotNull(album.getAlbumArt());

		Genre genre = em.createQuery("SELECT g FROM Genre g WHERE g.name = :name", Genre.class)
			.setParameter("name", "foo")
			.getSingleResult();

		Assert.assertNotNull(genre);
		Assert.assertNotNull(genre.getId());
		Assert.assertEquals("foo", genre.getName());

		Song song = em.createQuery("SELECT s FROM Song s WHERE s.title = :title", Song.class)
			.setParameter("title", "Perfect")
			.getSingleResult();

		Assert.assertNotNull(song);
		Assert.assertNotNull(song.getId());
		Assert.assertEquals(musician, song.getMusician());
		Assert.assertEquals(album, song.getAlbum());
		Assert.assertEquals(genre, song.getGenre());
		Assert.assertEquals("Perfect", song.getTitle());
		Assert.assertEquals(320, (int) song.getBitrate());
		Assert.assertEquals(0, (int) song.getPosition());
		Assert.assertEquals("", song.getCommentary());
	}

	private static Date toDate(int year, int month, int day) {
		return Date.from(LocalDate.of(year, month, day).atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
}
