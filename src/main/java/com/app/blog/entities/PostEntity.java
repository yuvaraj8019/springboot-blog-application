package com.app.blog.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Posts")
@Getter
@Setter
@NoArgsConstructor
public class PostEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int postId;
	
	@Column(name="post_title", nullable=false, length=100)
	private String title;
	
	@Column(length=1000)
	private String content;
	
	private String imageName="default.png";
	
	private Date addedDate;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private UserEntity user;
	
	@ManyToOne
	@JoinColumn(name = "categoryId")
	private CategoryEntity category;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private Set<CommentEntity> comments = new HashSet<>();

}
