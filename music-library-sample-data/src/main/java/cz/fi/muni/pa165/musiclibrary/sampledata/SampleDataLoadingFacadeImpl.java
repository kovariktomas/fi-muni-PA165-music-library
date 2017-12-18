package cz.fi.muni.pa165.musiclibrary.sampledata;

import cz.fi.muni.pa165.musiclibrary.entity.*;
import cz.fi.muni.pa165.musiclibrary.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

/**
 * Loads some sample data to populate the music library database.
 */
@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

	final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);

	public static final String JPEG = "image/jpeg";

	@Autowired
	private ApplicationUserService applicationUserService;

	@Autowired
	private AlbumService albumService;

	@Autowired
	private GenreService genreService;

	@Autowired
	private MusicianService musicianService;

	@Autowired
	private SongService songService;

	private ApplicationUser user;

	private HashMap<String, Musician> musicians = new HashMap<>();

	private HashMap<String, Album> albums = new HashMap<>();

	private HashMap<String, Integer> albumSizes = new HashMap<>();

	private HashMap<String, Genre> genres = new HashMap<>();

	private HashMap<String, Song> songs = new HashMap<>();

	@Override
	@SuppressWarnings("unused")
	public void loadData() throws IOException {
		loadApplicationUser();
		loadMusicians();
		loadAlbums();
		loadGenres();
		loadSongs();
	}

	private void loadApplicationUser() {
		if (user != null) return;

		user = new ApplicationUser();
		user.setName("admin");
		user.setEmail("admin@example.com");
		user.setRole("admin");
		applicationUserService.create(user, "password");
	}

	private void loadMusicians() {
		for (Record record : records) {
			if (musicians.containsKey(record.musicianName)) continue;

			Musician musician = new Musician();
			musician.setName(record.musicianName);
			musicianService.create(musician);
			musicians.put(record.musicianName, musician);
		}
	}

	private void loadAlbums() throws IOException {
		for (Record record : records) {
			if (albums.containsKey(record.albumTitle)) continue;

			Album album = new Album();
			album.setReleaseDate(record.albumReleaseDate);
			album.setTitle(record.albumTitle);
			album.setCommentary("");
			album.setAlbumArt(readImage("album-arts/" + record.albumArtPath));
			albumService.create(album);
			albums.put(record.albumTitle, album);
		}
	}

	private void loadGenres() {
		for (Record record : records) {
			if (genres.containsKey(record.genreName)) continue;

			Genre genre = new Genre();
			genre.setName(record.genreName);
			genreService.create(genre);
			genres.put(record.genreName, genre);
		}
	}

	private void loadSongs() {
		for (Record record : records) {
			if (songs.containsKey(record.songTitle)) continue;

			Song song = new Song();
			song.setMusician(musicians.get(record.musicianName));
			song.setAlbum(albums.get(record.albumTitle));
			song.setGenre(genres.get(record.genreName));
			song.setTitle(record.songTitle);
			song.setBitrate(320);

			int position = albumSizes.getOrDefault(record.albumTitle, 0);
			song.setPosition(position);
			albumSizes.put(record.albumTitle, position + 1);

			song.setCommentary("");
			songService.create(song);
			songs.put(record.songTitle, song);
		}
	}

	private Record[] records = new Record[]{
		new Record(
			"Post Malone",
			"Rockstar",
			toDate(2017, 9, 15),
			"rockstar.jpg",
			"Hip hop",
			"Rockstar"
		),
		new Record(
			"Camila Cabello",
			"Havana",
			toDate(2017, 9, 8),
			"havana.jpg",
			"Pop",
			"Havana"
		),
		new Record(
			"Lil Pump",
			"Gucci Gang",
			toDate(2017, 8, 31),
			"gucci-gang.jpg",
			"Hip hop",
			"Gucci Gang"
		),
		new Record(
			"Imagine Dragons",
			"Evolve",
			toDate(2017, 7, 23),
			"evolve.jpg",
			"Pop rock",
			"Thunder"
		),
		new Record(
			"Cardi B",
			"Bodak Yellow",
			toDate(2017, 7, 16),
			"bodak-yellow.jpg",
			"Hip hop",
			"Bodak Yellow"
		),
		new Record(
			"Sam Smith",
			"The Thrill Of It All",
			toDate(2017, 11, 3),
			"the-thrill-of-it-all.jpg",
			"R&B",
			"Too Good At Goodbyes"
		),
		new Record(
			"Ed Sheeran",
			"÷",
			toDate(2017, 3, 3),
			"divide.jpg",
			"Pop",
			"Perfect"
		),
		new Record(
			"Logic",
			"Everybody",
			toDate(2017, 12, 1),
			"everybody.jpg",
			"Conscious hip hop",
			"1-800-273-8255"
		),
		new Record(
			"Portugal. The Man",
			"Feel It Still",
			toDate(2017, 5, 5),
			"feel-it-still.jpg",
			"Psychedelic pop",
			"Feel It Still"
		),
		new Record(
			"J Balvin",
			"Mi Gente",
			toDate(2017, 6, 30),
			"mi-gente.jpg",
			"Reggaeton",
			"Mi Gente"
		),
		new Record(
			"Maroon 5",
			"Red Pill Blues",
			toDate(2017, 11, 3),
			"red-pill-blues.jpg",
			"R&B",
			"What Lovers Do"
		),
		new Record(
			"Demi Lovato",
			"Tell Me You Love Me",
			toDate(2017, 9, 29),
			"tell-me-you-love-me.jpg",
			"R&B",
			"Sorry Not Sorry"
		),
		new Record(
			"G-Eazy",
			"The Beautiful & Damned",
			toDate(2017, 12, 15),
			"the-beautiful-and-damned.jpg",
			"Hip hop",
			"No Limit"
		),
		new Record(
			"Eminem",
			"Revival",
			toDate(2017, 12, 15),
			"revival.jpg",
			"Hip hop",
			"Walk On Water"
		),
		new Record(
			"Migos",
			"MotorSport",
			toDate(2017, 10, 27),
			"motorsport.jpg",
			"Hip hop",
			"MotorSport"
		),
		new Record(
			"Gucci Mane",
			"Mr. Davis",
			toDate(2017, 10, 13),
			"mr-davis.jpg",
			"Trap",
			"I Get The Bag"
		),
		new Record(
			"Halsey",
			"Hopeless Fountain Kingdom",
			toDate(2017, 6, 2),
			"hopeless-fountain-kingdom.jpg",
			"Electropop",
			"Bad At Love"
		),
		new Record(
			"Taylor Swift",
			"reputation",
			toDate(2017, 11, 10),
			"reputation.jpg",
			"Electropop",
			"...Ready For It?"
		),
		new Record(
			"Dua Lipa",
			"Dua Lipa",
			toDate(2017, 6, 2),
			"dua-lipa.jpg",
			"Pop",
			"New Rules"
		),
		new Record(
			"P!nk",
			"Beautiful Trauma",
			toDate(2017, 10, 13),
			"beautiful-trauma.jpg",
			"Pop",
			"What About Us"
		)
	};

	private class Record {
		private String musicianName;
		private String albumTitle;
		private Date albumReleaseDate;
		private String albumArtPath;
		private String genreName;
		private String songTitle;

		Record(
			String musicianName,
			String albumTitle,
			Date albumReleaseDate,
			String albumArtPath,
			String genreName,
			String songTitle
		) {
			this.musicianName = musicianName;
			this.albumTitle = albumTitle;
			this.albumReleaseDate = albumReleaseDate;
			this.albumArtPath = albumArtPath;
			this.genreName = genreName;
			this.songTitle = songTitle;
		}
	}

	private static Date toDate(int year, int month, int day) {
		return Date.from(LocalDate.of(year, month, day).atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	private byte[] readImage(String file) throws IOException {
		try (InputStream is = this.getClass().getResourceAsStream("/" + file)) {
			int nRead;
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			byte[] data = new byte[1024];
			while ((nRead = is.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}
			buffer.flush();
			return buffer.toByteArray();
		}
	}
}
