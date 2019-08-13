package com.perscholas.dealfinder.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.perscholas.dealfinder.dao.TransactionDetailDAO;
import com.perscholas.dealfinder.entities.TransactionDetail;

/**
 * The TransactionDetailService class runs CRUD Methods to access the Database
 *
 */
public class TransactionDetailService implements TransactionDetailDAO {

	/**
	 * Method that creates a new transaction detail in the database (CRUD - Create)
	 * 
	 * @param newTransactionDetail
	 * @return
	 */
	@Override
	public boolean createTransactionDetail(TransactionDetail newTransactionDetail) {

		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		boolean result = true;

		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("DealFinder");
			entityManager = entityManagerFactory.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(newTransactionDetail);
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
	 * Method that returns a List of Transaction Details associated with a specific User (CRUD - Update)
	 * 
	 * @param userID
	 * @return
	 */
	@Override
	public List<TransactionDetail> getTransactionDetailsForUser(int userID) {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		List<TransactionDetail> transactionDetails = null;

		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("DealFinder");
			entityManager = entityManagerFactory.createEntityManager();

			//User foundUser = entityManager.find(User.class, userID);
			Query query = entityManager.createNativeQuery("SELECT t.TRANSACTIONID, t.TIMESTAMP, t.PRODUCT_PRODID " 
			+ "FROM transactiondetail t INNER JOIN user_transactiondetail ut ON "
					+ "(t.TRANSACTIONID = ut.transactionDetails_TRANSACTIONID) "
					+ "WHERE ut.User_SUSERID = ?", TransactionDetail.class);
			query.setParameter(1, userID);
			transactionDetails = (List<TransactionDetail>)query.getResultList();
		} catch (PersistenceException e) {
			e.getMessage();
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
		return transactionDetails;
	}

}
