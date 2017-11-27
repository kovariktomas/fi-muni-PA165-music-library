package cz.fi.muni.pa165.musiclibrary.service.facade;

import cz.fi.muni.pa165.musiclibrary.dto.AlbumDTO;
import cz.fi.muni.pa165.musiclibrary.dto.SongDTO;
import cz.fi.muni.pa165.musiclibrary.facade.AlbumFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class AlbumFacadeImplTest {

    @Autowired
    private AlbumFacade albumFacade;

    private AlbumDTO albumDTOHungry;
    private AlbumDTO albumDTOLazy;

    private GenreDTO contryGenre;
    private SongDTO countrySong;
    private MusicianDTO daya;

    @BeforeMethod
    public void setUp(){
        albumDTOHungry = new AlbumDTO();
        albumDTOHungry.setId(new Long(1));
        albumDTOHungry.setTitle("Hungry days");

        albumDTOLazy = new AlbumDTO();
        albumDTOLazy.setId(new Long(2));
        albumDTOLazy.setTitle("Hungry days");

        countryGenre = new GenreDTO();
        countryGenre.setId(new Long(1));
        countryGenre.setName("Country");

        daya = new MusicianDTO();
        daya.setID(new Long(1));
        daya.setName("Daya");

        countrySong = new SongDTO();
        countrySong.setId(new Long(1));
        countrySong.setTitle("Some country song");
        countrySong.setGenre(countryGenre);
        countrySong.setMusician(daya);

        albumDTOHungry.addSong(countrySong);
    }

    @Test
    public void testCreate(){
        albumFacade.create(albumDTOHungry);
        Assert.assertEquals(albumFacade.findById(albumDTOHungry.getId()), albumDTOHungry);
    }

    @Test
    public void testUpdate(){
        String oldName = albumDTOHungry.getTitle();
        albumFacade.create(albumDTOHungry);
        albumDTOHungry.setTitle("Last hungry day");
        albumFacade.update(albumDTOHungry);
        AlbumDTO updatedDto = albumFacade.findById(albumDTOHungry.getId());
        Assert.assertNotEquals(oldName, updatedDto.getTitle());
        Assert.assertEquals(albumDTOHungry, updatedDto);
        Assert.assertEquals(albumDTOHungry.getTitle(), updatedDto.getTitle());
    }

    @Test
    public void testRemove(){
        albumFacade.create(albumDTOHungry);
        albumFacade.remove(albumDTOHungry);
        List<AlbumDTO> result = albumFacade.findAll();
        Assert.assertEquals(0, result.size());
    }

    @Test
    public void testFindById(){
        albumFacade.create(albumDTOHungry);
        albumFacade.create(albumDTOLazy);
        Assert.assertEquals(albumFacade.findById(albumDTOHungry.getId()), albumDTOHungry);
        Assert.assertEquals(albumFacade.findById(albumDTOLazy.getId()), albumDTOLazy);
    }

    @Test
    public void testFindByMusician(){
        albumFacade.create(albumDTOHungry);
        Assert.assertEquals(albumFacade.findByMusician(daya).get(0), albumDTOHungry);
    }

    @Test
    public void testFindByGenre(){
        albumFacade.create(albumDTOHungry);
        Assert.assertEquals(albumFacade.findByGenre(contryGenre).get(0), albumDTOHungry);
    }

    @Test
    public void testFindByTitle(){
        albumFacade.create(albumDTOLazy);
        Assert.assertEquals(albumDTOLazy, albumFacade.findByTitle(albumDTOLazy.getTitle()).get(0));
    }

    @Test
    public void testFindAll(){
        albumFacade.create(albumDTOHungry);
        albumFacade.create(albumDTOLazy);
        List<AlbumDTO> result = albumFacade.findAll();
        Assert.assertEquals(2, result.size());
        Assert.assertEquals(result.get(1), albumDTOHungry);
        Assert.assertEquals(result.get(2), albumDTOLazy);
    }
}