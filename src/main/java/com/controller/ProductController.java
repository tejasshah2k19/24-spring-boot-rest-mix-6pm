package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.ProductEntity;
import com.repository.ProductRepository;

@RestController
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	// api
	// read product information and store into databse
	@PostMapping("/products")
	public ProductEntity addProduct(@RequestBody ProductEntity productEntity) {
		System.out.println(productEntity.getProductName());
		System.out.println(productEntity.getPrice());
		productRepository.save(productEntity);//insert -> id is not present 
		return productEntity;
	}

	// read all products information
	@GetMapping("/products")
	public List<ProductEntity> getAllProducts() {
		List<ProductEntity> products = productRepository.findAll();
		return products;
	}

	// productId -> fetch
	@GetMapping("/products/{productId}")
	public ProductEntity getProductById(@PathVariable("productId") Integer productId) {
		Optional<ProductEntity> op = productRepository.findById(productId);
		if (op.isEmpty()) {
			return null;
		} else {
			ProductEntity productEntity = op.get();
			return productEntity;
		}
	}

	@GetMapping("/productsbyid")
	public ProductEntity getProductById2(@RequestParam("productId") Integer productId) {
		Optional<ProductEntity> op = productRepository.findById(productId);
		if (op.isEmpty()) {
			return null;
		} else {
			ProductEntity productEntity = op.get();
			return productEntity;
		}
	}

	// api -> remove product from products table -> delete product
	// input : productId

	@DeleteMapping("/products/{productId}")
	public String deleteProductById(@PathVariable("productId") Integer productId) {
		Optional<ProductEntity> op = productRepository.findById(productId);

		if (op.isEmpty()) {
			return "NotFound";
		} else {
			productRepository.deleteById(productId);
			return "success";
		}
	}

	// update
//	input : all product info that we can modify  , productId must be present 
	@PutMapping("/products")
	public String updateProduct(@RequestBody ProductEntity productEntity) {
		productRepository.save(productEntity);//id is present 
		return "success";
	}
}

//http://localhost:9999/products 