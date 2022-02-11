package repository;

import com.Mongowithboot.C2S4.domain.Product;
import com.Mongowithboot.C2S4.domain.ProductDescription;
import com.Mongowithboot.C2S4.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;
    private Product product;
    private ProductDescription productDescription;

    @BeforeEach
    public void setUp() {
        product = new Product(201,"Table",3100,productDescription);
        productDescription =new ProductDescription(3,"Plywood",3,"21x23x31");
    }
    @AfterEach
    public void tearDown(){

        product = null;
        productDescription = null;
        productRepository.deleteAll();
    }
    @Test
    public void givenProductToSaveShouldReturnProduct(){
        productRepository.insert(product);
        Product product1 = productRepository.findById(product.getpID()).get();
        assertNotNull(product1);
        assertEquals(product.getpID(),product1.getpID());
    }

    @Test
    public void givenProductToDeleteReturnProduct()
    {
        productRepository.insert(product);
        Product product1 = productRepository.findById(product.getpID()).get();

        productRepository.delete(product1);
       assertEquals(Optional.empty(),productRepository.findById(product1.getpID()));
    }

    @Test
    public void givenProductReturnAllTrack()
    {
        productRepository.insert(product);
         ProductDescription productDescription1 =new ProductDescription(2,"demo",2,"22x11x10");
         Product product1 =new Product(202,"chair",2000,productDescription1);
         productRepository.insert(product1);
         List<Product> list = productRepository.findAll();
         assertEquals(2,list.size());
         assertEquals("chair",list.get(1).getName());
         assertEquals(201,list.get(0).getpID());
         assertEquals(2000,list.get(1).getPrice());

    }
}
