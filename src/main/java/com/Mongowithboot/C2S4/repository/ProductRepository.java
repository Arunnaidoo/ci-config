package com.Mongowithboot.C2S4.repository;

import com.Mongowithboot.C2S4.domain.Product;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product,Integer> {
    @Query("{'productDescription.brand':{$in : [?0]}}")
    List<Product> findAllByBrand(String brand);
}
