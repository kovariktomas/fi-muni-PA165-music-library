package cz.fi.muni.pa165.musiclibrary.service.facade;

import cz.fi.muni.pa165.musiclibrary.dao.AlbumDao;
import cz.fi.muni.pa165.musiclibrary.dto.AlbumDTO;
import cz.fi.muni.pa165.musiclibrary.facade.AlbumFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AlbumFacadeImplTest {

    @Autowired
    private AlbumFacade albumFacade;

    private AlbumDTO albumDTO;

    @BeforeMethod
    public void setUp(){
        albumDTO = new AlbumDTO();
        albumDTO.setId(new Long(1));
        albumDTO.setTitle("Hungry days");
    }

    @Test
    public void testCreate(){
    }

    @Test
    public void testUpdate(){
    }

    @Test
    public void testRemove(){
    }

    @Test
    public void testFindById(){
    }

    @Test
    public void testFindByMusician(){
    }

    @Test
    public void testFindByGenre(){
    }

    @Test
    public void testFindByTitle(){
    }

    @Test
    public void testFindAll(){
    }

}