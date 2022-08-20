package com.company.auth.app.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "my_roles", uniqueConstraints = { @UniqueConstraint(columnNames = "role_description") })
public class Role {

	@Id
	@Column(name = "role_name")
	private String roleName;

	@Column(name = "role_description")
	private String roleDescription;

	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
	private Set<User> users;

}
