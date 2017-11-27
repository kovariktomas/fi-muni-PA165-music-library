package cz.fi.muni.pa165.musiclibrary.dto;

import java.util.Objects;

/**
 * @author David
 */
public class SongDTO {
	private Long id;

	private MusicianDTO musician;

	private AlbumDTO album;

	private GenreDTO genre;

	private String title;

	private Integer bitrate;

	private Integer position;

	private String commentary;

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 19 * hash + Objects.hashCode(this.id);
		hash = 19 * hash + Objects.hashCode(this.title);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final SongDTO other = (SongDTO) obj;
		if (!Objects.equals(this.title, other.title)) {
			return false;
		}
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MusicianDTO getMusician() {
		return musician;
	}

	public void setMusician(MusicianDTO musician) {
		this.musician = musician;
	}

	public AlbumDTO getAlbum() {
		return album;
	}

	public void setAlbum(AlbumDTO album) {
		this.album = album;
	}

	public GenreDTO getGenre() {
		return genre;
	}

	public void setGenre(GenreDTO genre) {
		this.genre = genre;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getBitrate() {
		return bitrate;
	}

	public void setBitrate(Integer bitrate) {
		this.bitrate = bitrate;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getCommentary() {
		return commentary;
	}

	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}


}
