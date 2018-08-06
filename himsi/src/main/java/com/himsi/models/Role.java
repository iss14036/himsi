package com.himsi.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="t_role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	private String deskripsi_role;
	@OneToMany(mappedBy = "role")
    private Set<User> user;
	
	@Version
	@Column(name = "optVersion", columnDefinition="integer DEFAULT 0")
	private Integer version;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeskripsi_role() {
		return deskripsi_role;
	}

	public void setDeskripsi_role(String deskripsi_role) {
		this.deskripsi_role = deskripsi_role;
	}
	
	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	
}
