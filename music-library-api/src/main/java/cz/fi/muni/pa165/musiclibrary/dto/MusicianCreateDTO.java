package cz.fi.muni.pa165.musiclibrary.dto;

import java.util.Objects;

/**
 * @author Jan-Sebastian Fabík
 */
public class MusicianCreateDTO {

	private String name;

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
		if (!(obj instanceof MusicianCreateDTO)) {
			return false;
		}
		MusicianCreateDTO other = (MusicianCreateDTO) obj;
		return Objects.equals(this.name, other.getName());
	}

	@Override
	public String toString() {
		return "MusicianCreateDTO{" +
			"name='" + name + "'" +
			"}";
	}
}
