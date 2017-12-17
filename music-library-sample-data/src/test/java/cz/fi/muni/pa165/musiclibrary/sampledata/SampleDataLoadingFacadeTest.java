package cz.fi.muni.pa165.musiclibrary.sampledata;

import cz.fi.muni.pa165.musiclibrary.entity.Musician;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Tests data loading.
 */
@ContextConfiguration(classes = {MusicLibraryWithSampleDataConfiguration.class})
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class SampleDataLoadingFacadeTest extends AbstractTestNGSpringContextTests {

	final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeTest.class);

	@Autowired
	private SampleDataLoadingFacade sampleDataLoadingFacade;

	@PersistenceContext
	private EntityManager em;

	@Test
	public void loadData() {
		sampleDataLoadingFacade.loadData();

		Musician musician = em.createQuery("SELECT m FROM Musician m WHERE m.name = :name", Musician.class)
			.setParameter("name", "Ed Sheeran")
			.getSingleResult();

		Assert.assertNotNull(musician);
		Assert.assertEquals("Ed Sheeran", musician.getName());
	}
}
