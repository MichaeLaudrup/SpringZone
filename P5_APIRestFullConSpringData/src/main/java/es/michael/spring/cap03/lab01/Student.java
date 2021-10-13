package es.michael.spring.cap03.lab01;

import org.springframework.stereotype.Component;

@Component
public class Student {
	private String id; 
	private String name;
	private String email; 
	private String password; 
	
	public Student() {}
	public Student (String id, String name) {
		this.id = id; 
		this.name = name; 
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	} 
}
