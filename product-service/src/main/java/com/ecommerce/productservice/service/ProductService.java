package com.ecommerce.productservice.service;

import com.ecommerce.productservice.entity.Product;
import com.ecommerce.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    public ProductRepository  productRepository;


    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductsById(Integer id){
        System.out.println("Calling getProductsById from OrderService ");
        return productRepository.findById(id).orElse(null);
    }

    public Product updateProductStock(Integer productId,Integer quantity) {
        System.out.println("updating productStock from OrderService :)");
        Product product =  productRepository.findById(productId).orElse(null);
        if(product != null) {
            product.setStock(product.getStock() - quantity);
            return productRepository.save(product);
        }
        return null;
    }

    public List<Product> saveProducts(List<Product> products) {
        return productRepository.saveAll(products);
    }

    public Product getProductsForNormalUser() {
        return new Product(1,"NormalProduct","Its a product for NormalUser",100.0,1,"Normal");
    }

    public Product getProductsForGoldenUser() {
        return new Product(3,"PrimeProduct","Its a product for Prime User",0.0,10,"Prime");
    }
}
