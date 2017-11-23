package cz.fi.muni.pa165.musiclibrary.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @author Jan-Sebastian Fabík
 */
@Entity
public class Song {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Musician musician;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Album album;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Genre genre;

	@NotNull
	@Column(nullable = false)
	private String title;

	@NotNull
	@Column(nullable = false)
	private Integer bitrate;

	@NotNull
	@Column(nullable = false)
	private Integer position;

	private String commentary;

	public Song() {
	}

	public Song(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Musician getMusician() {
		return musician;
	}

	public void setMusician(Musician musician) {
		this.musician = musician;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
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

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 37 * hash + Objects.hashCode(this.musician);
		hash = 37 * hash + Objects.hashCode(this.album);
		hash = 37 * hash + Objects.hashCode(this.genre);
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
		if (!(obj instanceof Song)) {
			return false;
		}
		final Song other = (Song) obj;
		return Objects.equals(this.musician, other.getMusician()) &&
				Objects.equals(this.album, other.getAlbum()) &&
				Objects.equals(this.genre, other.getGenre()) &&
				Objects.equals(this.title, other.getTitle()) &&
				Objects.equals(this.bitrate, other.getBitrate()) &&
				Objects.equals(this.position, other.getPosition()) &&
				Objects.equals(this.commentary, other.getCommentary());
	}

	@Override
	public String toString() {
		return "Song{id=" + id +
				", musician=" + (musician != null ? "#" + musician.getId() : "null") +
				", album=" + (album != null ? "#" + album.getId() : "null") +
				", genre=" + (genre != null ? "#" + genre.getId() : "null") +
				", title=" + title +
				", bitrate=" + bitrate +
				", position=" + position +
				", commentary=" + commentary + "}";
	}
}
