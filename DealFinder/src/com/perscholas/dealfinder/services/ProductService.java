package com.perscholas.dealfinder.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.perscholas.dealfinder.dao.ProductDAO;
import com.perscholas.dealfinder.entities.Product;
import com.perscholas.dealfinder.entities.User;

/**
 * The ProductService class runs CRUD Methods to access the Database
 *
 */
public class ProductService implements ProductDAO {

	
	/**
	 * Method that creates a new product in the database (CRUD - Create)
	 * 
	 * @param newProduct
	 * @return
	 */
	@Override
	public boolean createProduct(Product newProduct) { 
		
		EntityManagerFactory entityManagerFactory = null; 
		EntityManager entityManager = null;
		boolean result = true; 
		
		try { 
			entityManagerFactory = Persistence.createEntityManagerFactory("DealFinder");
			entityManager = entityManagerFactory.createEntityManager(); 
			entityManager.getTransaction().begin();
			entityManager.persist(newProduct);
			entityManager.getTransaction().commit(); 

		} catch (PersistenceException e) { 
			e.getMessage(); 
			result = false; 
		} finally { 
			entityManager.close();
			entityManagerFactory.close();
		}
		return result; 
	}
	
	/**
	 * Method that queries the database for all the products (CRUD - Read)
	 * 
	 * @return
	 */
	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		List<Product> products = null;

		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("DealFinder");
			entityManager = entityManagerFactory.createEntityManager();

			Query query = entityManager.createNamedQuery("getAllProducts");
			products = query.getResultList();
		} catch (PersistenceException e) {
			e.getMessage();
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
		return products;
	}
	
	/**
	 * Method that queries the database a product by ID (CRUD - Read)
	 * 
	 * @param prodID
	 * @return
	 */
	@Override
	public Product getProductByID(int prodID) {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		Product product = null;

		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("DealFinder");
			entityManager = entityManagerFactory.createEntityManager();

			Query query = entityManager.createNamedQuery("getProductByProdID");
			query.setParameter("prodID", prodID);
			product = (Product) query.getSingleResult();
		} catch (PersistenceException e) {
			e.getMessage();
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
		return product;
	}
	
	/**
	 * Method that queries the database for all the products matching or partially matching a name  (CRUD - Read)
	 * 
	 * @return
	 */
	@Override
	public List<Product> getProductsByName(String name) {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		List<Product> products = null;

		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("DealFinder");
			entityManager = entityManagerFactory.createEntityManager();

			Query query = entityManager.createNamedQuery("getProductsByName");
			query.setParameter("name", name);
			products = query.getResultList();
		} catch (PersistenceException e) {
			e.getMessage();
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
		return products;
	}
	
	/**
	 * Method that removes the product from the database (CRUD - Delete)
	 * 
	 * @param is
	 * @return
	 */
	@Override
	public boolean removeProduct( int prodID ) { 
		EntityManagerFactory entityManagerFactory = null; 
		EntityManager entityManager = null;
		boolean result = true; 
		
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("DealFinder");
			entityManager = entityManagerFactory.createEntityManager(); 
			entityManager.getTransaction().begin();
			Product foundProduct = entityManager.find(Product.class, prodID);
			entityManager.remove(foundProduct);
			entityManager.getTransaction().commit();
		} catch (PersistenceException e) { 
			e.getMessage(); 
			result = false; 
		} catch (Exception e) { 
			e.getMessage(); 
			result = false; 
		} finally { 
			entityManager.close();
			entityManagerFactory.close();
		}
		return result; 
	}

}
