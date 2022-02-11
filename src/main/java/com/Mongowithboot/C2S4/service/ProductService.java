package com.Mongowithboot.C2S4.service;

import com.Mongowithboot.C2S4.domain.Product;
import com.Mongowithboot.C2S4.exception.ProductAlreadyExists;
import com.Mongowithboot.C2S4.exception.ProductNotFound;

import java.util.List;

public interface ProductService {
//    save data
    Product saveDetails(Product product) throws ProductAlreadyExists;
//    get the list of product
    List<Product> getAllProductDetails();
//    delete any record
    boolean deleteFromProduct(int pId) throws ProductNotFound;
//    update record
    boolean updateRecord(Product product) throws ProductNotFound;
//    get Product list by
    List<Product> getProductByBrand(String brand);
}

