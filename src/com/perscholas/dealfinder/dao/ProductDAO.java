package com.perscholas.dealfinder.dao;

import java.util.List;

import com.perscholas.dealfinder.entities.Product;

/**
 * The ProductDAO interface represents the methods needed to be implemented by
 * the ProductService class
 *
 */
public interface ProductDAO {

	/**
	 * Method that queries the database for all the products (CRUD - Read)
	 * 
	 * @return
	 */
	List<Product> getAllProducts();

	/**
	 * Method that queries the database a product by ID (CRUD - Read)
	 * 
	 * @param prodID
	 * @return
	 */
	Product getProductByID(int prodID);

	/**
	 * Method that removes the product from the database (CRUD - Delete)
	 * 
	 * @param is
	 * @return
	 */
	boolean removeProduct(int prodID);

	/**
	 * Method that creates a new product in the database (CRUD - Create)
	 * 
	 * @param newProduct
	 * @return
	 */
	boolean createProduct(Product newProduct);

	/**
	 * Method that queries the database for all the products matching or partially
	 * matching a name (CRUD - Read)
	 * 
	 * @return
	 */
	List<Product> getProductsByName(String name);

}
