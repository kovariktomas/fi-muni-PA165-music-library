/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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

//	@OneToMany(mappedBy = "Album", cascade = CascadeType.REMOVE)
//	private List<Song> songs = new ArrayList<>();

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

//	public List<Song> getSongs() {
//		return Collections.unmodifiableList(songs);
//	}
//
//	public void setSongs(List<Song> songs) {
//		this.songs = songs;
//	}
//
//	public void addSong(Song song) {
//		this.songs.add(song);
//	}
//
//	public void removeSong(Song song) {
//		this.songs.remove(song);
//	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 37 * hash + Objects.hashCode(this.id);
		hash = 37 * hash + Objects.hashCode(this.releaseDate);
		hash = 37 * hash + Objects.hashCode(this.title);
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
		final Album other = (Album) obj;
		if (!Objects.equals(this.title, other.title)) {
			return false;
		}
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		if (!Objects.equals(this.releaseDate, other.releaseDate)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Album{" + "id=" + id + ", releaseDate=" + releaseDate +
				", title=" + title + '}';
	}
}
