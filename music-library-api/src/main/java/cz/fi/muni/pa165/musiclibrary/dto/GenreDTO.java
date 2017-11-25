package cz.fi.muni.pa165.musiclibrary.dto;

import java.util.*;
/**
 * @author Kovarik Tomas
 */
public class GenreDTO
{
    private Long id;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
		if (!(obj instanceof GenreDTO)) {
			return false;
		}
		GenreDTO other = (GenreDTO) obj;
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