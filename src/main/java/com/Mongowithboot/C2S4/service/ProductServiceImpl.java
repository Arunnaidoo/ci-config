package com.Mongowithboot.C2S4.service;

import com.Mongowithboot.C2S4.domain.Product;
import com.Mongowithboot.C2S4.exception.ProductAlreadyExists;
import com.Mongowithboot.C2S4.exception.ProductNotFound;
import com.Mongowithboot.C2S4.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveDetails(Product product)throws ProductAlreadyExists {
        if(productRepository.findById(product.getpID()).isPresent())
        {
            throw new ProductAlreadyExists();
        }
        else

        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProductDetails() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public boolean deleteFromProduct(int pId) throws ProductNotFound {
        boolean flag = false;
        if(productRepository.findById(pId).isEmpty())
        {
            throw new ProductNotFound();
        }
        else {
            productRepository.deleteById(pId);
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean updateRecord(Product product) throws ProductNotFound {
        if(productRepository.findById(product.getpID()).isEmpty())
        {
         throw new ProductNotFound();
        }
        else
        productRepository.save(product);
        return true;
    }

    @Override
    public List<Product> getProductByBrand(String brand) {
        return productRepository.findAllByBrand(brand);
    }
}
