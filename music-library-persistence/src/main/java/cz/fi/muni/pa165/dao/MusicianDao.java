package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Musician;
import java.util.List;

/**
 *
 * @author David Koncak
 */
public interface MusicianDao {
    public void create(Musician m);
    public void remove(Musician m) throws IllegalArgumentException;
    public void update(Musician m);
    public Musician findById(Long id);
    public List<Musician> findByName(String namePattern);
    public List<Musician> findAll();
    
}
