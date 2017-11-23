package cz.fi.muni.pa165.musiclibrary.service;

import cz.fi.muni.pa165.musiclibrary.service.config.ServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class BeanMappingServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private BeanMappingService beanMappingService;

    /*private List<Category> categories = new ArrayList<Category>();
    private List<Product> products = new ArrayList<Product>();
    @BeforeMethod
    public void createOrders(){
    	Category cElectro = new Category(2l);
		cElectro.setName("Electronics");
    	Category cSmall = new Category(2l);
    	cSmall.setName("Small");


		Product pTv = new Product(3l);
		pTv.addCategory(cElectro);
		pTv.setName("Sharp TV");
		products.add(pTv);

		Product pRadio = new Product(3l);
		pRadio.addCategory(cElectro);
		pRadio.addCategory(cSmall);
		pRadio.setName("Radio");
		products.add(pRadio);

		Product pSpoon = new Product(3l);
		pSpoon.addCategory(cSmall);
		pSpoon.setName("Spoon");
		products.add(pSpoon);

		categories.add(cElectro);
		categories.add(cSmall);

    }

    @Test
    public void shouldMapInnerCategories(){
    	List<ProductDTO> cdtos = beanMappingService.mapTo(products, ProductDTO.class);
    	Assert.assertEquals(cdtos.get(0).getCategories().size(), 1);
    	
    }*/

}
