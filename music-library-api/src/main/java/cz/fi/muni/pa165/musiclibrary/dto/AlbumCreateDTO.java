package cz.fi.muni.pa165.musiclibrary.dto;

import java.util.*;

public class AlbumCreateDTO {

	private Date releaseDate;

	private String title;

	private String commentary;

	private byte[] albumArt;

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCommentary() {
		return commentary;
	}

	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}

	public byte[] getAlbumArt() {
		return albumArt;
	}

	public void setAlbumArt(byte[] albumArt) {
		this.albumArt = albumArt;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 37 * hash + Objects.hashCode(this.releaseDate);
		hash = 37 * hash + Objects.hashCode(this.title);
		hash = 37 * hash + Objects.hashCode(this.commentary);
		hash = 37 * hash + Arrays.hashCode(this.albumArt);
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
		if (!(obj instanceof AlbumCreateDTO)) {
			return false;
		}
		final AlbumCreateDTO other = (AlbumCreateDTO) obj;
		return Objects.equals(this.releaseDate, other.getReleaseDate()) &&
			Objects.equals(this.title, other.getTitle()) &&
			Objects.equals(this.commentary, other.getCommentary()) &&
			Arrays.equals(this.albumArt, other.getAlbumArt());
	}
}
