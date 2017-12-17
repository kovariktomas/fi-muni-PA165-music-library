package cz.fi.muni.pa165.musiclibrary.sampledata;

import cz.fi.muni.pa165.musiclibrary.entity.Musician;
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
import java.util.HashSet;

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

	@Override
	@SuppressWarnings("unused")
	public void loadData() {
		loadMusicians();
		loadAlbums();
		loadGenres();
		loadSongs();
	}

	private void loadMusicians() {
		HashSet<String> existing = new HashSet<>();

		for (Record record : records) {
			String name = record.getMusicianName();
			if (existing.contains(name)) continue;
			existing.add(name);

			Musician musician = new Musician();
			musician.setName(name);
			musicianService.create(musician);
		}
	}

	private void loadAlbums() {
		// TODO
	}

	private void loadGenres() {
		// TODO
	}

	private void loadSongs() {
		// TODO
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
			".jpg",
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
		private String albumArtPath;
		private String songTitle;

		Record(String musicianName, String albumTitle, String albumArtPath, String songTitle) {
			this.musicianName = musicianName;
			this.albumTitle = albumTitle;
			this.albumArtPath = albumArtPath;
			this.songTitle = songTitle;
		}

		String getMusicianName() {
			return musicianName;
		}

		String getAlbumTitle() {
			return albumTitle;
		}

		String getAlbumArtPath() {
			return albumArtPath;
		}

		String getSongTitle() {
			return songTitle;
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
