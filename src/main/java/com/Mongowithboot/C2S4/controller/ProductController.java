package com.Mongowithboot.C2S4.controller;

import com.Mongowithboot.C2S4.domain.Product;
import com.Mongowithboot.C2S4.exception.ProductAlreadyExists;
import com.Mongowithboot.C2S4.exception.ProductNotFound;
import com.Mongowithboot.C2S4.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mongo")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product")
    public ResponseEntity<?> saveProduct(@RequestBody Product product) throws ProductAlreadyExists {
        return new ResponseEntity<>(productService.saveDetails(product), HttpStatus.CREATED);
    }
    @GetMapping("/products")
    public ResponseEntity<?> getProduct(){
        return new ResponseEntity<>(productService.getAllProductDetails(), HttpStatus.OK);
    }
    @DeleteMapping("/product/{pId}")
    public ResponseEntity<?> deleteProduct(@PathVariable int pId) throws ProductNotFound {
        return new ResponseEntity<>(productService.deleteFromProduct(pId), HttpStatus.OK);
    }
    @PutMapping("/updatedProduct")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) throws ProductNotFound {
        return new ResponseEntity<>(productService.updateRecord(product), HttpStatus.OK);
    }
    @GetMapping("/selectedProduct/{brand}")
    public ResponseEntity<?> getProductByBrand(@PathVariable String brand)
    {
        return new ResponseEntity<>(productService.getProductByBrand(brand),HttpStatus.OK);
    }


}
