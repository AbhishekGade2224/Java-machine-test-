package com.example.demo.nimap.Controller;

import java.awt.print.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.nimap.Entity.Category;
import com.example.demo.nimap.Entity.Product;
import com.example.demo.nimap.Service.MyService;

@RestController
@RequestMapping("/api")
public class MyController {

	@Autowired
	MyService service;

	// GET all the categories
	@GetMapping("/categories")
	public Page<Category> getAllCategories(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size) {
		return service.getAllCategories( PageRequest.of(page, size));
	}

	// POST - create a new category
	@PostMapping("/categories")
	public String CreateNewcategories(@RequestBody Category C) {
		return service.saveinfo(C);
	}

	// GET category by Id
	@GetMapping("/categories/{di}")
	public Category getAllCategoriesById(@PathVariable("di") int c) {
		return service.findbyid(c);
	}

	// PUT - update category by id
	@PutMapping("/categories/{di}")
	public String UpdateCategoriesById(@PathVariable("di") int id, @RequestBody Category c1) {
		return service.update(id, c1);
	}

	// DELETE - Delete category by id
	@DeleteMapping("/categories/{di}")
	public String DeleteCategoriesById(@PathVariable("di") int id) {
		return service.deletebyid(id);
	}

	// GET all the products
	@GetMapping("/products")
	public Page<Product> getAllProducts(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size) {
		return service.getAllProducts(PageRequest.of(page, size));
	}

	// POST - create a new product
	@PostMapping("/products/{categoryId}")
	public String addProductToCategory(@PathVariable int categoryId, @RequestBody Product product) {
		return service.saveProduct(categoryId, product);
	}

	// GET product by Id
	@GetMapping("/products/{di}")
	public Product findProductById(@PathVariable("di") int p) {
		return service.findProductByid(p);
	}

	// PUT - update product by id
	@PutMapping("/products/{di}")
	public String UpdateProductByid(@PathVariable("di") int id, @RequestBody Product p1) {
		return service.updateProductByid(id, p1);
	}

//	 	DELETE - Delete product by id
	@DeleteMapping("/products/{di}")
	public String deleteProductByid(@PathVariable("di") int id) {
		return service.deleteProductByid(id);
	}

}
