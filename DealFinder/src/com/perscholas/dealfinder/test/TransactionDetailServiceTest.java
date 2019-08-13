package com.perscholas.dealfinder.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.perscholas.dealfinder.entities.TransactionDetail;
import com.perscholas.dealfinder.services.TransactionDetailService;

/**
 * Tests the methods of the TransactionDetailService class
 *
 */
public class TransactionDetailServiceTest {

	// Initialize Transaction Detail Service Object
	TransactionDetailService transactionDetailService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		// Set a new Transaction Detail Service Object to use for testing
		transactionDetailService = new TransactionDetailService(); 
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testCreateTransactionDetail() {

		// Test that the new store is created
		assertTrue(transactionDetailService.createTransactionDetail(new TransactionDetail()));
	}

	@Test
	public final void testGetTransactionDetailsForUser() {

		// Test that the List of Transaction Details is not null
		List<TransactionDetail> transactionDetails = transactionDetailService.getTransactionDetailsForUser(2);
		assertNotNull(transactionDetails);

	}

}
