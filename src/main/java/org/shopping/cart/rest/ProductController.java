package org.shopping.cart.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.shopping.cart.domain.Product;
import org.shopping.cart.service.ProductNotFoundException;
import org.shopping.cart.service.ProductService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class ProductController {
	
	
	@Autowired Logger log;
	
	@Autowired ProductService productService;

	
	@PostConstruct	
	public void init() throws Exception {
		log.info("Initializing Rest Controller...");
	}
	
	// HATEOAS - HyperMedia As The Engine Of Application State - Expose additional information to the REST responses
	
	@GetMapping("/products")
	public List<Product> getAllProducts(){
		
//		log.info());
		return productService.findAll();
		
		
	}
	
	@GetMapping("/products/{id}")
	public Resource<Product> getProductById(@PathVariable long id){
		
		
		Optional<Product> product = productService.findById(id);
			
		if(product.isPresent()) {


			Resource<Product> resource = new Resource<Product>(product.get());
			
			ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllProducts());
			
			resource.add(linkTo.withRel("all-products"));
			
			return resource;
			
//			return product.get();
		}
		else {
			// throws unchecked exception that is handled by the Spring REST Controller interfaces to generate specific HTTP response type
			throw new ProductNotFoundException(id); 
		}
	}
	
	@GetMapping("/products/name/{id}")
	public Product getProductByName(@PathVariable String name){
		
		Optional<Product> product = productService.findByName(name);
			
		if(product.isPresent()) return product.get();
		else {
			// throws unchecked exception that is handled by the Spring REST Controller interfaces to generate specific HTTP response type
			throw new ProductNotFoundException(name); 
		}
		
	}
	
	
	@PostMapping("/product")
	public ResponseEntity<Object> createProduct(@Valid @RequestBody Product product){
		Product savedProduct = productService.saveAndFlush(product);
		// Creates a created URI location
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedProduct.getId()).toUri();
		// Sets response code to 201 response code (HTTP best practice is to set response
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable long id){
		
		
		Optional<Product> product = productService.findById(id);
			
		if(product.isPresent()) {
			
			return ResponseEntity.noContent().build();
			
		}
		else {
			// throws unchecked exception that is handled by the Spring REST Controller interfaces to generate specific HTTP response type
//			
			
		}
		
		
		throw new ProductNotFoundException(id); 
//		return ResponseEntity.notFound().build();
	}
	
	
	
}
