package cz.fi.muni.pa165.musiclibrary.sampledata;

import java.io.IOException;

/**
 * Populates the database with sample data.
 */
public interface SampleDataLoadingFacade {

	void loadData() throws IOException;
}
