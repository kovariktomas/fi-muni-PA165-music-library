package cz.fi.muni.pa165.musiclibrary.service;

import cz.fi.muni.pa165.musiclibrary.dao.SongDao;
import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.entity.Musician;
import cz.fi.muni.pa165.musiclibrary.entity.Song;
import cz.fi.muni.pa165.musiclibrary.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


/**
 * @author David
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class SongServiceImplTest extends AbstractTestNGSpringContextTests {

	@Mock
	private SongDao songDao;

	@Autowired
	@InjectMocks
	private SongService songService;

	private Song song1;
	private Song song2;

	@BeforeMethod
	public void createSongs() {
		song1 = new Song();
		song2 = new Song();

		song1.setTitle("Roots");
		song1.setId(1L);
		song2.setTitle("Bloody Roots");
		song2.setId(2L);
	}

	@BeforeClass
	public void setup() throws ServiceException {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreate() {
		ArgumentCaptor<Song> argumentCaptor = ArgumentCaptor.forClass(Song.class);
		songService.create(song1);
		Mockito.verify(songDao, Mockito.times(1)).create(song1);
	}

	@Test
	public void testRemove() {
		songService.remove(song1);
		ArgumentCaptor<Song> argumentCaptor = ArgumentCaptor.forClass(Song.class);
		verify(songDao, atLeast(0)).delete(argumentCaptor.capture());

		Assert.assertEquals(argumentCaptor.getAllValues().size(), 1);
	}

	@Test
	public void testFindAll() {
		List<Song> songs = new ArrayList<>();
		songs.add(song1);
		songs.add(song2);
		when(songDao.findAll()).thenReturn(songs);

		List<Song> found = songService.findAll();

		verify(songDao).findAll();
		Assert.assertEquals(songs, found);
	}

	@Test
	public void testFindByTitle() {
		List<Song> songs = new ArrayList<>();
		songs.add(song1);
		when(songDao.findByTitle("Roots")).thenReturn(songs);

		List<Song> found = songService.findByTitle("Roots");

		Assert.assertEquals(found.get(0).getTitle(), "Roots");
	}

	@Test
	public void testFindByPartOfTitle() {
		List<Song> songs = new ArrayList<>();
		songs.add(song2);
		when(songDao.findByTitle("Bloody")).thenReturn(songs);

		List<Song> found = songService.findByTitle("Bloody");

		Assert.assertEquals(found.get(0).getTitle(), "Bloody Roots");
	}

	@Test
	public void testFindByMusician() {
		List<Song> songs = new ArrayList<>();
		Musician m = new Musician();

		m.setId(1L);
		m.setName("Sepultura");
		song2.setMusician(m);
		songs.add(song2);
		when(songDao.findByMusician(m)).thenReturn(songs);

		List<Song> found = songService.findByMusician(m);

		Assert.assertEquals(found.size(), songs.size());
		Assert.assertEquals(found.get(0).getTitle(), "Bloody Roots");
	}

	@Test
	public void testFindByGenre() {
		List<Song> songs = new ArrayList<>();

		Genre g1 = new Genre();
		g1.setName("Heavy Metal");

		song1.setGenre(g1);
		songs.add(song1);
		when(songDao.findByGenre(g1)).thenReturn(songs);

		List<Song> found = songService.findByGenre(g1);

		Assert.assertEquals(found.get(0).getGenre().getName(), g1.getName());
	}

	@Test
	public void testFindById() {

		when(songDao.findById(1L)).thenReturn(song1);
		when(songDao.findById(2L)).thenReturn(song2);

		Song found1 = songService.findById(1L);
		Song found2 = songService.findById(2L);


		Assert.assertEquals(found1, song1);
		Assert.assertEquals(found2, song2);
	}

	@Test
	public void testFindByNonExistingId() {
		when(songDao.findById(3L)).thenReturn(null);

		Song found = songService.findById(3L);
		Assert.assertNull(found);
	}
}
