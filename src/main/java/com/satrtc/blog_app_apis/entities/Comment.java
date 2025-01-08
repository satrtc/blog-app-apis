package com.satrtc.blog_app_apis.entities;

import java.util.Date;

import org.hibernate.annotations.ManyToAny;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
@Table(name = "comments")

public class Comment {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int commentId;
private String content;
private Date commentDate;

@ManyToOne
private User user;

@ManyToOne
private Post post;

}
