package com.example.realestate.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Username is mandatory")
	@Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
	@Column(nullable = false, unique = true)
	private String username;

	@NotBlank(message = "Email is mandatory")
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
	@Column(nullable = false, unique = true)
	private String email;

	@NotBlank(message = "Password is mandatory")
	@Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
	@Column(nullable = false)
	private String password;
	
	 @CreationTimestamp
	 private LocalDateTime  createdAt;

	 	@JsonManagedReference
	    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private Set<Post> posts = new HashSet<>();
	 
	private String imageUrl;
}
