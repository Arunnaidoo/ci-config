package service;

import com.Mongowithboot.C2S4.domain.Product;
import com.Mongowithboot.C2S4.domain.ProductDescription;
import com.Mongowithboot.C2S4.exception.ProductAlreadyExists;
import com.Mongowithboot.C2S4.exception.ProductNotFound;
import com.Mongowithboot.C2S4.repository.ProductRepository;
import com.Mongowithboot.C2S4.service.ProductServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductServiceImpl productService;
    private Product product1,product2;
    List<Product> list;
    private ProductDescription productDescription1,productDescription2;

    @BeforeEach
    public void setUp(){
        product1 = new Product(201,"Table",3100,productDescription1);
        productDescription1 =new ProductDescription(3,"Plywood",3,"21x23x31");
        product2 =new Product(202,"chair",2000,productDescription2);
        productDescription2 =new ProductDescription(2,"demo",2,"22x11x10");
        list= Arrays.asList(product1,product2);
    }
    @AfterEach
    public void tearDown()
    {
        product1=null;
        product2 = null;

    }
    @Test
    public void givenProductToSaveReturnSavedProductSuccess() throws ProductAlreadyExists {
        when(productRepository.findById(product1.getpID())).thenReturn(Optional.ofNullable(null));
        when(productRepository.save(any())).thenReturn(product1);
        assertEquals(product1,productService.saveDetails(product1));
        verify(productRepository,times(1)).save(any());
        verify(productRepository,times(1)).findById(any());

    }

    @Test
    public void givenCustomerToSaveReturnCustomerFailure(){
        when(productRepository.findById(product1.getpID())).thenReturn(Optional.ofNullable(product1));
        assertThrows(ProductAlreadyExists.class,()->productService.saveDetails(product1));
        verify(productRepository,times(0)).save(any());
        verify(productRepository,times(1)).findById(any());
    }

    @Test
    public void givenCustomerToDeleteShouldDeleteSuccess() throws ProductNotFound {
        when(productRepository.findById(product1.getpID())).thenReturn(Optional.ofNullable(product1));
        boolean flag = productService.deleteFromProduct(product1.getpID());
        assertEquals(true, flag);

        verify(productRepository, times(1)).deleteById(any());
        verify(productRepository, times(1)).findById(any());

    }
}
