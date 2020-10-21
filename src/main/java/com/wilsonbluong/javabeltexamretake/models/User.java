package com.wilsonbluong.javabeltexamretake.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Size(min = 2, message="Name must be present")
    private String name;
    
    @Size(min = 1, message="Email must be present and valid format")
    @Email(message="Email must be present and valid format")
    private String email;
    
    @Size(min=8, message="Password must be 8 characters long")
    private String password;
    
    @Transient
    private String passwordConfirmation;
    
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    @OneToMany(mappedBy= "creator", fetch=FetchType.LAZY)
    private List<Task> tasks;   
    
    @Size(max=3)
    @OneToMany(mappedBy= "assignee", fetch=FetchType.LAZY)
    private List<Task> assignedTasks;

    
	public User() {
    }


	// getters
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public List<Task> getTasks() {
		return tasks;
	}
   public List<Task> getAssignedTasks() {
		return assignedTasks;
	}


	// setters
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	public void setAssignedTasks(List<Task> assignedTasks) {
		this.assignedTasks = assignedTasks;
	}

	
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

}
