package com.java.crudproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.crudproject.entities.Product;
import com.java.crudproject.exception.ProductNotFoundException;
import com.java.crudproject.service.BaseResponse;
import com.java.crudproject.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/addProduct")
    public ResponseEntity<BaseResponse<Product>> addProduct(@RequestBody Product product) {
        try {
            Product savedProduct = service.saveProduct(product);
            return new ResponseEntity<>(new BaseResponse<>(201, "Product added successfully", savedProduct), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new BaseResponse<>(500, "Error adding product", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addProducts")
    public ResponseEntity<BaseResponse<List<Product>>> addProducts(@RequestBody List<Product> products) {
        try {
            List<Product> savedProducts = service.saveProducts(products);
            return new ResponseEntity<>(new BaseResponse<>(201, "Products added successfully", savedProducts), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new BaseResponse<>(500, "Error adding products", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products")
    public ResponseEntity<BaseResponse<List<Product>>> findAllProducts() {
        try {
            List<Product> products = service.getProducts();
            return new ResponseEntity<>(new BaseResponse<>(200, "Products retrieved successfully", products), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new BaseResponse<>(500, "Error retrieving products", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/productById/{id}")
    public ResponseEntity<BaseResponse<Product>> findProductById(@PathVariable Long id) {
        try {
            Product product = service.getProductById(id);
            if (product != null) {
                return new ResponseEntity<>(new BaseResponse<>(200, "Product found", product), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new BaseResponse<>(404, "Product not found", null), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new BaseResponse<>(500, "Error retrieving product", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{name}")
    public ResponseEntity<BaseResponse<Product>> findProductByName(@PathVariable String name) {
        try {
            Product product = service.getProductByName(name);
            if (product != null) {
                return new ResponseEntity<>(new BaseResponse<>(200, "Product found", product), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new BaseResponse<>(404, "Product not found", null), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new BaseResponse<>(500, "Error retrieving product", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<BaseResponse<Product>> updateProduct(@RequestBody Product product) {
        try {
            Product updatedProduct = service.updateProduct(product);
            return new ResponseEntity<>(new BaseResponse<>(200, "Product updated successfully", updatedProduct), HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(new BaseResponse<>(404, e.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new BaseResponse<>(500, "Error updating product", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseResponse<String>> deleteProduct(@PathVariable Long id) {
        try {
            String result = service.deleteProduct(id);
            return new ResponseEntity<>(new BaseResponse<>(200, "Product deleted successfully", result), HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(new BaseResponse<>(404, e.getMessage(), null), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new BaseResponse<>(500, "Error deleting product", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<BaseResponse<String>> greet(HttpServletRequest request) {
        try {
            return new ResponseEntity<>(new BaseResponse<>(200, "Session retrieved successfully", "Hello Spring Security, Session ID: " + request.getSession().getId()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new BaseResponse<>(500, "Error retrieving session", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
