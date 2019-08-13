package com.perscholas.dealfinder.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.perscholas.dealfinder.entities.Product;
import com.perscholas.dealfinder.entities.Store;
import com.perscholas.dealfinder.services.ProductService;
import com.perscholas.dealfinder.services.StoreService;

/**
 * Tests the methods of the StoreService class
 *
 */
public class StoreServiceTest {

	// Initialize Store Service and Product Service Objects
	StoreService storeService;
	ProductService productService; 
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		// Set a new Store Service and Product Service to use for testing
		storeService = new StoreService(); 
		productService = new ProductService();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testCreateStore() {
		
		// Test that the new store is created
		assertTrue(storeService.createStore(new Store("Star Market", "Cambridge", "No coupons allowed")));
	}

	@Test
	public final void testGetAllStores() {

		// Test that the List of Stores is not null
		List<Store> stores = storeService.getAllStores();
		assertNotNull(stores);
	}

	@Test
	public final void testGetStoreByID() {
		// Get store using store 6 for ID which is Roche Bros.
		Store store = storeService.getStoreByID(6);
		assertNotNull(store);
		assertEquals(store.getName(), "Roche Bros.");
	}

	@Test
	public final void testFindProductInStore() {
		// Test with a product that should be in the store
		assertTrue(storeService.findProductInStore(1, 21)); 
		
		// Test with a product that should not be in the store
		assertFalse(storeService.findProductInStore(5, 68)); 

	}

	@Test
	public final void testAddProductToStore() {
		
		// Set up a Store and Product Object
		Store store = storeService.getStoreByID(1);
		Product prod = productService.getProductByID(70);
		
		// Test with a product that should not be in the store
		assertFalse(storeService.findProductInStore(store.getStoreId(), prod.getProdID())); 
		
		
		// Test the addProductToStore method returns true
		assertTrue(storeService.addProductToStore(store, prod));
		
		// Test that the Product should now be in the Store
		assertTrue(storeService.findProductInStore(store.getStoreId(), prod.getProdID())); 
	}

	@Test
	public final void testRemoveStore() {
		
		// Tests that the store created earlier is removed
		assertTrue(storeService.removeStore(9));
	}

}
