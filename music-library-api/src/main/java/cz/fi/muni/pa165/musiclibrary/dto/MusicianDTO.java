package cz.fi.muni.pa165.musiclibrary.dto;

import java.util.Objects;

/**
 * @author Jan-Sebastian Fabík
 */
public class MusicianDTO {

	private Long id;

	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 17;
		int result = 1;
		result = prime * result + Objects.hashCode(this.name);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof MusicianDTO)) {
			return false;
		}
		MusicianDTO other = (MusicianDTO) obj;
		return Objects.equals(this.name, other.getName());
	}

	@Override
	public String toString() {
		return "MusicianDTO{" +
			"id=" + id +
			", name='" + name + "'" +
			"}";
	}
}
