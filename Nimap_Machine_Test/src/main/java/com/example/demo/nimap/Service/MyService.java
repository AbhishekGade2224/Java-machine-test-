package com.example.demo.nimap.Service;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.nimap.Entity.Category;
import com.example.demo.nimap.Entity.Product;
import com.example.demo.nimap.Repository.CategoryRepository;
import com.example.demo.nimap.Repository.ProductRepository;

@Service
public class MyService {
	@Autowired
	CategoryRepository CR;

	@Autowired
	ProductRepository PR;

	// create a new category
	public String saveinfo(Category C) {
		for (Product P : C.getProducts()) {
			P.setCategory(C); // add category_id in product table
		}

		CR.save(C); // add record in category table and cascade in student table
		return "Record Added Successfully..!!";
	}

//GET all Category

	public Page<Category> getAllCategories(Pageable pageable) {
		return CR.findAll(pageable);
	}

//GET category by Id
	public Category findbyid(int c) {
		return CR.findById(c).orElse(null);
	}

//Delete category by id
	public String deletebyid(int id) {
		if (CR.existsById(id)) {
			CR.deleteById(id);
			return "Data Deleted By Id";
		} else
			return "Data is Not Found By Id";
	}

//update category by id
	public String update(int id, Category s1) {
		Category s2 = CR.findById(id).orElse(null);
		if (s1.getCname() != null)
			s2.setCname(s1.getCname());
		CR.save(s2);
		return "update successfull";
	}

// 	GET all the products
	public Page<Product> getAllProducts(Pageable pageable) {
		return PR.findAll(pageable);
	}

//create a new product
	public String saveProduct(int categoryId, Product product) {
		Category category = CR.findById(categoryId).orElse(null);
		if (category == null) {
			return "Category with ID " + categoryId + " not found!";
		}

		product.setCategory(category); // Link product to the category

		PR.save(product); // Save the product to the database
		return "Product added successfully..!";
	}

//GET product by Id
	public Product findProductByid(int p) {
		return PR.findById(p).orElse(null);
	}

//Delete product by id
	public String deleteProductByid(int id) {
		if (PR.existsById(id)) {
			PR.deleteById(id);
			return "Product Data Deleted By Id";
		} else
			return " Product Data Not Found ";
	}

//update product by id
	public String updateProductByid(int id, Product p1) {
		Product p2 = PR.findById(id).orElse(null);
		if (p1.getPname() != null)
			p2.setPname(p1.getPname());
		if (p1.getPrice() != null)
			p2.setPrice(p1.getPrice());
		PR.save(p2);
		return "update successfull..!! ";
	}
}
