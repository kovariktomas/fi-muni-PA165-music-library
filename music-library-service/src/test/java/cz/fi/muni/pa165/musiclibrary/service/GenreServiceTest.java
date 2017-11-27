package cz.fi.muni.pa165.musiclibrary.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cz.fi.muni.pa165.musiclibrary.dao.GenreDao;
import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.exceptions.MusicLibraryServiceException;
import cz.fi.muni.pa165.musiclibrary.service.GenreService;
import cz.fi.muni.pa165.musiclibrary.service.config.ServiceConfiguration;

@ContextConfiguration(classes=ServiceConfiguration.class)
public class GenreServiceTest extends AbstractTransactionalTestNGSpringContextTests
{
    @Mock
    private GenreDao genreDao;
    
    @Autowired
    @InjectMocks
    private GenreService genreService;

    @BeforeClass
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }
    
    private Genre testGenre;
    
    
    @BeforeMethod
    public void prepareTestGenre(){
    	testGenre = new Genre();
    }

}