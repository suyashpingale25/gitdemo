package com.example.CropListing.Controller;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.CropListing.DTO.InventoryResponse;
import com.example.CropListing.Model.Crop;
import com.example.CropListing.Repository.CropRepository;
import com.example.CropListing.Service.CropService;
import com.example.CropListing.DTO.OrderLineItems;

@RestController
@RequestMapping("/croplisting")
public class CropListingController {
	
	
	public CropListingController(CropService cropService) {
        this.cropService = cropService;
    }
	

	@Autowired
	private CropService cropService;
	
	@PostMapping("/CropListing")
	public ResponseEntity<String> cropListing(@RequestBody Crop crop){
	
		cropService.addCrop(crop);
		return ResponseEntity.ok("CropListed Successfully");
		
	}
	
	
	@PutMapping("/update/{cropId}")
    public ResponseEntity<?> updateCrop(@PathVariable String cropId, @RequestBody Crop updatedCrop) {
        Crop existingCrop = cropService.getCropById(cropId);
        if (existingCrop == null) {
            return ResponseEntity.notFound().build();
        }
        
        
        existingCrop.setQuantity(updatedCrop.getQuantity());
        existingCrop.setLocation(updatedCrop.getLocation());
       
        
        Crop updatedCropEntity = cropService.updateCrop(existingCrop);
        return ResponseEntity.ok("Updated Successfully");
    }
	
	
	@DeleteMapping("/delete/{cropId}")
    public ResponseEntity<?> deleteCrop(@PathVariable String cropId) {
        Crop crop = cropService.getCropById(cropId);
        if (crop == null) {
            return ResponseEntity.notFound().build();
        }
        
        cropService.deleteCrop(crop);
        return ResponseEntity.ok("Deleted Successfully");
    }
	
	@PostMapping("/check")
	public InventoryResponse isInStock(@RequestBody List<OrderLineItems> items){
		
		boolean allItemsInStock=cropService.checkStockAvailability(items);

        if (allItemsInStock) {
        	
        	InventoryResponse inventoryResponse = new InventoryResponse(true, "Items in stock");
            return inventoryResponse;
        } 
        else {
        	InventoryResponse inventoryResponse = new InventoryResponse(false, "Sorry!!!...not in stock");
            return inventoryResponse;
        }
		
	}
	
	@PostMapping("/placeOrder")
	public InventoryResponse placeOrder(@RequestBody List<OrderLineItems> items) {
		
		boolean result=cropService.reduceStock(items);
		
		if (result) {
        	InventoryResponse inventoryResponse = new InventoryResponse(true, "order Placed");
            return inventoryResponse;
        } 
        else {
        	InventoryResponse inventoryResponse = new InventoryResponse(false, "Sorry!!!...not placed");
            return inventoryResponse;
        }
		
	}
	
	
}
