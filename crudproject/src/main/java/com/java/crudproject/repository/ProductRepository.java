package com.java.crudproject.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.java.crudproject.entities.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
	
	 
   Product findByName(String name);

}
