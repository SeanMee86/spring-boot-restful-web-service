package com.seanmeedev.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

//import com.seanmeedev.rest.webservices.restfulwebservices.post.Post;
//import com.seanmeedev.rest.webservices.restfulwebservices.post.PostNotFoundException;


@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService service;
	
	@CrossOrigin
	@GetMapping(path="/users")
	public List<User> retrieveAllUsers(){
		return service.findAll();
	}
	
	@GetMapping(path="/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		if(user==null)
			throw new UserNotFoundException("id-"+id);
		
		EntityModel<User> resource = EntityModel.of(user);
		
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		resource.add(linkTo.withRel("all-users"));
		
		return resource;
	}
	
	@DeleteMapping(path="/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);
		if(user==null)
			throw new UserNotFoundException("id-"+id);
	}

//	@GetMapping(path="/users/{id}/posts")
//	public List<Post> retrieveAllUserPosts(@PathVariable int id) {
//		User user = service.findOne(id);
//		if(user==null)
//			throw new UserNotFoundException("id-"+id);
//		return user.getUserPosts();
//	}
	
//	@GetMapping(path="/users/{id}/posts/{post_id}")
//	public Post retrieveUsersPost(@PathVariable int id, @PathVariable int post_id) {
//		User user = service.findOne(id);
//		if(user==null)
//			throw new UserNotFoundException("userId-"+id);
//		Post userPost = service.findOnePost(user, post_id);
//		if(userPost==null)
//			throw new PostNotFoundException("postId-"+post_id);
//		return userPost;
//	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId())
			.toUri();
		return ResponseEntity.created(location).build();
	}

//	@PostMapping("/users/{id}/posts")
//	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
//		User user = service.findOne(id);
//		Post savedPost = service.savePost(user, post);
//		URI location = ServletUriComponentsBuilder
//				.fromCurrentRequest()
//				.path("/{postId}")
//				.buildAndExpand(savedPost.getPostId())
//				.toUri();
//		return ResponseEntity.created(location).build();
//	}
}
