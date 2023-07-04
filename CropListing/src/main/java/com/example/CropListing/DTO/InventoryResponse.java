package com.example.CropListing.DTO;

public class InventoryResponse {
	
	private boolean status ;
	private String message;
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public InventoryResponse(boolean status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
}
