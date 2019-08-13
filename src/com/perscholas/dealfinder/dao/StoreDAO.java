package com.perscholas.dealfinder.dao;

import java.util.List;

import com.perscholas.dealfinder.entities.Product;
import com.perscholas.dealfinder.entities.Store;

/**
 * The StoreDAO interface represents the methods needed to be implemented by the
 * StoreService class
 *
 */
public interface StoreDAO {

	/**
	 * Method that creates a new store in the database (CRUD - Create)
	 * 
	 * @param newStore
	 * @return
	 */
	boolean createStore(Store newStore);

	/**
	 * Method that queries the database for all the stores (CRUD - Read)
	 * 
	 * @return
	 */
	List<Store> getAllStores();

	/**
	 * Method that queries the database a store by ID (CRUD - Read)
	 * 
	 * @param storeId
	 * @return
	 */
	Store getStoreByID(int storeId);

	/**
	 * Method that removes the store from the database (CRUD - Delete)
	 * 
	 * @param storeId
	 * @return
	 */
	boolean removeStore(int storeId);

	/**
	 * Returns true if the store carries the product.
	 * 
	 * @param storeId
	 * @param prodId
	 * @return
	 */
	boolean findProductInStore(int storeId, int prodId);

	/**
	 * Method that adds a Product Object to the list in Store (CRUD - Update)
	 * 
	 * @param store
	 * @param product
	 * @return
	 */
	boolean addProductToStore(Store store, Product product);

}
