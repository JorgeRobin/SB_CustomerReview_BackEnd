package com.customerreview.Repositories;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.customerreview.ExceptionsAndErrors.CurseException;
import com.customerreview.ExceptionsAndErrors.RatingException;
import com.customerreview.Models.CustomerReview;

@Repository
@Transactional(readOnly = false)
public class CustomerReviewRepositoryImpl implements CustomerReviewRepositoryCustom {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	private ApplicationContext appContext;
	
	@SuppressWarnings("unchecked")
	public List<CustomerReview> getProductCustomerReviewsInRange(int productId, double fromRating, double toRating) {
		
		List<CustomerReview> list = null;
		Query query = entityManager.createNativeQuery("SELECT * FROM customerreview as cr " +
                "WHERE cr.productId = ?1 and cr.rating >= ?2 and cr.rating <= ?3", CustomerReview.class);
		
        query.setParameter(1, productId);
        query.setParameter(2, fromRating);
        query.setParameter(3,  toRating);
        System.out.println(entityManager.toString());
        list = query.getResultList();
		
		return list;
	};
	
	@SuppressWarnings("unchecked")
	public int totalProductCustomerReviewsInRange(int productId, double fromRating, double toRating) {
		
		List<CustomerReview> list = null;
		Query query = entityManager.createNativeQuery("SELECT * FROM customerreview as cr " +
                "WHERE cr.productId = ?1 and cr.rating >= ?2 and cr.rating <= ?3", CustomerReview.class);
		
        query.setParameter(1, productId);
        query.setParameter(2, fromRating);
        query.setParameter(3,  toRating);
        System.out.println(entityManager.toString());
        list = query.getResultList();
		
		return list.size();
	};
	
	public int addCustomerReview(CustomerReview customerReview) throws CurseException, RatingException {
		int result = -1;
		// get curse words from resources file
		Resource resource = appContext.getResource("classpath:curseWords.txt");
		List<String> lines = new ArrayList<String>();
		try {
			lines = Files.readAllLines(Paths.get(resource.getURI()),
			        StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
			return result;
		}
		// check for curse words
		String comment = customerReview.getComment();
		double rating = customerReview.getRating();
		String[] curseWords = new String[lines.size()];
		curseWords = lines.toArray(curseWords);
		System.out.println(curseWords.toString());
		boolean curse = Arrays.stream(curseWords).parallel().anyMatch(comment::contains);
		if (curse) throw new CurseException(comment);
		else if (rating < 0) {
			throw new RatingException(rating);
		} else {
			Query query = entityManager.createNativeQuery(
					"INSERT INTO customerreview (headline, comment, rating, blocked, alias, approvalStatus, language, userId, productId) " +
	                " VALUES(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9)", CustomerReview.class);
			query.setParameter(1, customerReview.getHeadline());
			query.setParameter(2, customerReview.getComment());
			query.setParameter(3, customerReview.getRating());
			query.setParameter(4, customerReview.getBlocked());
			query.setParameter(5, customerReview.getAlias());
			query.setParameter(6, customerReview.getApprovalStatus());
			query.setParameter(7, customerReview.getLanguage());
			query.setParameter(8, customerReview.getUserId());
			query.setParameter(9, customerReview.getProductId());
			result = query.executeUpdate();
		}
		return result;
	}
	
	public int updateCustomerReview(CustomerReview customerReview) throws CurseException, RatingException {
		int result = -1;
		// get curse words from resources file
		Resource resource = appContext.getResource("classpath:curseWords.txt");
		List<String> lines = new ArrayList<String>();
		try {
			lines = Files.readAllLines(Paths.get(resource.getURI()),
			        StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
			return result;
		}
		// check for curse words
		String comment = customerReview.getComment();
		double rating = customerReview.getRating();
		String[] curseWords = new String[lines.size()];
		curseWords = lines.toArray(curseWords);
		System.out.println(curseWords.toString());
		boolean curse = Arrays.stream(curseWords).parallel().anyMatch(comment::contains);
		if (curse) throw new CurseException(comment);
		else if (rating < 0) {
			throw new RatingException(rating);
		} else {
			Query query = entityManager.createNativeQuery(
					"UPDATE customerreview SET headline=?1, comment=?2, rating=?3, blocked=?4, alias=?5, approvalStatus=?6, " +
	                " language=?7, userId=?8, productId=?9 WHERE id=?10", CustomerReview.class);
			query.setParameter(1, customerReview.getHeadline());
			query.setParameter(2, customerReview.getComment());
			query.setParameter(3, customerReview.getRating());
			query.setParameter(4, customerReview.getBlocked());
			query.setParameter(5, customerReview.getAlias());
			query.setParameter(6, customerReview.getApprovalStatus());
			query.setParameter(7, customerReview.getLanguage());
			query.setParameter(8, customerReview.getUserId());
			query.setParameter(9, customerReview.getProductId());
			query.setParameter(10, customerReview.getId());
			result = query.executeUpdate();
		}
		return result;
	}

}
