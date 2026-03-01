package com.project.sonica.photographyServices;

import org.springframework.data.annotation.Id;

import com.project.sonica.security.User;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class PhotographyServices {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String serviceName;
	private String description;
	private double price;
	private double discount; // percentage discount

	@ManyToOne
	@JoinColumn(name = "photographer_id")
	private User photographer; // link to photographer user

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public User getPhotographer() {
		return photographer;
	}

	public void setPhotographer(User photographer) {
		this.photographer = photographer;
	}

}
