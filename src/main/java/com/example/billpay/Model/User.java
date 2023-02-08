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
import lombok.Data;

@Data
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
}