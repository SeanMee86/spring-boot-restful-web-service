package com.seanmeedev.rest.webservices.restfulwebservices.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.seanmeedev.rest.webservices.restfulwebservices.post.Post;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="All details about the user.")
@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min=2, message="name should have at least 2 characters")
	@ApiModelProperty(notes="Name must have at least 2 characters.")
	private String name;
	
	@Past
	@ApiModelProperty(notes="Birthday should be in the past")
	private Date birthDate;
	
	@OneToMany(mappedBy="user")
	private List<Post> posts;
	
	protected User() {
		
	}

	public User(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("User [id=%s, name=%s, birthDate=%s]", id, name, birthDate);
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
}
