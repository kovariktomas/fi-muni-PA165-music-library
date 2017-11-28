package cz.fi.muni.pa165.musiclibrary.service.facade;

import cz.fi.muni.pa165.musiclibrary.dto.*;
import cz.fi.muni.pa165.musiclibrary.entity.Album;
import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.entity.Musician;
import cz.fi.muni.pa165.musiclibrary.entity.Song;
import cz.fi.muni.pa165.musiclibrary.facade.SongFacade;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class SongFacadeImplTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private SongFacade songFacade;

	@PersistenceContext
	private EntityManager em;

	private Song song1;
	private SongDTO songDTO;
	private Album album;
	private Musician musician;
	private Genre genre;

	@BeforeMethod
	public void init() {
		album = createSampleAlbum("Hybrid Theory", "2000-10-24");
		musician = createSampleMusician("Linkin Park");
		genre = createSampleGenre("Nu metal");

		songDTO = new SongDTO();
		songDTO.setAlbum(createAlbumDTO(album));
		songDTO.setMusician(createMusicianDTO(musician));
		songDTO.setGenre(createGenreDTO(genre));
		songDTO.setTitle("In The End");
		songDTO.setBitrate(320);
		songDTO.setPosition(0);

		song1 = new Song();
		song1.setAlbum(album);
		song1.setMusician(musician);
		song1.setGenre(genre);
		song1.setTitle("In The End");
		song1.setBitrate(320);
		song1.setPosition(0);
	}


	@Test
	public void testCreate() {
		SongCreateDTO songCreateDTO = new SongCreateDTO();
		songCreateDTO.setAlbumId(album.getId());
		songCreateDTO.setMusicianId(musician.getId());
		songCreateDTO.setGenreId(genre.getId());
		songCreateDTO.setTitle("In The End");
		songCreateDTO.setBitrate(320);
		songCreateDTO.setPosition(0);
		songFacade.create(songCreateDTO);

		List<Song> songs = getAllSongs();
		Assert.assertEquals(1, songs.size());
		Assert.assertEquals("In The End", songs.get(0).getTitle());
	}

	@Test
	public void testDelete() {
		em.persist(song1);

		songFacade.delete(song1.getId());

		List<Song> songs = getAllSongs();
		Assert.assertEquals(0, songs.size());
	}

	@Test
	public void testFindById() {
		em.persist(song1);

		SongDTO songDto = songFacade.findById(song1.getId());

		Assert.assertEquals(song1.getId(), songDto.getId());
		Assert.assertEquals("In The End", songDto.getTitle());
	}

	@Test
	public void testFindAll() {
		em.persist(song1);

		List<SongDTO> songDTOs = songFacade.findAll();

		Assert.assertEquals(1, songDTOs.size());
		Assert.assertEquals(song1.getTitle(), songDTOs.get(0).getTitle());
	}

	@Test
	public void testFindByTitle() {
		em.persist(song1);

		List<SongDTO> songDTOs = songFacade.findByTitle("end");

		Assert.assertEquals(1, songDTOs.size());
		Assert.assertEquals(song1.getId(), songDTOs.get(0).getId());
	}

	@Test
	public void testFindByMusician() {
		em.persist(song1);

		List<SongDTO> songDTOs = songFacade.findByMusician(musician.getId());

		Assert.assertEquals(1, songDTOs.size());
		Assert.assertEquals(song1.getTitle(), songDTOs.get(0).getTitle());
		Assert.assertEquals(song1.getPosition(), songDTOs.get(0).getPosition());
	}

	@Test
	public void testFindByGenre() {
		em.persist(song1);

		List<SongDTO> songDTOs = songFacade.findByGenre(genre.getId());

		Assert.assertEquals(1, songDTOs.size());
		Assert.assertEquals(song1.getTitle(), songDTOs.get(0).getTitle());
		Assert.assertEquals(song1.getPosition(), songDTOs.get(0).getPosition());
	}

	private Album createSampleAlbum(String title, String date) {
		Album album = new Album();
		album.setReleaseDate(createDate(date));
		album.setTitle(title);
		em.persist(album);
		return album;
	}

	private AlbumDTO createAlbumDTO(Album album) {
		AlbumDTO albumDTO = new AlbumDTO();
		albumDTO.setId(album.getId());
		albumDTO.setReleaseDate(album.getReleaseDate());
		albumDTO.setTitle(album.getTitle());
		albumDTO.setCommentary(album.getCommentary());
		albumDTO.setAlbumArt(album.getAlbumArt());
		return albumDTO;
	}

	private Musician createSampleMusician(String name) {
		Musician musician = new Musician();
		musician.setName(name);
		em.persist(musician);
		return musician;
	}

	private MusicianDTO createMusicianDTO(Musician musician) {
		MusicianDTO musicianDTO = new MusicianDTO();
		musicianDTO.setId(musician.getId());
		musicianDTO.setName(musician.getName());
		return musicianDTO;
	}

	private Genre createSampleGenre(String name) {
		Genre genre = new Genre();
		genre.setName(name);
		em.persist(genre);
		return genre;
	}

	private GenreDTO createGenreDTO(Genre genre) {
		GenreDTO genreDTO = new GenreDTO();
		genreDTO.setId(genre.getId());
		genreDTO.setName(genre.getName());
		return genreDTO;
	}

	private Date createDate(String date) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			return dateFormat.parse(date);
		} catch (ParseException ex) {
			throw new RuntimeException(ex);
		}
	}

	private List<Song> getAllSongs() {
		return em.createQuery("SELECT s FROM Song s", Song.class).getResultList();
	}

}
