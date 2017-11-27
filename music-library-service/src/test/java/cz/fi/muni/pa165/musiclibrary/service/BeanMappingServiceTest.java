package cz.fi.muni.pa165.musiclibrary.service;

import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.service.config.ServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.List;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class BeanMappingServiceTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private BeanMappingService beanMappingService;

	private List<Genre> genres = new ArrayList<Genre>();

	@BeforeMethod
	public void createGenres() {
		Genre gRock = new Genre(2l);
		gRock.setName("Rock");
		Genre gPop = new Genre(3l);
		gPop.setName("Pop");

	}

	/* @Test
	public void shouldMapInnerCategories(){
		List<ProductDTO> cdtos = beanMappingService.mapTo(products, ProductDTO.class);
		Assert.assertEquals(cdtos.get(0).getCategories().size(), 1);
	}*/

}
