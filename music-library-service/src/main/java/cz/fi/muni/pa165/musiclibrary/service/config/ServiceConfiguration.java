package cz.fi.muni.pa165.musiclibrary.service.config;

import cz.fi.muni.pa165.musiclibrary.PersistenceSampleApplicationContext;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PersistenceSampleApplicationContext.class)
//@ComponentScan(basePackageClasses={OrderServiceImpl.class, CategoryFacadeImpl.class})
public class ServiceConfiguration {


    @Bean
    public Mapper dozer() {
        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.addMapping(new DozerCustomConfig());
        return dozer;
    }

    /**
     * Custom config for Dozer if needed
     *
     * @author nguyen
     */
    public class DozerCustomConfig extends BeanMappingBuilder {
        @Override
	    protected void configure() {
	        //mapping(Category.class, CategoryDTO.class);
	    }
    }

}

