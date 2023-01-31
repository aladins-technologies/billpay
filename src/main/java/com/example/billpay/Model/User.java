package com.example.billpay.Model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.TableGenerator;

@Entity
public class User {
	
	@Id
	@TableGenerator(name="sequence", initialValue=10000, allocationSize=50)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="sequence")
    private int userID;

    private String name;

    @Column(unique = true)
    private String email;				//user name

    @Column(unique = true)
    private String mobile;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String pwd;

    private String role;
    
    @JsonIgnore
    @OneToMany(mappedBy="user",fetch=FetchType.EAGER)
    private Set<Authority> authorities;

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int userID, String name, String email, String mobile, String pwd, String role,
			Set<Authority> authorities) {
		super();
		this.userID = userID;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.pwd = pwd;
		this.role = role;
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", name=" + name + ", email=" + email + ", mobile=" + mobile + ", pwd=" + pwd
				+ ", role=" + role + ", authorities=" + authorities + "]";
	}

}