package com.customerreview.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.customerreview.Models.CustomerReview;

// This will be AUTO IMPLEMENTED by Spring into a Bean called customerReviewRepository
// CRUD refers Create, Read, Update, Delete

public interface CustomerReviewRepository extends CrudRepository<CustomerReview, Long>, CustomerReviewRepositoryCustom {
		
}
