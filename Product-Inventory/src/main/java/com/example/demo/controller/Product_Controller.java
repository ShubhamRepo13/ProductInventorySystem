package com.example.demo.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.service.Product_Service;

@RestController
@RequestMapping("/product")
public class Product_Controller {
	@Autowired
	private Product_Service service;
	
	@PostMapping("/save")
	public ResponseEntity<String> Save_product(@RequestBody Product p)
	{
		service.SaveProduct(p);
		return new ResponseEntity<String>("Product Saved", HttpStatus.OK);
	}
	
	@GetMapping("/getProducts")
	public ResponseEntity<List<Product>> getAll()
	{
		List<Product> list= service.getAll();
		return ResponseEntity.ok(list);
	}
	
	@DeleteMapping("/delete/{ProductId}")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer id)
	{
		ResponseEntity<String> resp = null;
		try {
			service.DeleteProduct(id);
			resp=ResponseEntity.ok("Product Deleted");
		}catch(Exception e)
		{
		resp=new ResponseEntity<>("unable to delete product",HttpStatus.INTERNAL_SERVER_ERROR);
		e.printStackTrace();
		}
		return resp;

	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateProduct(@PathVariable Integer id,@RequestBody Product product)
	{
		ResponseEntity<String> resp=null;
		try {
			if(service.isProductExist(id))
			{
				product.setProduct_id(id);
				service.UpdateProduct(product);
				return ResponseEntity.ok("Product updated");
			}else
			{
				throw new Exception();
			}


		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;

	}

}
