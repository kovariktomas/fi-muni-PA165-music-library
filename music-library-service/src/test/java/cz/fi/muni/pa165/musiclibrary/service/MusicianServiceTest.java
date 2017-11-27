package cz.fi.muni.pa165.musiclibrary.service;

import cz.fi.muni.pa165.musiclibrary.dao.MusicianDao;
import cz.fi.muni.pa165.musiclibrary.entity.Musician;
import cz.fi.muni.pa165.musiclibrary.service.config.ServiceConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class MusicianServiceTest extends AbstractTestNGSpringContextTests {

	@Mock
	private MusicianDao musicianDao;

	@Autowired
	@InjectMocks
	private MusicianService musicianService;

	@BeforeClass
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreate() {
		Musician musician = new Musician();
		musician.setName("Parov Stelar");

		musicianService.create(musician);

		verify(musicianDao).create(musician);
	}

	@Test
	public void testUpdate() {
		Musician musician = new Musician();
		musician.setName("Parov Stelar");

		musicianService.update(musician);

		verify(musicianDao).update(musician);
	}

	@Test
	public void testDelete() {
		Musician musician = new Musician();
		musician.setName("Parov Stelar");

		musicianService.delete(musician);

		verify(musicianDao).delete(musician);
	}

	@Test
	public void testFindById() {
		Musician musician = new Musician();
		musician.setName("Parov Stelar");

		when(musicianDao.findById(1L)).thenReturn(musician);

		Assert.assertSame(musician, musicianService.findById(1L));
	}

	@Test
	public void testFindNonexistentById() {
		when(musicianDao.findById(1L)).thenReturn(null);

		Assert.assertEquals(null, musicianService.findById(1L));
	}

	@Test
	public void testFindByName() {
		Musician musician1 = new Musician();
		musician1.setName("Red Hot Chilli Peppers");
		Musician musician2 = new Musician();
		musician2.setName("Alabama Red Peppers");
		List<Musician> musicians = Arrays.asList(musician1, musician2);

		when(musicianDao.findByName(Arrays.asList("%red%", "%peppers%"))).thenReturn(musicians);

		Assert.assertSame(musicians, musicianService.findByName("red peppers"));
	}

	@Test
	public void testFindByNameWithEmptyQuery() {
		Musician musician1 = new Musician();
		musician1.setName("Parov Stelar");
		Musician musician2 = new Musician();
		musician2.setName("Caravan Palace");
		List<Musician> musicians = Arrays.asList(musician1, musician2);

		when(musicianDao.findByName(new ArrayList<>())).thenReturn(musicians);

		Assert.assertSame(musicians, musicianService.findByName(""));
	}

	@Test
	public void testFindAll() {
		Musician musician1 = new Musician();
		musician1.setName("Parov Stelar");
		Musician musician2 = new Musician();
		musician2.setName("Caravan Palace");
		List<Musician> musicians = Arrays.asList(musician1, musician2);

		when(musicianDao.findAll()).thenReturn(musicians);

		Assert.assertSame(musicians, musicianService.findAll());
	}

}
