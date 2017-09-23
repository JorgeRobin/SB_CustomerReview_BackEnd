package com.customerreview.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.customerreview.Models.Product;

// This will be AUTO IMPLEMENTED by Spring into a Bean called productRepository
// CRUD refers Create, Read, Update, Delete

public interface ProductRepository extends CrudRepository<Product, Long> {

}
