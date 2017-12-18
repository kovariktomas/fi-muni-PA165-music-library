package cz.fi.muni.pa165.musiclibrary.service;

import cz.fi.muni.pa165.musiclibrary.entity.Album;
import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.entity.Musician;
import cz.fi.muni.pa165.musiclibrary.entity.Song;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author David
 */
@Service
public interface SongService {

	Song findById(Long id);

	Song create(Song song);

	void remove(Song song);

	List<Song> findAll();

	List<Song> findByMusician(Musician musician);

	List<Song> findByAlbum(Album album);

	List<Song> findByGenre(Genre genre);

	List<Song> findByTitle(String query);
}
