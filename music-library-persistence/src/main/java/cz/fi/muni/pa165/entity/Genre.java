package cz.fi.muni.pa165.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Kovarik Tomas
 */
@Entity
public class Genre {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(nullable=false,unique=true)
	private String name;

//	@OneToMany()
//	@OrderBy("title DESC")
//	@JoinColumn(name="Song_FK")
//	private List<Song> songs new ArrayList<Song>();
	
	public void setId(Long id){
		this.id = id;
	}
	
	public Genre(Long genreId) {
		this.id = genreId;
	}
	public Genre() {
	}

//	public void addSong(Song s){
//		songs.add(s);
//	}

//	public List<Songse> getSongs() {
//		return Collections.unmodifiableList(songs);
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Genre))
			return false;
		Genre other = (Genre) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.getName()))
			return false;
		return true;
	}

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
 //               ", songs" + songs +
                '}';
    }
}
