package demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "authorities")
public class UserRole {
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
	// "user_seq")
	// @SequenceGenerator(name = "user_seq", sequenceName = "seq_user")
	@Id
	@GeneratedValue
	private Long id;

	private String username;

	@Column(name = "authority")
	private String userRole;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
