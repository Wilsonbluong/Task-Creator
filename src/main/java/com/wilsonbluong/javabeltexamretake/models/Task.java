package com.wilsonbluong.javabeltexamretake.models;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="tasks")
public class Task {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Size(min = 2, max = 200, message="Task must be present")
    private String name;
    
	@Column(nullable=false)
	@Min(1)
	@Max(3)
	@NotNull(message="Task priority cannot be empty")
    private Integer priority;
    
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="creator_id")
    private User creator;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="assignee_id")
    @NotNull(message="assignee cannot be empty")
    private User assignee;

    // empty constructor
	public Task() {
    }
   
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Integer getPriority() {
		return priority;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
    public User getCreator() {
		return creator;
	}
	public User getAssignee() {
		return assignee;
	}



	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	public void setAssignee(User assignee) {
		this.assignee = assignee;
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
