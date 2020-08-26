package com.seanmeedev.rest.webservices.restfulwebservices.user;

import java.util.Date;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

//import com.seanmeedev.rest.webservices.restfulwebservices.post.Post;

@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<>();
	
	private static int usersCount = 3;

	static {
		users.add(new User(1, "Adam", new Date()));
		users.add(new User(2, "Tom", new Date()));
		users.add(new User(3, "Dillon", new Date()));
		
//		for(User user:users) {
//			Post post1 = 
//					new Post(
//							new Date(), 
//							user.getName()+"'s First Post", 
//							String.format("My name is %s and this is my first post", user.getName()),
//							user.getUserPosts().size()+1
//						);
//			user.addPost(post1);
//			Post post2 = 
//					new Post(
//							new Date(), 
//							user.getName()+"'s Second Post", 
//							String.format("My name is %s and this is my second post", user.getName()),
//							user.getUserPosts().size()+1
//						);
//			user.addPost(post2);
//		}
		
	}
	
	public List<User> findAll() {
		return users;
	}

	public User save(User user) {
		if(user.getId()==null) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}
	
//	public Post savePost(User user, Post post) {
//		if(post.getPostId()==null) {
//			post.setPostId(user.getUserPosts().size()+1);
//		}
//		post.setTimestamp(new Date());
//		user.addPost(post);
//		return post;
//	}
	
	public User findOne(int id) {
		for(User user:users) {
			if(user.getId()==id) {
				return user;
			}
		}
		return null;
	}
	
//	public Post findOnePost(User user, int postId) {
//		for(Post post:user.getUserPosts()) {
//			if(post.getPostId()==postId)
//				return post;
//		}
//		return null;
//	}
	
	public User deleteById(int id) {
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
}
