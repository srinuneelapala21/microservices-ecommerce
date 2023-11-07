package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.entity.Product;
import com.ecommerce.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;
    @GetMapping("/get-all-products")
    public List<Product> getAllProducts(){
        List<Product> allProducts = productService.getAllProducts();
        System.out.println(allProducts);
        return allProducts;
    }


    @GetMapping("/get-product/{productId}")
    public Product getProductById(@PathVariable(name = "productId") Integer id){
        return productService.getProductsById(id);
    }


    @PutMapping("/update-stock/product-id/{productId}/quantity/{quantity}")
    public Product updateStock(@PathVariable(name = "productId") Integer productId,@PathVariable(name="quantity") Integer quantity){
        return productService.updateProductStock(productId,quantity);
    }

    @PostMapping("/add-products")
    public List<Product> addProducts(@RequestBody List<Product> products){
        return productService.saveProducts(products);                           //Need to handle the duplicate products
    }

}
