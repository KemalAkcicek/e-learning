package com.kemalakcicek.e_learning.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Categories extends BaseEntity {

	private String name;

	private String description;

	@OneToMany(mappedBy = "categories", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Course> courses;

}
