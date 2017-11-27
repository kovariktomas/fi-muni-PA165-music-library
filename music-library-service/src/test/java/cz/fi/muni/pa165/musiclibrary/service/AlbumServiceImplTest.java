package cz.fi.muni.pa165.musiclibrary.service;

import cz.fi.muni.pa165.musiclibrary.service.config.ServiceConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class AlbumServiceImplTest extends AbstractTestNGSpringContextTests {
	@BeforeMethod
	public void setUp() {
	}

	@Test
	public void testCreate() {
	}

	@Test
	public void testUpdate() {
	}

	@Test
	public void testRemove() {
	}

	@Test
	public void testFindById() {
	}

	@Test
	public void testFindByMusician() {
	}

	@Test
	public void testFindByGenre() {
	}

	@Test
	public void testFindByTitle() {
	}

	@Test
	public void testFindAll() {
	}

	@Test
	public void testGetAlbumsFromLastMonth() {
	}

	@Test
	public void testSearchAlbumsByQuery() {
	}

}
