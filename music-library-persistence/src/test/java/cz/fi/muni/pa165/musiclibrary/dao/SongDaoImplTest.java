package cz.fi.muni.pa165.musiclibrary.dao;

import bsh.ParseException;
import cz.fi.muni.pa165.musiclibrary.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.musiclibrary.entity.Album;
import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.entity.Musician;
import cz.fi.muni.pa165.musiclibrary.entity.Song;
import cz.fi.muni.pa165.musiclibrary.dao.AlbumDao;
import cz.fi.muni.pa165.musiclibrary.dao.GenreDao;
import cz.fi.muni.pa165.musiclibrary.dao.MusicianDao;
import cz.fi.muni.pa165.musiclibrary.dao.SongDao;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


/**
 *
 * @author Kovarik Tomas
 */

@ContextConfiguration(classes=PersistenceSampleApplicationContext.class) // TODO
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class SongDaoImplTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private SongDao songDao;

	@Autowired
    private MusicianDao musicianDao;

	@Autowired
    private AlbumDao albumDao;

	@Autowired
    private GenreDao genreDao;

	
    public static final int SONGS_COUNT = 1;

    private Song song1;
	
    private Song song2;
    
    @BeforeMethod
    public void init() throws ParseException {
	
		Musician m = new Musician();
		Album a = new Album();
		Genre g = new Genre();
		song1 = new Song();
		
		m.setName("Musician 01");
		musicianDao.create(m);
		
		g.setName("Genre 01");
		genreDao.create(g);
		
		song1.setBitrate(1000);
		song1.setAlbum(a);
		song1.setCommentary("Sample comentary");
		song1.setGenre(g);
		song1.setId(1l);
		song1.setMusician(m);
		song1.setPosition(1);
		song1.setTitle("Song 01");
		
		a.setTitle("Album 01");
		a.setReleaseDate(new Date());
		a.setCommentary("Comentary for album");
		a.addSong(song1);		
		albumDao.create(a);
	
		songDao.create(song1);
	
	}
	
    @Test
    public void testFindAllSong() {
        Assert.assertEquals(SONGS_COUNT, songDao.findAll().size());
    }
	
}
