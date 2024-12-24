package com.satrtc.blog_app_apis.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class User {
	
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private int id;
@Column(name = "user_name",nullable = false, length = 150)
private String name;
private String email;
private String password;
private String about;
}
