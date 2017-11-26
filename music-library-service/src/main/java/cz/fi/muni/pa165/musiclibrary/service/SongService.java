package cz.fi.muni.pa165.musiclibrary.service;

import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.entity.Musician;
import cz.fi.muni.pa165.musiclibrary.entity.Song;
import java.util.List;

/**
 *
 * @author David
 */
public interface SongService {
    
    Song findById(Long id);
    
    void create(Song song);
    
    void remove(Song song);
    
    List<Song> findAll();
    
    List<Song> findByMusician(Musician musician);

    List<Song> findByGenre(Genre genre);

    List<Song> findByTitle(String titlePattern);
}
