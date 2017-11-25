package cz.fi.muni.pa165.musiclibrary.dto;

import java.util.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * @author Kovarik Tomas
 */
public class GenreCreateDTO
{
    private Long id;

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
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
		if (!(obj instanceof GenreCreateDTO)) {
			return false;
		}
		GenreCreateDTO other = (GenreCreateDTO) obj;
		return Objects.equals(this.name, other.getName());
	}

    @Override
	public String toString() {
		return "Genre{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}