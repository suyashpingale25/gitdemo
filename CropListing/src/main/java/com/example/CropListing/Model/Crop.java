package com.example.CropListing.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="crops")
public class Crop {
	
	@Id
	private String cropId;
	private String name;
	private String type;
	private int quantity;
	private String location;
	private String description;
	public String getName() {
		return name;
	}
	public String getId() {
		return cropId;
	}
	public void setId(String id) {
		this.cropId = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	

}
