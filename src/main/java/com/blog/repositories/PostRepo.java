package com.blog.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;

@EnableJpaRepositories
public interface PostRepo extends JpaRepository<Post, Integer> {

//	@Query("Select p from Post p where p.user_id= :userId") 
//	Page<Post> findByUser(@Param("userId")Integer userId,User user, Pageable p);
	
	List<Post> findByCategory(Category category);
	
	@Query("Select p from Post p where p.title like :key")
	List<Post> searchByTitle(@Param("key") String title);
//    List<Post> findByTitleContaining(@Param("key") String title);

}
