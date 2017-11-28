package cz.fi.muni.pa165.musiclibrary.dao;

import cz.fi.muni.pa165.musiclibrary.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.musiclibrary.entity.Album;
import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.entity.Musician;
import cz.fi.muni.pa165.musiclibrary.entity.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;

/**
 * @author David Koncak
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class AlbumDaoTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private AlbumDao albumDao;

	@Autowired
	private SongDao songDao;

	@Autowired
	private MusicianDao musicianDao;

	@Autowired
	private GenreDao genreDao;

	private Song song1;

	private Song song2;
	private Musician m;

	private Album a;

	private Musician m1;

	@BeforeMethod
	public void createAlbum() throws ParseException {
		a = new Album();

		song1 = mock(Song.class);
		song2 = mock(Song.class);

		m = new Musician();
		m.setName("musikant");
		musicianDao.create(m);

		a.addSong(song1);
		a.addSong(song2);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = sdf.parse("21/12/2012");
		a.setTitle("Testovaci album");
		a.setReleaseDate(d);
		a.setCommentary("skvele album 9/11");
		albumDao.create(a);
	}

	@Test
	public void testNonExistentReturnsNull() {
		Assert.assertNull(albumDao.findById(11L));
	}


	@Test
	public void testCreate() {
		Assert.assertEquals(albumDao.findById(a.getId()).getTitle(), "Testovaci album");
	}

	@Test
	public void testRemove() {
		Assert.assertNotNull(albumDao.findById(a.getId()));
		albumDao.remove(a);
		Assert.assertNull(albumDao.findById(a.getId()));
	}

	@Test
	public void testUpdate() {
		a.setTitle("Upravene");
		albumDao.update(a);

		Assert.assertEquals(albumDao.findById(a.getId()).getTitle(), "Upravene");
	}

	@Test
	public void testFind() {
		Album found = albumDao.findById(a.getId());
		Assert.assertEquals(found.getTitle(), "Testovaci album");
		Assert.assertEquals(found.getSongs().size(), 2);
	}

	@Test
	public void testFindByMusician() {
		m1 = new Musician();
		m1.setName("Onassis");
		Genre g = new Genre();
		g.setName("Genre");
		genreDao.create(g);
		musicianDao.create(m1);
		Song s = new Song();
		s.setAlbum(a);
		s.setTitle("Song");
		s.setBitrate(345);
		s.setPosition(1);
		s.setMusician(m1);
		s.setGenre(g);
		songDao.create(s);
		a.addSong(s);

		albumDao.findByMusician(m1);
		Assert.assertEquals(albumDao.findByMusician(m1).size(), 1);
		Assert.assertEquals(albumDao.findByMusician(m1).get(0).getTitle(), "Testovaci album");
		Assert.assertEquals(albumDao.findByMusician(m1).get(0).getSongs().size(), 3);
	}

	@Test
	public void testFindByGenre() {
		m1 = new Musician();
		m1.setName("Onassis");
		Genre g = new Genre();
		g.setName("Genre");
		genreDao.create(g);
		musicianDao.create(m1);
		Song s = new Song();
		s.setAlbum(a);
		s.setTitle("Song");
		s.setBitrate(345);
		s.setPosition(1);
		s.setMusician(m1);
		s.setGenre(g);
		songDao.create(s);
		a.addSong(s);

		albumDao.findByGenre(g);

		Assert.assertEquals(albumDao.findByMusician(m1).size(), 1);
		Assert.assertEquals(albumDao.findByMusician(m1).get(0).getTitle(), "Testovaci album");
		Assert.assertEquals(albumDao.findByMusician(m1).get(0).getSongs().size(), 3);
	}

	@Test
	public void testFindByTitle() {
		List<String> pattern = new ArrayList<>();
		pattern.add("Testovaci album");
		List<Album> albums = albumDao.findByTitle(pattern);
		Assert.assertEquals(albums.size(), 1);
		Assert.assertTrue(albums.contains(a));
	}

	@Test
	public void testWithNonExistingTitle() {
		List<String> pattern = new ArrayList<>();
		pattern.add("avdsfa");
		List<Album> albums = albumDao.findByTitle(pattern);
		Assert.assertEquals(albums.size(), 0);
		Assert.assertTrue(!albums.contains(a));
	}

	@Test
	public void testFindAll() {
		List<Album> albums = albumDao.findAll();
		Assert.assertEquals(albums.size(), 1);
		Assert.assertEquals(albums.get(0).getTitle(), "Testovaci album");
	}

	@Test
	public void testGetAlbumsReleasedBetweenNoAlbum() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2016, Calendar.FEBRUARY, 1);
		Date dateStart = calendar.getTime();
		calendar.set(2016, Calendar.MARCH, 25);
		Date dateEnd = calendar.getTime();
		Assert.assertEquals(albumDao.getAlbumsReleasedBetween(dateStart, dateEnd).size(), 0);
	}

	@Test
	public void testGetAlbumsReleasedBetween() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2016, Calendar.FEBRUARY, 1);
		Date dateStart = calendar.getTime();
		calendar.set(2016, Calendar.MARCH, 25);
		Date dateEnd = calendar.getTime();

		calendar.set(2016, Calendar.MARCH, 1);
		a.setReleaseDate(calendar.getTime());
		albumDao.update(a);
		Assert.assertEquals(albumDao.getAlbumsReleasedBetween(dateStart, dateEnd).size(), 1);

		a.setReleaseDate(dateStart);
		albumDao.update(a);
		Assert.assertEquals(albumDao.getAlbumsReleasedBetween(dateStart, dateEnd).size(), 1);

		a.setReleaseDate(dateEnd);
		albumDao.update(a);
		Assert.assertEquals(albumDao.getAlbumsReleasedBetween(dateStart, dateEnd).size(), 1);
	}
}
