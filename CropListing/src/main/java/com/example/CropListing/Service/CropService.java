package com.example.CropListing.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.CropListing.DTO.OrderLineItems;
import com.example.CropListing.Model.Crop;
import com.example.CropListing.Repository.CropRepository;

@Service
public class CropService {
	
	
	CropRepository cropRepository;
	
	public CropService(CropRepository cropRepository) {
        this.cropRepository = cropRepository;
    }
	
	public boolean addCrop(Crop crop) {
		
		Crop savedCrop=cropRepository.save(crop);
		return savedCrop!=null;
	}
	
	public Crop getCropById(String cropId) {
        return cropRepository.findById(cropId).orElse(null);
    }
	
	public void deleteCrop(Crop crop) {
        cropRepository.delete(crop);
    }
	
	 public Crop updateCrop(Crop crop) {
	        return cropRepository.save(crop);
	 }
	 
	 
	public boolean checkStockAvailability(List<OrderLineItems> items) {
		
        boolean allItemsInStock = true;
		
		for (int i = 0; i < items.size(); i++) {
			
			OrderLineItems orderItem = items.get(i);
			String cropId=orderItem.getCropId();
			int requiredQuantity=orderItem.getQuantity();
			Crop crop=cropRepository.findById(cropId).orElse(null);
			
			
			if(!(crop.getQuantity()>=requiredQuantity)) {
                allItemsInStock = false;
                break;
            }
        }
		
		return allItemsInStock;
	}
	
	
	public boolean reduceStock(List<OrderLineItems> items) {
			for (int i = 0; i < items.size(); i++) {
			
				OrderLineItems orderItem = items.get(i);
				String cropId=orderItem.getCropId();
				Optional<Crop> cropOptional = cropRepository.findById(cropId);
					if (cropOptional.isEmpty()) {
				        return false;
				    }
		    
			    Crop crop = cropOptional.get();
				int requiredQuantity=orderItem.getQuantity();
				int avilableQuantity=crop.getQuantity();
				crop.setQuantity(avilableQuantity-requiredQuantity);
				Crop crop1=cropRepository.save(crop);
			}
			return true;
			
        }
		
	}
	

