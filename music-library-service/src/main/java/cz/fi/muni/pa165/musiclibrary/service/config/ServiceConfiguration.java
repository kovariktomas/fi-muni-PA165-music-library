package cz.fi.muni.pa165.musiclibrary.service.config;

import cz.fi.muni.pa165.musiclibrary.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.musiclibrary.dto.AlbumDTO;
import cz.fi.muni.pa165.musiclibrary.dto.GenreDTO;
import cz.fi.muni.pa165.musiclibrary.dto.MusicianDTO;
import cz.fi.muni.pa165.musiclibrary.dto.SongDTO;
import cz.fi.muni.pa165.musiclibrary.entity.Album;
import cz.fi.muni.pa165.musiclibrary.entity.Genre;
import cz.fi.muni.pa165.musiclibrary.entity.Musician;
import cz.fi.muni.pa165.musiclibrary.entity.Song;
import cz.fi.muni.pa165.musiclibrary.facade.*;
import cz.fi.muni.pa165.musiclibrary.service.*;
import cz.fi.muni.pa165.musiclibrary.service.facade.AlbumFacadeImpl;
import cz.fi.muni.pa165.musiclibrary.service.facade.ApplicationUserFacadeImpl;
import cz.fi.muni.pa165.musiclibrary.service.facade.GenreFacadeImpl;
import cz.fi.muni.pa165.musiclibrary.service.facade.SongFacadeImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan(basePackageClasses = {
	AlbumServiceImpl.class,
	ApplicationUserServiceImpl.class,
	GenreServiceImpl.class,
	MusicianServiceImpl.class,
	SongServiceImpl.class,
	AlbumFacade.class,
	ApplicationUserFacade.class,
	GenreFacade.class,
	MusicianFacade.class,
	SongFacade.class,
	AlbumFacadeImpl.class,
	ApplicationUserFacadeImpl.class,
	GenreFacadeImpl.class,
	MusicianServiceImpl.class,
	SongFacadeImpl.class
}, basePackages = {
	"cz.fi.muni.pa165.musiclibrary.facade",
	"cz.fi.muni.pa165.musiclibrary.service",
	"cz.fi.muni.pa165.musiclibrary.service.facade"
})
public class ServiceConfiguration {


	@Bean
	public Mapper dozer() {
		DozerBeanMapper dozer = new DozerBeanMapper();
		dozer.addMapping(new DozerCustomConfig());
		return dozer;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Custom config for Dozer if needed
	 *
	 * @author nguyen
	 */
	public class DozerCustomConfig extends BeanMappingBuilder {
		@Override
		protected void configure() {
			mapping(Album.class, AlbumDTO.class);
			mapping(Genre.class, GenreDTO.class);
			mapping(Musician.class, MusicianDTO.class);
			mapping(Song.class, SongDTO.class);
		}
	}

}

