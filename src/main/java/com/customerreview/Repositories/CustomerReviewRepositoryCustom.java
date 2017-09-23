package com.customerreview.Repositories;

import java.util.List;

import com.customerreview.ExceptionsAndErrors.CurseException;
import com.customerreview.ExceptionsAndErrors.RatingException;
import com.customerreview.Models.CustomerReview;

public interface CustomerReviewRepositoryCustom {

	public List<CustomerReview> getProductCustomerReviewsInRange(int productId, double fromRating, double toRating);
	public int totalProductCustomerReviewsInRange(int productId, double fromRating, double toRating);
	public int addCustomerReview(CustomerReview customerReview) throws CurseException, RatingException;
	public int updateCustomerReview(CustomerReview customerReview) throws CurseException, RatingException;
	
}
