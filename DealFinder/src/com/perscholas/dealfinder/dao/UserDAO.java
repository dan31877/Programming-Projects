package com.perscholas.dealfinder.dao;

import java.util.List;

import com.perscholas.dealfinder.entities.TransactionDetail;
import com.perscholas.dealfinder.entities.User;

/**
 * The UserDAO interface represents the methods needed to be implemented by the
 * UserService class
 *
 */
public interface UserDAO {

	/**
	 * Method that queries the database for all the users (CRUD - Read)
	 * 
	 * @return
	 */
	List<User> getAllUsers();

	/**
	 * Method that queries the database a user by Email (CRUD - Read)
	 * 
	 * @param sEmail
	 * @return
	 */
	User getUserByEmail(String sEmail);

	/**
	 * Method that queries the database to verify that the email and password are
	 * valid (CRUD - Read)
	 * 
	 * @param sEmail
	 * @param sPass
	 * @return
	 */
	boolean validateUser(String sEmail, String sPass);

	/**
	 * Method that creates a new user in the database (CRUD - Create)
	 * 
	 * @param newUser
	 * @return
	 */
	boolean createUser(User newUser);

	/**
	 * Method that removes the user from the database (CRUD - Delete)
	 * 
	 * @param is
	 * @return
	 */
	boolean removeUser(int id);

	/**
	 * Method to update a user's information in the database. (CRUD - Update)
	 * 
	 * @param user
	 * @return
	 */
	boolean updateUser(User user);

	/**
	 * Method that adds a TransactionDetail Object to the list in User (CRUD -
	 * Update)
	 * 
	 * @param user
	 * @param transactionDetail
	 * @return
	 */
	boolean addTransactionDetailToUser(User user, TransactionDetail transactionDetail);

}
