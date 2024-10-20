package com.example.realestate.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.realestate.entity.Post;
import com.example.realestate.entity.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>,JpaSpecificationExecutor<Post> {

	List<Post> findByUserId(Long userId);

	List<Post> getPostsByUser(User user);
	/*
	 * @Query("SELECT p FROM Post p WHERE " +
	 * ":city IS NULL OR LOWER(p.city) LIKE LOWER(CONCAT('%',:city,'%')) AND " +
	 * " :bedroom IS NULL OR p.bedroom=:bedroom AND " +
	 * "  :type IS NULL OR LOWER(p.type) LIKE LOWER(CONCAT('%',:type,'%')) AND " +
	 * ":property IS NULL OR LOWER(p.property) LIKE LOWER(CONCAT('%',:property,'%'))"
	 * 
	 * ) List<Post> searchByQuery(@Param("city") String city, @Param("bedroom")
	 * Integer bedroom, @Param("type") String type,
	 * 
	 * @Param("property") String property
	 * 
	 * );
	 * 
	 * // List<Post> searchByQuery(SearchDto searchDto);
	 * 
	 * @Query("SELECT p FROM Post p WHERE " +
	 * "LOWER(p.city) LIKE LOWER(CONCAT('%',:city,'%')) "
	 * 
	 * ) List<Post> searchByCityQuery(@Param("city") String city
	 * 
	 * );
	 * 
	 * @Query("SELECT p FROM Post p WHERE " +
	 * "LOWER(p.type) LIKE LOWER(CONCAT('%',:type,'%'))"
	 * 
	 * ) List<Post> searchByTypeQuery(@Param("type") String type
	 * 
	 * );
	 * 
	 * 
	 * 
	 * List<Post> findByCity(String city); List<Post> findByBedroom(int bedroom);
	 * List<Post> findByType(String type); List<Post> findByProperty(String
	 * property);
	 */
}
