package cz.fi.muni.pa165.musiclibrary.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class SongCreateDTO {

	private Long musicianId;

	private Long albumId;

	private Long genreId;

	@NotNull
	private String title;

	@NotNull
	@Min(value = 1, message = "The value must be positive.")
	private Integer bitrate;

	@NotNull
	@Min(value = 0, message = "The value must be non-negative.")
	private Integer position;

	private String commentary;

	public Long getMusicianId() {
		return musicianId;
	}

	public void setMusicianId(Long musicianId) {
		this.musicianId = musicianId;
	}

	public Long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

	public Long getGenreId() {
		return genreId;
	}

	public void setGenreId(Long genreId) {
		this.genreId = genreId;
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

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 37 * hash + Objects.hashCode(this.musicianId);
		hash = 37 * hash + Objects.hashCode(this.albumId);
		hash = 37 * hash + Objects.hashCode(this.genreId);
		hash = 37 * hash + Objects.hashCode(this.title);
		hash = 37 * hash + Objects.hashCode(this.bitrate);
		hash = 37 * hash + Objects.hashCode(this.position);
		hash = 37 * hash + Objects.hashCode(this.commentary);
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
		if (!(obj instanceof SongCreateDTO)) {
			return false;
		}
		SongCreateDTO other = (SongCreateDTO) obj;
		return Objects.equals(this.musicianId, other.getMusicianId()) &&
			Objects.equals(this.albumId, other.getAlbumId()) &&
			Objects.equals(this.genreId, other.getGenreId()) &&
			Objects.equals(this.title, other.getTitle()) &&
			Objects.equals(this.bitrate, other.getBitrate()) &&
			Objects.equals(this.position, other.getPosition()) &&
			Objects.equals(this.commentary, other.getCommentary());
	}
}
