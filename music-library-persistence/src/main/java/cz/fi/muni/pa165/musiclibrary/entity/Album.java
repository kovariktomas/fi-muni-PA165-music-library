package cz.fi.muni.pa165.musiclibrary.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * Class representing musical Album.
 *
 * @author Iva Liberova
 */
@Entity
public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(nullable = false)
	private Date releaseDate;

	@NotNull
	@Column(nullable = false, unique = true)
	private String title;

	private String commentary;

	private byte[] albumArt;

	@OneToMany(mappedBy = "album", cascade = CascadeType.REMOVE)
	private List<Song> songs = new ArrayList<>();

	public Album() {
	}

	public Album(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public List<Song> getSongs() {
		return Collections.unmodifiableList(songs);
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public void addSong(Song song) {
		this.songs.add(song);
	}

	public void removeSong(Song song) {
		this.songs.remove(song);
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
		if (!(obj instanceof Album)) {
			return false;
		}
		final Album other = (Album) obj;
		return Objects.equals(this.releaseDate, other.getReleaseDate()) &&
			Objects.equals(this.title, other.getTitle()) &&
			Objects.equals(this.commentary, other.getCommentary()) &&
			Arrays.equals(this.albumArt, other.getAlbumArt());
	}

	@Override
	public String toString() {
		return "Album{" + "id=" + id + ", releaseDate=" + releaseDate +
			", title=" + title + '}';
	}
}
