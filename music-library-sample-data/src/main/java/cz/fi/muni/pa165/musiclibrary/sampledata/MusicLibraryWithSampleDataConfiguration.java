package cz.fi.muni.pa165.musiclibrary.sampledata;

import cz.fi.muni.pa165.musiclibrary.service.config.ServiceConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Takes ServiceConfiguration and adds the SampleDataLoadingFacade bean.
 */
@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackageClasses = {SampleDataLoadingFacadeImpl.class})
public class MusicLibraryWithSampleDataConfiguration {

	final static Logger log = LoggerFactory.getLogger(MusicLibraryWithSampleDataConfiguration.class);

	@Autowired
	SampleDataLoadingFacade sampleDataLoadingFacade;

	@PostConstruct
	public void dataLoading() throws IOException {
		log.debug("dataLoading()");
		sampleDataLoadingFacade.loadData();
	}
}
