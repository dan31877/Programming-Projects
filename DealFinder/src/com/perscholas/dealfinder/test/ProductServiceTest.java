package com.perscholas.dealfinder.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.perscholas.dealfinder.entities.Product;
import com.perscholas.dealfinder.entities.Store;
import com.perscholas.dealfinder.services.ProductService;

/**
 * Tests the methods of the ProductService class
 *
 */
public class ProductServiceTest {

	// Initialize Product Service Object
	ProductService productService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		// Set a new Product Service to use for testing
		productService = new ProductService();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testCreateProduct() {
		// Test that the new product is created
		assertTrue(productService.createProduct(new Product("Almonds", 5.99, "Walmart", "www.walmart.com")));
	}

	@Test
	public final void testGetAllProducts() {
		// Test that the List of Products is not null
		List<Product> products = productService.getAllProducts();
		assertNotNull(products);
		assertTrue(products.size()>=171);
	}

	@Test
	public final void testGetProductByID() {
		// Get product using product 5 for ID which is Bacon
		Product product = productService.getProductByID(5);
		assertNotNull(product);
		assertEquals(product.getName(), "Bacon");
	}

	@Test
	public final void testGetProductsByName() {
		// Test that the List of Products from the name that is not null
		List<Product> products = productService.getProductsByName("cheese");
		assertNotNull(products);
	}

	@Test
	public final void testRemoveProduct() {
		// Test that the new product created earlier is removed
		assertTrue(productService.removeProduct(308));
	}

}
