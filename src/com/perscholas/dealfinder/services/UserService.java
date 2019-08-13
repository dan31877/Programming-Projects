package com.perscholas.dealfinder.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.perscholas.dealfinder.dao.UserDAO;
import com.perscholas.dealfinder.entities.TransactionDetail;
import com.perscholas.dealfinder.entities.User;

/**
 * The UserService class runs CRUD Methods to access the Database
 *
 */
public class UserService implements UserDAO {
	
	/**
	 * Method that creates a new user in the database (CRUD - Create)
	 * 
	 * @param newUser
	 * @return
	 */
	@Override
	public boolean createUser(User newUser) { 
		
		EntityManagerFactory entityManagerFactory = null; 
		EntityManager entityManager = null;
		boolean result = true; 
		
		try { 
			entityManagerFactory = Persistence.createEntityManagerFactory("DealFinder");
			entityManager = entityManagerFactory.createEntityManager(); 
			entityManager.getTransaction().begin();
			entityManager.persist(newUser);
			entityManager.getTransaction().commit(); 

		} catch (PersistenceException e) { 
			e.getMessage(); 
			result = false; 
		} finally { 
			if( entityManager != null) 
				entityManager.close();
			if( entityManagerFactory != null)
				entityManagerFactory.close();
		}
		return result; 
	}
	
	/**
	 * Method that queries the database for all the users (CRUD - Read)
	 * 
	 * @return
	 */
	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		List<User> users = null;

		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("DealFinder");
			entityManager = entityManagerFactory.createEntityManager();

			Query query = entityManager.createNamedQuery("getAllUsers");
			users = query.getResultList();
		} catch (PersistenceException e) {
			e.getMessage();
		} finally {
			if( entityManager != null) 
				entityManager.close();
			if( entityManagerFactory != null)
				entityManagerFactory.close();
		}
		return users;
	}

	/**
	 * Method that queries the database a user by Email (CRUD - Read)
	 * 
	 * @param sEmail
	 * @return
	 */
	@Override
	public User getUserByEmail(String sEmail) {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		User User = null;

		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("DealFinder");
			entityManager = entityManagerFactory.createEntityManager();

			Query query = entityManager.createNamedQuery("getUserByEmail");
			query.setParameter("sEmail", sEmail);
			User = (User) query.getSingleResult();
		} catch (PersistenceException e) {
			e.getMessage();
		} finally {
			if( entityManager != null) 
				entityManager.close();
			if( entityManagerFactory != null)
				entityManagerFactory.close();
		}
		return User;
	}

	/**
	 * Method that queries the database to verify that the email and password are
	 * valid (CRUD - Read)
	 * 
	 * @param sEmail
	 * @param sPass
	 * @return
	 */
	@Override
	public boolean validateUser(String sEmail, String sPass) {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		User user = null;

		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("DealFinder");
			entityManager = entityManagerFactory.createEntityManager();

			Query query = entityManager.createNamedQuery("getUserByEmailAndPassword");
			query.setParameter("sEmail", sEmail);
			query.setParameter("sPass", sPass);
			user = (User) query.getSingleResult();
		} catch (PersistenceException e) {
			e.getMessage();
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
		return (user != null);
	}
	
	/**
	 * Method to update a user's information in the database. (CRUD - Update)
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public boolean updateUser(User user) { 
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("DealFinder"); 
		EntityManager entityManager = entityManagerFactory.createEntityManager(); 
		boolean result = true; 
		
		try {
			entityManager.getTransaction().begin();
			System.out.println("Here?");
			User foundUser = entityManager.find(User.class, this.getUserByEmail(user.getsEmail()).getsUserID());
			foundUser.setsFirstName(user.getsFirstName());
			foundUser.setsLastName(user.getsLastName());
			foundUser.setsEmail(user.getsEmail());
			foundUser.setsPass(user.getsPass());
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
	 * Method that adds a TransactionDetail Object to the list in User (CRUD - Update)
	 * 
	 * @param user
	 * @param transactionDetail
	 * @return
	 */
	@Override
	public boolean addTransactionDetailToUser(User user, TransactionDetail transactionDetail) {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		
		boolean result = false; 

		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("DealFinder");
			entityManager = entityManagerFactory.createEntityManager();

			User foundUser = entityManager.find(User.class, user.getsUserID()); 
			if (foundUser != null) {
				entityManager.getTransaction().begin();
				foundUser.addTransactionDetailToList(transactionDetail);
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
	 * Method that removes the user from the database (CRUD - Delete)
	 * 
	 * @param is
	 * @return
	 */
	@Override
	public boolean removeUser( int sUserID ) { 
		EntityManagerFactory entityManagerFactory = null; 
		EntityManager entityManager = null;
		boolean result = true; 
		
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("DealFinder");
			entityManager = entityManagerFactory.createEntityManager(); 
			entityManager.getTransaction().begin();
			User foundUser = entityManager.find(User.class, sUserID);
			entityManager.remove(foundUser);
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
