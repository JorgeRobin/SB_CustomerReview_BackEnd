package com.customerreview.Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.customerreview.Models.*;
import com.customerreview.Repositories.*;
import com.customerreview.ExceptionsAndErrors.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/api") // This means URL's start with /api (after Application path)
public class MainController {
	@Autowired 
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CustomerReviewRepository customerReviewRepository;
	
	Jackson2ObjectMapperBuilder mp = new Jackson2ObjectMapperBuilder();
	final ObjectMapper om = mp.build();
	
	@GetMapping(path= "/")
	public @ResponseBody String index () {
		return "<h2>Welcome to Spring Boot - MySQL - Customer Review</h2>";
	}
	
	@PostMapping(path="/addUser") // Map ONLY POST Requests
	public @ResponseBody RestResponse addUser (@RequestBody User user) {
		
		RestResponse rr = new RestResponse();
		
		User n = userRepository.save(user);
		String userS = null;
		rr.setData(userS);
		rr.setPath("/api/addUser");
		rr.setTimestamp(System.currentTimeMillis());
		rr.setError("");
		rr.setException("");
		if (n == null){
			rr.setStatus(HttpStatus.BAD_REQUEST.value());
			rr.setMessage("Review user info");
			rr.setError("Could not add user");
		} else {
			rr.setStatus(HttpStatus.OK.value());
			rr.setMessage("User added successfully");
			try {
	            userS = om.writeValueAsString(user); 
	            rr.setData(userS.replace("\"", "'"));
	        } catch (Exception e) {
	            e.printStackTrace();
	            rr.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
	            rr.setMessage(e.getMessage());
	        }
			
		}
		return rr;
	
	}
	
	@PutMapping(path="/updateUser/{id}") // Map ONLY POST Requests
	public @ResponseBody RestResponse updateUser (@RequestBody User user, @PathVariable Long id) {
		
		RestResponse rr = new RestResponse();
		
		User n = userRepository.findOne(id);
		String userS = null;
		rr.setData(null);
		rr.setPath("/api/updateUser/{id}");
		rr.setTimestamp(System.currentTimeMillis());
		rr.setError("");
		rr.setException("");
		if (n == null){
			rr.setStatus(HttpStatus.BAD_REQUEST.value());
			rr.setMessage("Could not update user " + id);
		} else {
			user.setId(id);
			n = userRepository.save(user);
			rr.setStatus(HttpStatus.OK.value());
			rr.setMessage("User updated successfully");
			try {
				user.setId(id);
				userS = om.writeValueAsString(user);
	            rr.setData(userS.replace("\"", "'"));
	        } catch (Exception e) {
	            e.printStackTrace();
	            rr.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
	            rr.setMessage(e.getMessage());
	        }
			
		}
		return rr;
	
	}
	
	@DeleteMapping(path="/deleteUser/{id}") 
	public @ResponseBody RestResponse deleteUser (@PathVariable Long id) {
		
		RestResponse rr = new RestResponse();
		
		User n = userRepository.findOne(id);
		String userS = null;
		rr.setPath("/api/deleetUser/{id}");
		rr.setTimestamp(System.currentTimeMillis());
		rr.setError("");
		rr.setException("");
		rr.setData(userS);
		if (n == null) {
			rr.setStatus(HttpStatus.BAD_REQUEST.value());
			rr.setMessage("Could not delete user " + id);
		} else {
			userRepository.delete(id);
			rr.setStatus(HttpStatus.OK.value());
			rr.setMessage("User deleted successfully " + id);
		}
		return rr;
	}
	
	@GetMapping(path="/getAllUsers")
	public @ResponseBody RestResponse getAllUsers() {
		RestResponse rr = new RestResponse();
		String userS = null;
		rr.setData(userS);
		rr.setPath("/api/getAllUsers");
		rr.setTimestamp(System.currentTimeMillis());
		rr.setError("");
		rr.setException("");
		Iterable<User> users = userRepository.findAll();
		
		List<User> target = new ArrayList<User>();
		users.forEach(target::add);
		if (target.size() == 0) {
			rr.setStatus(HttpStatus.EXPECTATION_FAILED.value());
			rr.setMessage("Could not get any users");
		} else {
			try {
				userS = om.writeValueAsString(target);
				rr.setStatus(HttpStatus.OK.value());
				rr.setMessage("List of users retrieved successfully");
	            rr.setData(userS);
	        } catch (Exception e) {
	            e.printStackTrace();
	            rr.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
	            rr.setMessage(e.getMessage());
	        }
		}
		return rr;
	}
	
	@GetMapping(path="/getUser")
	public @ResponseBody RestResponse getUser(@RequestParam("id") String id) {
		RestResponse rr = new RestResponse();
		String userS = null;
		rr.setData(userS);
		rr.setPath("/api/getUser");
		rr.setTimestamp(System.currentTimeMillis());
		rr.setError("");
		rr.setException("");
		// check if id is integer/long
		long idL = 0L;
		try {
			idL = Long.parseLong(id);
		} catch (Exception e){
			rr.setStatus(HttpStatus.BAD_REQUEST.value());
			rr.setMessage("get User - Invalid parameter");
			return rr;
		}
		// chek if user is in database
		User user = userRepository.findOne(idL);
		if (user == null) {
			rr.setStatus(HttpStatus.NOT_FOUND.value());
			rr.setMessage("get User - Could not find user");
			return rr;
		} else {
			try {
				userS = om.writeValueAsString(user);
				rr.setStatus(HttpStatus.OK.value());
				rr.setMessage("User retrieved successfully");
	            rr.setData(userS);
	        } catch (Exception e) {
	            e.printStackTrace();
	            rr.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
	            rr.setMessage(e.getMessage());
	        }
		}
		return rr;
	}
	
	@PostMapping(path="/addProduct") // Map ONLY POST Requests
	public @ResponseBody RestResponse addProduct (@Valid @RequestBody Product product, BindingResult bindingResult) {
		
		RestResponse rr = new RestResponse();
		String productS = null;
		rr.setData(productS);
		rr.setPath("/api/addProduct");
		rr.setTimestamp(System.currentTimeMillis());
		rr.setError("");
		rr.setException("");
		if(bindingResult.hasErrors()) {
			rr.setStatus(HttpStatus.BAD_REQUEST.value());
			rr.setMessage("Validation error, could not add product");
	    }
		else {
			Product p = productRepository.save(product);
			if (p == null) {
				rr.setStatus(HttpStatus.BAD_REQUEST.value());
				rr.setMessage("Could not add product");
			
		    } else {
			    rr.setStatus(HttpStatus.OK.value());
			    rr.setMessage("Product added successfully");
			    try {
	                productS = om.writeValueAsString(product); 
	                rr.setData(productS);
	            } catch (Exception e) {
	                e.printStackTrace();
	                rr.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
	                rr.setMessage(e.getMessage());
	            }
			
		   }
		}	
		return rr;
	}	
	
	@PutMapping(path="/updateProduct/{id}") // Map ONLY PUT Requests
	public @ResponseBody RestResponse updateProduct (@RequestBody Product product, @PathVariable Long id) {
		
		RestResponse rr = new RestResponse();
		
		Product p = productRepository.findOne(id);
		String productS = null;
		rr.setData(null);
		rr.setPath("/api/updateProduct/{id}");
		rr.setTimestamp(System.currentTimeMillis());
		rr.setError("");
		rr.setException("");
		if (p == null){
			rr.setStatus(HttpStatus.BAD_REQUEST.value());
			rr.setMessage("Could not update product " + id);
		} else {
			product.setId(id);
			p = productRepository.save(product);
			rr.setStatus(HttpStatus.OK.value());
			rr.setMessage("Product updated successfully");
			try {
				product.setId(id);
				productS = om.writeValueAsString(product);
	            rr.setData(productS);
	        } catch (Exception e) {
	            e.printStackTrace();
	            rr.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
	            rr.setMessage(e.getMessage());
	        }
			
		}
		return rr;
		
	}
	
	@DeleteMapping(path="/deleteProduct/{id}") // Map ONLY DELETE Requests
	public @ResponseBody RestResponse deleteProduct (@PathVariable Long id) {
		
        RestResponse rr = new RestResponse();
		
		Product p = productRepository.findOne(id);
		String productS = null;
		rr.setData(productS);
		rr.setPath("/api/deleteProduct/{id}");
		rr.setTimestamp(System.currentTimeMillis());
		rr.setError("");
		rr.setException("");
		if (p == null) {
			rr.setStatus(HttpStatus.BAD_REQUEST.value());
			rr.setMessage("Could not delete product " + id);
		} else {
			productRepository.delete(id);
			rr.setStatus(HttpStatus.OK.value());
			rr.setMessage("Product deleted successfully " + id);
		}
		return rr;
	
	}
	
	@GetMapping(path="/getAllProducts")
	public @ResponseBody RestResponse getAllProducts() {
		
		RestResponse rr = new RestResponse();
		String productS = null;
		rr.setData(productS);
		rr.setPath("/api/getAllProducts");
		rr.setTimestamp(System.currentTimeMillis());
		rr.setError("");
		rr.setException("");
		Iterable<Product> products = productRepository.findAll();
		
		List<Product> target = new ArrayList<Product>();
		products.forEach(target::add);
		if (target.size() == 0) {
			rr.setStatus(HttpStatus.EXPECTATION_FAILED.value());
			rr.setMessage("Could not get any users");
		} else {
			try {
				productS = om.writeValueAsString(target);
				rr.setStatus(HttpStatus.OK.value());
				rr.setMessage("List of users retrieved successfully");
	            rr.setData(productS);
	        } catch (Exception e) {
	            e.printStackTrace();
	            rr.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
	            rr.setMessage(e.getMessage());
	        }
		}
		return rr;
	}
	
	@GetMapping(path="/getProduct")
	public @ResponseBody RestResponse getProduct(@RequestParam("id") String id) {
		RestResponse rr = new RestResponse();
		String productS = null;
		rr.setData(productS);
		rr.setPath("/api/getProduct");
		rr.setTimestamp(System.currentTimeMillis());
		rr.setError("");
		rr.setException("");
		// check if id is integer/long
		long idL = 0L;
		try {
			idL = Long.parseLong(id);
		} catch (Exception e){
			rr.setStatus(HttpStatus.BAD_REQUEST.value());
			rr.setMessage("get Product - Invalid parameter");
			return rr;
		}
		// chek if user is in database
		Product product = productRepository.findOne(idL);
		if (product == null) {
			rr.setStatus(HttpStatus.NOT_FOUND.value());
			rr.setMessage("get Product - Could not find product");
			return rr;
		} else {
			try {
				productS = om.writeValueAsString(product);
				rr.setStatus(HttpStatus.OK.value());
				rr.setMessage("Product retrieved successfully");
	            rr.setData(productS);
	        } catch (Exception e) {
	            e.printStackTrace();
	            rr.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
	            rr.setMessage(e.getMessage());
	        }
		}
		return rr;
	}
	
	@PostMapping(path="/addCustomerReview") // Map ONLY POST Requests
	public @ResponseBody RestResponse addCustomerReview(@RequestBody CustomerReview customerReview) {
		
		RestResponse rr = new RestResponse();
		String customerS = null;
		rr.setData(customerS);
		rr.setPath("/api/addCustomerReview");
		rr.setTimestamp(System.currentTimeMillis());
		rr.setError("");
		rr.setException("");
		int result = -1;
		// check for curse words and rating < 0
		try {
			result = customerReviewRepository.addCustomerReview(customerReview);
		} catch (CurseException e) {
			e.printStackTrace();
			rr.setStatus(HttpStatus.BAD_REQUEST.value());
			rr.setMessage("Customer Review - Comment has curse words");
			return rr;
		} catch (RatingException e) {
			e.printStackTrace();
			rr.setStatus(HttpStatus.BAD_REQUEST.value());
			rr.setMessage("Customer Review - Rating less tha zero");
			return rr;
		}
		
		if (result == -1){
			rr.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			rr.setMessage("Customer Review - Could not add customer review");
		} else {
			rr.setStatus(HttpStatus.OK.value());
			rr.setMessage("Customer Review added successfully");
			try {
				customerReview.setId((long) result);
	            customerS = om.writeValueAsString(customerReview); 
	            rr.setData(customerS);
	        } catch (Exception e) {
	            e.printStackTrace();
	            rr.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
	            rr.setMessage(e.getMessage());
	        }
			
		}
		return rr;
		
	}
	
	@PutMapping(path="/updateCustomerReview/{id}") // Map ONLY POST Requests
	public @ResponseBody RestResponse updateCustomerReview(@RequestBody CustomerReview customerReview, @PathVariable Long id) {
		
		RestResponse rr = new RestResponse();
		String customerS = null;
		rr.setData(customerS);
		int result = -1;
		rr.setPath("/api/updateCustomerREview/{id}");
		rr.setTimestamp(System.currentTimeMillis());
		rr.setError("");
		rr.setException("");
		CustomerReview cr = customerReviewRepository.findOne(id);
		if (cr == null) {
			rr.setStatus(HttpStatus.NOT_FOUND.value());
			rr.setMessage("Customer Review - Not found");
			return rr;
		}
		//
		try {
			customerReview.setId(id);
			result = customerReviewRepository.updateCustomerReview(customerReview);
		} catch (CurseException e) {
			e.printStackTrace();
			rr.setStatus(HttpStatus.BAD_REQUEST.value());
			rr.setMessage("Customer Review - Comment has curse words");
			return rr;
		} catch (RatingException e) {
			e.printStackTrace();
			rr.setStatus(HttpStatus.BAD_REQUEST.value());
			rr.setMessage("Customer Review - Rating less tha zero");
			return rr;
		}
		//
		if (result == -1){
			rr.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			rr.setMessage("Customer Review - Could not update customer review");
		} else {
			rr.setStatus(HttpStatus.OK.value());
			rr.setMessage("Customer Review updated successfully");
			try {
	            customerS = om.writeValueAsString(customerReview); 
	            rr.setData(customerS);
	        } catch (Exception e) {
	            e.printStackTrace();
	            rr.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
	            rr.setMessage(e.getMessage());
	        }
			
		}
		return rr;
	
	}
	
	@DeleteMapping(path="/deleteCustomerReview/{id}") 
	public @ResponseBody RestResponse deleteCustomerReview (@PathVariable Long id) {
		
        RestResponse rr = new RestResponse();
		
		CustomerReview cr = customerReviewRepository.findOne(id);
		String customerS = null;
		rr.setData(customerS);
		rr.setPath("/api/deleteCustomerReview/{id}");
		rr.setTimestamp(System.currentTimeMillis());
		rr.setError("");
		rr.setException("");
		if (cr == null) {
			rr.setStatus(HttpStatus.BAD_REQUEST.value());
			rr.setMessage("Could not delete customer review " + id);
		} else {
			customerReviewRepository.delete(id);
			rr.setStatus(HttpStatus.OK.value());
			rr.setMessage("Customer Review - deleted successfully " + id);
		}
		return rr;
		
	}
	
	@GetMapping(path="/getCustomerReview")
	public @ResponseBody RestResponse getCustomerReview(@RequestParam("id") String id) {
		RestResponse rr = new RestResponse();
		String crS = null;
		rr.setData(crS);
		rr.setPath("/api/getCustoemrReview");
		rr.setTimestamp(System.currentTimeMillis());
		rr.setError("");
		rr.setException("");
		// check if id is integer/long
		long idL = 0L;
		try {
			idL = Long.parseLong(id);
		} catch (Exception e){
			rr.setStatus(HttpStatus.BAD_REQUEST.value());
			rr.setMessage("get Customer Review - Invalid parameter");
			return rr;
		}
		// check if user is in database
		CustomerReview cr = customerReviewRepository.findOne(idL);
		if (cr == null) {
			rr.setStatus(HttpStatus.NOT_FOUND.value());
			rr.setMessage("get Customer Review - Could not find Customer Review");
			return rr;
		} else {
			try {
				crS = om.writeValueAsString(cr);
				rr.setStatus(HttpStatus.OK.value());
				rr.setMessage("User retrieved successfully");
	            rr.setData(crS);
	        } catch (Exception e) {
	            e.printStackTrace();
	            rr.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
	            rr.setMessage(e.getMessage());
	        }
		}
		return rr;
	}
	
	@GetMapping(path="/getProductCustomerReviewsInRange")
	public @ResponseBody RestResponse getProductCustomerReviesInRange(@RequestParam("productId") int productId,
			@RequestParam("fromRating") double fromRating, @RequestParam("toRating") double toRating) {
		
		RestResponse rr = new RestResponse();
		String customerS = null;
		rr.setData(customerS);
		rr.setPath("/api/getProductCustomerReviewInRange?productId=?1&fromRating=?2&toRating=?3");
		rr.setTimestamp(System.currentTimeMillis());
		rr.setError("");
		rr.setException("");
		List<CustomerReview> crs = customerReviewRepository.getProductCustomerReviewsInRange(productId, fromRating, toRating);
		
		List<CustomerReview> target = new ArrayList<CustomerReview>();
		crs.forEach(target::add);
		if (target.size() == 0) {
			rr.setStatus(HttpStatus.EXPECTATION_FAILED.value());
			rr.setMessage("Could not get any custoemr reviews");
		} else {
			try {
				customerS = om.writeValueAsString(target);
				rr.setStatus(HttpStatus.OK.value());
				rr.setMessage("List of customer reviews retrieved successfully");
	            rr.setData(customerS);
	        } catch (Exception e) {
	            e.printStackTrace();
	            rr.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
	            rr.setMessage(e.getMessage());
	        }
		}
		return rr;
	}
	
	@GetMapping(path="/getAllCustomerReviews")
	public @ResponseBody RestResponse getAllCustomerReviews() {
		
		RestResponse rr = new RestResponse();
		String customerS = null;
		rr.setData(customerS);
		rr.setPath("/api/getAllCustomerReviews");
		rr.setTimestamp(System.currentTimeMillis());
		rr.setError("");
		rr.setException("");
		Iterable<CustomerReview> crs = customerReviewRepository.findAll();
		
		List<CustomerReview> target = new ArrayList<CustomerReview>();
		crs.forEach(target::add);
		if (target.size() == 0) {
			rr.setStatus(HttpStatus.EXPECTATION_FAILED.value());
			rr.setMessage("Could not get any customer reviews");
		} else {
			try {
				customerS = om.writeValueAsString(target);
				rr.setStatus(HttpStatus.OK.value());
				rr.setMessage("List of customer reviews retrieved successfully");
	            rr.setData(customerS);
	        } catch (Exception e) {
	            e.printStackTrace();
	            rr.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
	            rr.setMessage(e.getMessage());
	        }
		}
		return rr;
		
	}
	
	@GetMapping(path="/totalProductCustomerReviewsInRange")
	public @ResponseBody RestResponse totalProductCustomerReviewsInRange(@RequestParam("productId") int productId,
			@RequestParam("fromRating") double fromRating, @RequestParam("toRating") double toRating) {
		
		RestResponse rr = new RestResponse();
		String customerS = null;
		rr.setData(customerS);
		rr.setPath("/api/totalProductCustomerReviewInRange?productId=?1&fromRating=?2&toRating=?3");
		rr.setTimestamp(System.currentTimeMillis());
		rr.setError("");
		rr.setException("");
		int result = customerReviewRepository.totalProductCustomerReviewsInRange(productId, fromRating, toRating);
		
		try {
			customerS = om.writeValueAsString(result);
			rr.setStatus(HttpStatus.OK.value());
			rr.setMessage("Total product customer reviews in range retrieved successfully");
	        rr.setData(customerS);
	    } catch (Exception e) {
	        e.printStackTrace();
	        rr.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
	        rr.setMessage(e.getMessage());
	    }
		return rr;
	}
	
	
}
