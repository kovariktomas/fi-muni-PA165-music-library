package cz.fi.muni.pa165.musiclibrary.facade;

import cz.fi.muni.pa165.musiclibrary.dto.AlbumDTO;

import java.util.List;

/**
 *
 * @author Iva Liberova
 */
public interface AlbumFacade {
    void create(AlbumDTO album);
    void update(AlbumDTO album) throws IllegalArgumentException;
    void remove(AlbumDTO album) throws IllegalArgumentException;
    AlbumDTO findById(Long id);
    List<AlbumDTO> findByMusician(MusicianDTO musician);
    List<AlbumDTO> findByGenre(GenreDTO genre);
    List<AlbumDTO> findByTitle(String titlePattern);
    List<AlbumDTO> findAll();
}
