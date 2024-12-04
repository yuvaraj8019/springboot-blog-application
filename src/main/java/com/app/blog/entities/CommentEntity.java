package com.app.blog.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Comments")
@Getter
@Setter
@NoArgsConstructor
public class CommentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false, length=100)
	private String content;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "postId")
	private PostEntity post;

}
