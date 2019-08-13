package com.perscholas.dealfinder.dao;

import java.util.List;

import com.perscholas.dealfinder.entities.TransactionDetail;
import com.perscholas.dealfinder.entities.User;

/**
 * The TransactionDetailDAO interface represents the methods needed to be implemented by the TransactionDetailService class
 *
 */
public interface TransactionDetailDAO {

	/**
	 * Method that creates a new transaction detail in the database (CRUD - Create)
	 * 
	 * @param newTransactionDetail
	 * @return
	 */
	boolean createTransactionDetail(TransactionDetail newTransactionDetail);

	/**
	 * Method that returns a List of Transaction Details associated with a specific User (CRUD - Update)
	 * 
	 * @param userID
	 * @return
	 */
	List<TransactionDetail> getTransactionDetailsForUser(int userID);

}
