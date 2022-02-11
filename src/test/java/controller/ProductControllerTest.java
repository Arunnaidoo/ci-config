package controller;

import com.Mongowithboot.C2S4.controller.ProductController;
import com.Mongowithboot.C2S4.domain.Product;
import com.Mongowithboot.C2S4.domain.ProductDescription;
import com.Mongowithboot.C2S4.exception.ProductAlreadyExists;
import com.Mongowithboot.C2S4.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProductService productService;
    private Product product1,product2;
    List<Product> list;
    private ProductDescription productDescription1,productDescription2;

    @InjectMocks
    private ProductController productController;


    @BeforeEach
    public void setUp(){
        product1 = new Product(201,"Table",3100,productDescription1);
        productDescription1 =new ProductDescription(3,"Plywood",3,"21x23x31");
        product2 =new Product(202,"chair",2000,productDescription2);
        productDescription2 =new ProductDescription(2,"demo",2,"22x11x10");
        list= Arrays.asList(product1,product2);

        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }
    @AfterEach
    public void tearDown()
    {
        product1=null;
        product2 = null;

    }
    @Test
    public void givenProductToSaveReturnSaveProductSuccess() throws Exception {
        when(productService.saveDetails(any())).thenReturn(product1);
        mockMvc.perform(post("/mongo/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(product1)))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
        verify(productService,times(1)).saveDetails(any());
    }
    @Test
    public void givenCustomerToSaveReturnSaveProductFailure() throws Exception {
        when(productService.saveDetails(any())).thenThrow(ProductAlreadyExists.class);
        mockMvc.perform(post("/mongo/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(product1)))
                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
        verify(productService, times(1)).saveDetails(any());
    }

    private static String jsonToString(final Object ob) throws JsonProcessingException {
        String result;

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = mapper.writeValueAsString(ob);
            result = jsonContent;
        } catch(JsonProcessingException e) {
            result = "JSON processing error";
        }

        return result;
    }


}
