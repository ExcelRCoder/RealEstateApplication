package com.example.realestate.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private Double price;
	private String imageUrl;
	private String address;
	private String city;
	private int bedroom;
	private int bathroom;
	@Enumerated(EnumType.STRING)
	private Type type;

	@Enumerated(EnumType.STRING)
	private Property property;
	private String latitude;
	private String longitude;

	private String description;

	@Enumerated(EnumType.STRING)
	private Utilities utilities;

	@Enumerated(EnumType.STRING)
	private PetPolicy pet;

	private String income;
	private int size;
	private int school;
	private int bus;
	private int restaurant;
	@CreationTimestamp
	private LocalDateTime createdAt;

	enum Type {
		buy, rent
	}

	enum Property {
		apartment, house, condo, land
	}

	enum Utilities {
		Owner, Tenant, Shared

	}

	enum PetPolicy {
		allowed, notallowed,

	}

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")

	private User user;

}
