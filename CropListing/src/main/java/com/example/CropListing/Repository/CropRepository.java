package com.example.CropListing.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.CropListing.Model.Crop;
@Repository
public interface CropRepository extends MongoRepository<Crop, String> {
	
	Crop save(Crop crop);
}
