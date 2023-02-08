package com.example.billpay.Model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "authorities")
@NoArgsConstructor
public class Authority {
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

	public Authority(String name, User user) {
		super();
		this.name = name;
		this.user = user;
	}
}