package cz.fi.muni.pa165.musiclibrary.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @author Kovarik Tomas
 */
@Entity
public class ApplicationUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(nullable = false)
	private String name;

	@NotNull
	@Column(nullable = false, unique = true)
	private String email;

	@NotNull
	@Column(nullable = false)
	private String passHash;

	@NotNull
	@Column(nullable = false)
	private String role;

	public ApplicationUser() {
	}

	public ApplicationUser(Long genreId) {
		this.id = genreId;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassHash() {
		return passHash;
	}

	public void setPassHash(String passHash) {
		this.passHash = passHash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Objects.hashCode(this.email);
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
		if (!(obj instanceof ApplicationUser)) {
			return false;
		}
		ApplicationUser other = (ApplicationUser) obj;
		return Objects.equals(this.email, other.getEmail());
	}

	@Override
	public String toString() {
		return "User{" +
			"id=" + id +
			", name='" + name + '\'' +
			", email='" + email + '\'' +
			", passHash='" + passHash + '\'' +
			", role='" + role + '\'' +
			'}';
	}
}
