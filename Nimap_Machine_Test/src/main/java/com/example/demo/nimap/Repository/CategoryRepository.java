package com.example.demo.nimap.Repository;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.nimap.Entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
