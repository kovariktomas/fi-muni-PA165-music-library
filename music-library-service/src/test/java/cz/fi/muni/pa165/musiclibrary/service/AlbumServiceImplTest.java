package cz.fi.muni.pa165.musiclibrary.service;

import cz.fi.muni.pa165.musiclibrary.dao.AlbumDao;
import cz.fi.muni.pa165.musiclibrary.entity.Album;
import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.entity.Musician;
import cz.fi.muni.pa165.musiclibrary.entity.Song;
import cz.fi.muni.pa165.musiclibrary.service.config.ServiceConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for AlbumServiceImpl
 *
 * @author Iva Liberova
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class AlbumServiceImplTest extends AbstractTestNGSpringContextTests {
	@Mock
	private AlbumDao albumDao;

	@Mock
	private TimeService timeService;

	@Autowired
	@InjectMocks
	private AlbumService albumService;

	private Album album22;
	private Album reputation;
	private Musician taylor;
	private Musician daya;
	private Genre jazz;
	private Genre pop;
	private Song delicate;
	private Song dress;

	@BeforeClass
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeMethod
	public void setUp() {
		album22 = new Album();
		album22.setTitle("22");

		reputation = new Album();
		reputation.setTitle("Reputation");

		taylor = new Musician();
		taylor.setName("Taylor Swift");

		daya = new Musician();
		daya.setName("Daya");

		jazz = new Genre();
		jazz.setName("jazz");

		pop = new Genre();
		pop.setName("pop");

		delicate = new Song();
		delicate.setTitle("Delicate");
		delicate.setPosition(5);
		delicate.setGenre(jazz);
		delicate.setMusician(taylor);

		dress = new Song();
		dress.setTitle("Dress");
		dress.setPosition(12);
		dress.setGenre(jazz);
		dress.setMusician(taylor);
	}

	@Test
	public void testCreate() {
		albumService.create(album22);
		verify(albumDao).create(album22);
	}

	@Test
	public void testUpdate() {
		albumService.update(album22);
		verify(albumDao).update(album22);
	}

	@Test
	public void testRemove() {
		albumService.remove(album22);
		verify(albumDao).remove(album22);
	}

	@Test
	public void testFindById() {
		when(albumDao.findById(1L)).thenReturn(album22);
		Assert.assertSame(album22, albumService.findById(1L));
	}

	@Test
	public void testFindByNIdNotExist() {
		when(albumDao.findById(1L)).thenReturn(null);
		Assert.assertEquals(null, albumService.findById(1L));
	}

	@Test
	public void testFindByMusician() {
		List<Album> albums = Arrays.asList(album22, reputation);
		when(albumDao.findByMusician(taylor)).thenReturn(albums);
		Assert.assertEquals(albums, albumService.findByMusician(taylor));
	}

	@Test
	public void testFindByMusicianNotUsed() {
		when(albumDao.findByMusician(daya)).thenReturn(null);
		Assert.assertEquals(null, albumService.findByMusician(daya));
	}

	@Test
	public void testFindByGenre() {
		List<Album> albums = Arrays.asList(album22, reputation);
		when(albumDao.findByGenre(jazz)).thenReturn(albums);
		Assert.assertEquals(albums, albumService.findByGenre(jazz));
	}

	@Test
	public void testFindByGenreNotUsed() {
		when(albumDao.findByGenre(pop)).thenReturn(null);
		Assert.assertEquals(null, albumService.findByGenre(pop));
	}

	@Test
	public void testFindByTitle() {
		List<Album> albums = Arrays.asList(album22, reputation);
		when(albumDao.findByTitle(Arrays.asList("%rep%", "%on%"))).thenReturn(albums);
		Assert.assertSame(albums, albumService.findByTitle("rep on"));
	}

	@Test
	public void testFindAll() {
		List<Album> albums = Arrays.asList(album22, reputation);
		when(albumDao.findAll()).thenReturn(albums);
		Assert.assertSame(albums, albumService.findAll());
	}

	@Test
	public void testGetAlbumsFromLastMonth() {
		List<Album> albums = Arrays.asList(album22);
		Calendar calendar = Calendar.getInstance();
		calendar.set(2016, 1, 1);
		Date fabricatedTime = calendar.getTime();
		calendar.add(Calendar.MONTH, -1);
		Date monthBeforeFabricatedTime = calendar.getTime();
		album22.setReleaseDate(monthBeforeFabricatedTime);

		when(albumDao.getAlbumsReleasedBetween(any(Date.class), any(Date.class))).thenReturn(albums);
		when(timeService.getCurrentTime()).thenReturn(fabricatedTime);

		Assert.assertSame(albums, albumService.getAlbumsFromLastMonth());
	}

	@Test
	public void testGetAlbumsFromLastMonthNoAlbum() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2016, 1, 1);
		Date fabricatedTime = calendar.getTime();
		calendar.add(Calendar.MONTH, -2);
		Date twoMonthsBeforeFabricatedTime = calendar.getTime();
		album22.setReleaseDate(twoMonthsBeforeFabricatedTime);

		when(albumDao.getAlbumsReleasedBetween(any(Date.class), any(Date.class))).thenReturn(null);
		when(timeService.getCurrentTime()).thenReturn(fabricatedTime);

		Assert.assertSame(null, albumService.getAlbumsFromLastMonth());
	}

	@Test
	public void testGetAlbumsReleasedBetween() {
		List<Album> albums = Arrays.asList(album22);
		Calendar calendar = Calendar.getInstance();
		calendar.set(2016, 1, 1);
		Date fabricatedTime = calendar.getTime();
		calendar.add(Calendar.MONTH, -1);
		Date monthBeforeFabricatedTime = calendar.getTime();
		album22.setReleaseDate(monthBeforeFabricatedTime);

		when(albumDao.getAlbumsReleasedBetween(any(Date.class), any(Date.class))).thenReturn(albums);
		when(timeService.getCurrentTime()).thenReturn(fabricatedTime);

		Assert.assertSame(albums, albumService.getAlbumsReleasedBetween(monthBeforeFabricatedTime, fabricatedTime));
	}

	@Test
	public void testGetAlbumsReleasedBetweenNoAlbum() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2016, 1, 1);
		Date fabricatedTime = calendar.getTime();
		calendar.add(Calendar.MONTH, -1);
		Date monthBeforeFabricatedTime = calendar.getTime();
		calendar.add(Calendar.MONTH, -1);
		Date twoMonthsBeforeFabricatedTime = calendar.getTime();
		album22.setReleaseDate(twoMonthsBeforeFabricatedTime);

		when(albumDao.getAlbumsReleasedBetween(any(Date.class), any(Date.class))).thenReturn(null);
		when(timeService.getCurrentTime()).thenReturn(fabricatedTime);

		Assert.assertSame(null, albumService.getAlbumsReleasedBetween(monthBeforeFabricatedTime, fabricatedTime));
	}
}
