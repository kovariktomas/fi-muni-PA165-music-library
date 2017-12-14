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
		// TODO
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
