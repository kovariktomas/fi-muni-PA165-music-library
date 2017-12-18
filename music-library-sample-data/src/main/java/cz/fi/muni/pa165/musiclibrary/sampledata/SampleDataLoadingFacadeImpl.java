package cz.fi.muni.pa165.musiclibrary.sampledata;

import cz.fi.muni.pa165.musiclibrary.entity.Album;
import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.entity.Musician;
import cz.fi.muni.pa165.musiclibrary.entity.Song;
import cz.fi.muni.pa165.musiclibrary.service.AlbumService;
import cz.fi.muni.pa165.musiclibrary.service.GenreService;
import cz.fi.muni.pa165.musiclibrary.service.MusicianService;
import cz.fi.muni.pa165.musiclibrary.service.SongService;
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
	private AlbumService albumService;

	@Autowired
	private GenreService genreService;

	@Autowired
	private MusicianService musicianService;

	@Autowired
	private SongService songService;

	private HashMap<String, Musician> musicians = new HashMap<>();

	private HashMap<String, Album> albums = new HashMap<>();

	private HashMap<String, Integer> albumSizes = new HashMap<>();

	private HashMap<String, Genre> genres = new HashMap<>();

	private HashMap<String, Song> songs = new HashMap<>();

	@Override
	@SuppressWarnings("unused")
	public void loadData() throws IOException {
		loadMusicians();
		loadAlbums();
		loadGenres();
		loadSongs();
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

	private Record[] records = new Record[] {
		new Record(
			"Post Malone",
			"Rockstar",
			"rockstar.jpg",
			"Rockstar"
		),
		new Record(
			"Camila Cabello",
			"Havana",
			"havana.jpg",
			"Havana"
		),
		new Record(
			"Lil Pump",
			"Gucci Gang",
			"gucci-gang.jpg",
			"Gucci Gang"
		),
		new Record(
			"Imagine Dragons",
			"Evolve",
			"evolve.jpg",
			"Thunder"
		),
		new Record(
			"Cardi B",
			"Bodak Yellow",
			"bodak-yellow.jpg",
			"Bodak Yellow"
		),
		new Record(
			"Sam Smith",
			"The Thrill Of It All",
			"the-thrill-of-it-all.jpg",
			"Too Good At Goodbyes"
		),
		new Record(
			"Ed Sheeran",
			"÷",
			"divide.jpg",
			"Perfect"
		),
		new Record(
			"Logic",
			"Everybody",
			"everybody.jpg",
			"1-800-273-8255"
		),
		new Record(
			"Portugal. The Man",
			"Feel It Still",
			"feel-it-still.jpg",
			"Feel It Still"
		),
		new Record(
			"J Balvin",
			"Mi Gente",
			"mi-gente.jpg",
			"Mi Gente"
		),
		new Record(
			"Maroon 5",
			"Red Pill Blues",
			"red-pill-blues.jpg",
			"What Lovers Do"
		),
		new Record(
			"Demi Lovato",
			"Tell Me You Love Me",
			"tell-me-you-love-me.jpg",
			"Sorry Not Sorry"
		),
		new Record(
			"G-Eazy",
			"The Beautiful & Damned",
			"the-beautiful-and-damned.jpg",
			"No Limit"
		),
		new Record(
			"Eminem",
			"Revival",
			"revival.jpg",
			"Walk On Water"
		),
		new Record(
			"Migos",
			"MotorSport",
			"motorsport.jpg",
			"MotorSport"
		),
		new Record(
			"Gucci Mane",
			"Mr. Davis",
			"mr-davis.jpg",
			"I Get The Bag"
		),
		new Record(
			"Halsey",
			"hopeless fountain kingdom",
			"hopeless-fountain-kingdom.jpg",
			"Bad At Love"
		),
		new Record(
			"Taylor Swift",
			"reputation",
			"reputation.jpg",
			"...Ready For It?"
		),
		new Record(
			"Dua Lipa",
			"Dua Lipa",
			"dua-lipa.jpg",
			"New Rules"
		),
		new Record(
			"P!nk",
			"Beautiful Trauma",
			"beautiful-trauma.jpg",
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

		Record(String musicianName, String albumTitle, String albumArtPath, String songTitle) {
			this.musicianName = musicianName;
			this.albumTitle = albumTitle;
			this.albumReleaseDate = toDate(2017, 12, 1); // TODO
			this.albumArtPath = albumArtPath;
			this.genreName = "foo"; // TODO
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
