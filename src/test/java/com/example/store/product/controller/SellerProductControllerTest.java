package com.example.store.product.controller;

import com.example.store.product.domain.Product;
import com.example.store.product.dto.ProductCreateRequest;
import com.example.store.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SellerProductController.class)
@AutoConfigureMockMvc(addFilters = false) // Security 필터 비활성화: 401 방지
class SellerProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    ProductService productService;

    @Test
    void create_returns201_andProductId() throws Exception {
        // given
        Product saved = new Product(1L, "Keyboard", "mechanical keyboard", 59000L, 10);
        setId(saved, 1L);

        Mockito.when(productService.create(Mockito.any(ProductCreateRequest.class)))
                .thenReturn(saved);

        // when / then
        mockMvc.perform(post("/seller/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                          "sellerId": 1,
                          "name": "Keyboard",
                          "description": "mechanical keyboard",
                          "price": 59000,
                          "stock": 10
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.sellerId").value(1))
                .andExpect(jsonPath("$.name").value("Keyboard"))
                .andExpect(jsonPath("$.price").value(59000))
                .andExpect(jsonPath("$.stock").value(10))
                .andExpect(jsonPath("$.status").value("ACTIVE"));
    }

    @Test
    void create_returns400_whenNameIsBlank() throws Exception {
        mockMvc.perform(post("/seller/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                          "sellerId": 1,
                          "name": "",
                          "description": "mechanical keyboard",
                          "price": 59000,
                          "stock": 10
                        }
                        """))
                .andExpect(status().isBadRequest());
    }

    private static void setId(Product product, Long id) throws Exception {
        Field idField = Product.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(product, id);
    }
}
