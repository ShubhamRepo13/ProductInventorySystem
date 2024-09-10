package com.example.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repo.Product_Repo;

@Service
public class Product_Service {
	
	@Autowired
	private Product_Repo repo;
	
public Product SaveProduct(Product p)
{
	Product p1= repo.save(p);
	return p1;
}

public List<Product> getAll()
{
	List<Product> list= repo.findAll();
	return list;
}

public void UpdateProduct(Product product)
{
	 repo.save(product);
}

public void DeleteProduct(int product_id)
{
	repo.deleteById(product_id);
}

public String SearchProduct(int product_id)
{
	boolean flag=repo.existsById(product_id);
	if(flag==true)
	{
		return "Product Found";
	}else
	{
		return "Product Not Existed";
	}
	
}

public boolean isProductExist(Integer pid) {
	repo.existsById(pid);
	return false;
}
}
