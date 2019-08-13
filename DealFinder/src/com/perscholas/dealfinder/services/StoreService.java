package com.perscholas.dealfinder.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.perscholas.dealfinder.dao.StoreDAO;
import com.perscholas.dealfinder.entities.Product;
import com.perscholas.dealfinder.entities.Store;
import com.perscholas.dealfinder.entities.TransactionDetail;
import com.perscholas.dealfinder.entities.User;

/**
 * The StoreService class runs CRUD Methods to access the Database
 *
 */
public class StoreService implements StoreDAO {
	
	/**
	 * Method that creates a new store in the database (CRUD - Create)
	 * 
	 * @param newStore
	 * @return
	 */
	@Override
	public boolean createStore(Store newStore) { 
		
		EntityManagerFactory entityManagerFactory = null; 
		EntityManager entityManager = null;
		boolean result = true; 
		
		try { 
			entityManagerFactory = Persistence.createEntityManagerFactory("DealFinder");
			entityManager = entityManagerFactory.createEntityManager(); 
			entityManager.getTransaction().begin();
			entityManager.persist(newStore);
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
	 * Method that queries the database for all the stores (CRUD - Read)
	 * 
	 * @return
	 */
	@Override
	public List<Store> getAllStores() {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		List<Store> stores = null;

		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("DealFinder");
			entityManager = entityManagerFactory.createEntityManager();

			Query query = entityManager.createNamedQuery("getAllStores");
			stores = query.getResultList();
		} catch (PersistenceException e) {
			e.getMessage();
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
		return stores;
	}
	
	/**
	 * Method that queries the database a store by ID (CRUD - Read)
	 * 
	 * @param storeId
	 * @return
	 */
	@Override
	public Store getStoreByID(int storeId) {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		Store store = null;

		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("DealFinder");
			entityManager = entityManagerFactory.createEntityManager();

			Query query = entityManager.createNamedQuery("getStoreByStoreID");
			query.setParameter("storeId", storeId);
			store = (Store) query.getSingleResult();
		} catch (PersistenceException e) {
			e.getMessage();
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
		return store;
	}
	
	/**
	 * Returns true if the store carries the product.
	 * 
	 * @param storeId
	 * @param prodId
	 * @return
	 */
	@Override
	public boolean findProductInStore(int storeId, int prodId) { 
		
		Store store = this.getStoreByID(storeId); 
		//Product prod = null; 
		
		if( store != null && store.isInStore(prodId)) { 
			return true; 
		} else { 
			return false; 
		}
		
	}
	
	/** 
	 * Method that adds a Product Object to the list in Store (CRUD - Update)
	 * 
	 * @param store
	 * @param product
	 * @return
	 */
	@Override
	public boolean addProductToStore(Store store, Product product) {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		
		boolean result = false; 

		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("DealFinder");
			entityManager = entityManagerFactory.createEntityManager();

			Store foundStore = entityManager.find(Store.class, store.getStoreId()); 
			if (foundStore != null && !foundStore.isInStore(product.getProdID())) {
				entityManager.getTransaction().begin();
				foundStore.addProductToStore(product);
				entityManager.getTransaction().commit();
				result = true; 
			} 
		} catch (PersistenceException e) {
			e.getMessage();
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}

		return result;
	}
	
	/**
	 * Method that removes the store from the database (CRUD - Delete)
	 * 
	 * @param storeId
	 * @return
	 */
	@Override
	public boolean removeStore( int storeId ) { 
		EntityManagerFactory entityManagerFactory = null; 
		EntityManager entityManager = null;
		boolean result = true; 
		
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("DealFinder");
			entityManager = entityManagerFactory.createEntityManager(); 
			entityManager.getTransaction().begin();
			Store foundStore = entityManager.find(Store.class, storeId);
			entityManager.remove(foundStore);
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
