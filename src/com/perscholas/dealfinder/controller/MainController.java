package com.perscholas.dealfinder.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.perscholas.dealfinder.entities.Product;
import com.perscholas.dealfinder.entities.Store;
import com.perscholas.dealfinder.entities.TransactionDetail;
import com.perscholas.dealfinder.entities.User;
import com.perscholas.dealfinder.services.ProductService;
import com.perscholas.dealfinder.services.StoreService;
import com.perscholas.dealfinder.services.TransactionDetailService;
import com.perscholas.dealfinder.services.UserService;

@Controller
@SessionAttributes(value = { "suser" })
public class MainController {

	//////////////////////
	// MODEL ATTRIBUTES //
	//////////////////////

	/* Sets the initial User to Guest in Session */
	@ModelAttribute("suser")
	public User getInitialUser() {
		UserService userService = new UserService();
		User user = userService.getUserByEmail("guest@guest.com"); // guest@guest.com dan@email.com
		return user;
	}

	/* Represents a User object to be used in Registration validation */
	@ModelAttribute("userkey")
	public User setUpForm() {
		return new User();
	}

	/* Maps to the Home/Search page */
	@RequestMapping(value = { "/", "/index" })
	public ModelAndView home() {
		return new ModelAndView("index");
	}

	/* Maps to the About page */
	@RequestMapping(value = { "about" })
	public ModelAndView about() {
		return new ModelAndView("about");
	}

	////////////////
	// PRODUCTS //
	////////////////

	/* Displays the Products page along with all the Products in the Database */
	@RequestMapping(value = { "/showAllProducts" })
	public ModelAndView showAllProducts() {

		ModelAndView mav = new ModelAndView();
		ProductService productService = new ProductService();
		List<Product> products = productService.getAllProducts();
		String message = "List of all the available products. Click anywhere in the row to select a product.";

		mav.addObject("prodMessage", message);
		mav.addObject("products", products);
		mav.setViewName("productList");

		return mav;
	}

	/*
	 * Displays the Products page along with the Products that match the searched
	 * name
	 */
	@RequestMapping(value = { "/getProducts" }, method = RequestMethod.POST)
	public ModelAndView getProducts(@RequestParam("name") String name) {

		ModelAndView mav = new ModelAndView();
		ProductService productService = new ProductService();
		List<Product> products = productService.getProductsByName(name);
		String message = "";
		if (name.isEmpty())
			message = "List of all the available products. Click anywhere in the row to select a product.";
		else
			message = "List of products that match your search. Click anywhere in the row to select a product.";

		mav.addObject("prodMessage", message);
		mav.addObject("products", products);
		mav.setViewName("productList");

		return mav;
	}

	/*
	 * Allows the User to select one of the Products displayed and move to the
	 * Product Details page, uses Streams to find the Stores that have the Product
	 */
	@RequestMapping(value = { "/selectProduct/{id}" })
	public ModelAndView selectProduct(@PathVariable(name = "id", required = true) int id,
			@SessionAttribute("suser") User user) {

		ModelAndView mav = new ModelAndView();
		ProductService productService = new ProductService();
		TransactionDetailService transactionDetailService = new TransactionDetailService();
		UserService userService = new UserService();
		StoreService storeService = new StoreService();
		Product prod = productService.getProductByID(id);
		List<Store> stores = storeService.getAllStores();
		
		List<Store> storesWithProd = stores.stream()
				.filter(store -> storeService.findProductInStore(store.getStoreId(), id))
				.collect(Collectors.toList());

		TransactionDetail transactionDetail = new TransactionDetail(prod, storesWithProd);
		userService.addTransactionDetailToUser(user, transactionDetail);

		mav.addObject("suser", user);
		mav.addObject("storeList", storesWithProd);
		mav.addObject("product", prod);
		mav.setViewName("prodDetails");

		return mav;
	}

	////////////////
	// USERS //
	////////////////

	/* Maps to the User Registration Page */
	@RequestMapping(value = { "/registerUser" })
	public ModelAndView registerUser() {
		return new ModelAndView("registerUser");
	}

	/* Registers the User and Validates User information */
	@RequestMapping(value = { "/userRegistration" }, method = RequestMethod.POST)
	public ModelAndView userRegistration(@ModelAttribute("userkey") @Valid User user, BindingResult errors) {

		ModelAndView mav = new ModelAndView();
		if (errors.hasErrors()) {
			String validationMessage = "Please enter valid data in the fields below.";
			mav.addObject("validationMessage", validationMessage);
			mav.setViewName("registerUser");
			return mav;
		}
		mav = new ModelAndView();
		UserService userService = new UserService();
		boolean result = userService.createUser(user);
		String userMessage = "";
		String errorMessage = "";
		if (result) {
			userMessage = "User registered successfully. Please login with your email and password.";
			mav.setViewName("userLogin");
		} else {
			errorMessage = "Something went wrong. User was not registered.";
			mav.setViewName("registerUser");
		}
		mav.addObject("userMessage", userMessage);
		mav.addObject("errorMessage", errorMessage);
		return mav;
	}

	/* Maps to the User Login Page */
	@RequestMapping(value = { "/userLogin" })
	public ModelAndView userLogin() {
		return new ModelAndView("userLogin");
	}

	/* Validates the User and Password match a User in the Database */
	@RequestMapping(value = { "/loginUser" }, method = RequestMethod.POST)
	public ModelAndView loginUser(@RequestParam("sEmail") String email, @RequestParam("sPass") String password) {

		ModelAndView mav = new ModelAndView();
		UserService userService = new UserService();
		boolean result = userService.validateUser(email, password);
		String userMessage = "";
		String errorMessage = "";
		if (result) {
			userMessage = "User successfully logged in.";
			User user = userService.getUserByEmail(email);
			mav.addObject("suser", user);
			mav.setViewName("index");
		} else {
			errorMessage = "Something went wrong. User was not logged in.";
			mav.setViewName("userLogin");
		}
		mav.addObject("userMessage", userMessage);
		mav.addObject("errorMessage", errorMessage);
		return mav;
	}

	/* Logs out the user, returns as Guest to the Home/Search page */
	@RequestMapping(value = { "/userLogout" })
	public ModelAndView userLogout() {
		ModelAndView mav = new ModelAndView();
		UserService userService = new UserService();
		String userMessage = "User successfully logged out.";
		User user = userService.getUserByEmail("guest@guest.com");

		mav.addObject("suser", user);
		mav.addObject("userMessage", userMessage);

		mav.setViewName("index");
		return mav;
	}

	/* Maps to the User Profile Page */
	@RequestMapping(value = { "/userProfile" })
	public ModelAndView userProfile() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("userProfile");
		return mav;
	}

	/*
	 * Allows a User to update their information except for their User ID and Email
	 */
	@RequestMapping(value = { "/updateUser" }, method = RequestMethod.POST)
	public ModelAndView updateUser(@ModelAttribute User user) {

		ModelAndView mav = new ModelAndView();
		//System.out.println("User: " + user);
		UserService userService = new UserService();
		boolean result = userService.updateUser(user);
		String userMessage = "";
		String errorMessage = "";
		if (result) {
			userMessage = "User was updated successfully.";
			mav.setViewName("index");
		} else {
			errorMessage = "Something went wrong. User was not updated.";
			mav.setViewName("userProfile");
		}
		mav.addObject("suser", user);
		mav.addObject("userMessage", userMessage);
		mav.addObject("errorMessage", errorMessage);
		return mav;
	}

	/* Deletes a User from the Database, returns as Guest to the Home/Search page */
	@RequestMapping(value = { "/deleteUser" })
	public ModelAndView deleteUser(@SessionAttribute("suser") User deleteUser) {

		ModelAndView mav = new ModelAndView();
		UserService userService = new UserService();
		User user = userService.getUserByEmail("guest@guest.com");
		boolean result = false;
		if (deleteUser.getsUserID() != 1) { // Make sure guest user doesn't get deleted.
			result = userService.removeUser(deleteUser.getsUserID());
		}
		String userMessage = "";
		if (result) {
			userMessage = "User was deleted successfully.";
			mav.setViewName("index");
		} else {
			userMessage = "Something went wrong. User was not deleted.";
			mav.setViewName("userProfile");
		}
		mav.addObject("suser", user);
		mav.addObject("userMessage", userMessage);
		return mav;
	}

	/* Displays a History of User activity through Transaction Details */
	@RequestMapping(value = { "/transactionDetailList" })
	public ModelAndView transactionDetailList(@SessionAttribute("suser") User user) {

		ModelAndView mav = new ModelAndView();
		TransactionDetailService transactionDetailService = new TransactionDetailService();
		//System.out.println(user.getsUserID());
		List<TransactionDetail> transactionDetailList = transactionDetailService
				.getTransactionDetailsForUser(user.getsUserID());

		mav.addObject("transactionDetailList", transactionDetailList);
		mav.addObject("suser", user);
		mav.setViewName("transactionDetailList");
		return mav;
	}

}
