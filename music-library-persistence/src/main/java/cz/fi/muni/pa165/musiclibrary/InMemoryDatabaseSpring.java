package cz.fi.muni.pa165.musiclibrary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import cz.fi.muni.pa165.musiclibrary.dao.AlbumDao;
import cz.fi.muni.pa165.musiclibrary.dao.GenreDao;
import cz.fi.muni.pa165.musiclibrary.dao.MusicianDao;
import cz.fi.muni.pa165.musiclibrary.dao.SongDao;


@Configuration
public class InMemoryDatabaseSpring {

	@Bean
	public DataSource db() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.DERBY).build();
		return db;
	}
}
