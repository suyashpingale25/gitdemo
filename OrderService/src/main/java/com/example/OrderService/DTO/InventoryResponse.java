package com.example.OrderService.DTO;

public class InventoryResponse {
	
	private boolean status;
    private String message;

    public InventoryResponse() {
    }

    public InventoryResponse(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

	public boolean getStatus() {
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
}
