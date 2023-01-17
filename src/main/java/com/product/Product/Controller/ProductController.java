package com.product.Product.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.Product.Repository.ProductRepo;
import com.product.Product.model.Product;

@RestController
public class ProductController {
	
	@Autowired
	private ProductRepo productRepo;
	
	//Creating the Product
	@PostMapping("/product")
	public ResponseEntity<?> Create(@RequestBody Product pro)
	{
		productRepo.save(pro);
		return new ResponseEntity<>(pro , HttpStatus.OK);
	}
	
	//Reading the Product
	@GetMapping("/product")
	public ResponseEntity<?> Read()
	{
		List<Product> product = productRepo.findAll();
		if(product.size()!=0)
		{
			return new ResponseEntity<List<Product>>(product , HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("No Product Available" , HttpStatus.NOT_FOUND);
		}
		
	}
	
	//Reading the Product By Id
	@GetMapping("/product/{id}")
	public ResponseEntity<?> ReadById(@PathVariable String id)
	{
		Optional<Product> product = productRepo.findById(id);
		if(product.isPresent())
		{
			return new ResponseEntity<>(product.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<>("No Product With Such id : " + id , HttpStatus.NOT_FOUND);
		}
	}
	
	
	//Reading the Product By Id
	@DeleteMapping("/product/{id}")
	public ResponseEntity<?> DeleteById(@PathVariable String id)
	{
		Optional<Product> product = productRepo.findById(id);
		if(product.isPresent())
		{
			productRepo.deleteById(id);
			return new ResponseEntity<>("Delete Successfully "+id,HttpStatus.OK);
		}else {
			return new ResponseEntity<>("No Product With Such id : " + id , HttpStatus.NOT_FOUND);
		}
	}
	
	//Update the Product
	@PutMapping("/product/{id}")
	public ResponseEntity<?> UpdateById(@PathVariable String id , @RequestBody Product pro)
	{
		
		Optional<Product> product = productRepo.findById(id);
		if(product.isPresent())
		{
			Product productToSave = product.get();
			productToSave.setPrice(pro.getPrice() != 0 ? pro.getPrice() : productToSave.getPrice());
			productToSave.setProduct(pro.getProduct()!= null ? pro.getProduct() : productToSave.getProduct());
			productToSave.setId(pro.getId() != null ? pro.getId() : productToSave.getId());
			productRepo.save(productToSave);
			return new ResponseEntity<>("Successfully Upadate" , HttpStatus.OK);
		}else {
			return new ResponseEntity<>("No Element With Such Id : " + id , HttpStatus.NOT_FOUND);
		}
	}
	
	//Patch Method
	@PatchMapping("/product/{id}")
	public ResponseEntity<?> PatchById(@PathVariable String id , @RequestBody Product pro)
	{
		Optional<Product> product = productRepo.findById(id);
		if(product.isPresent())
		{
			Product productToSave = product.get();
			productToSave.setId(pro.getId() != null ? pro.getId() : productToSave.getId());
			productToSave.setPrice(pro.getPrice() != 0 ? pro.getPrice(): productToSave.getPrice());
			productToSave.setProduct(pro.getProduct() != null ? pro.getProduct() : productToSave.getProduct());
			productRepo.save(productToSave);
			return new ResponseEntity<>("Successfully Updated : " + id , HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>("No Product With Such Id : " + id , HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	
}
