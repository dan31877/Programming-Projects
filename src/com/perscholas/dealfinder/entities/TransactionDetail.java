package com.perscholas.dealfinder.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
/**
 * The TransactionDetail class represents a history of all the items searched
 * and can be assigned to a User
 *
 */
public class TransactionDetail {

	// Parameters/Columns
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(updatable = false, nullable = false, length = 50)
	private int transactionID;

//	@Basic
//	@Column(nullable = false, length = 50)
	@OneToOne
//    @MapsId
	private Product product;

	@Basic
	@Column(nullable = false, length = 50)
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;

	// Define one to many relationship with Product Entity
	@OneToMany(targetEntity = Store.class)
	private List<Store> storeList;

	public TransactionDetail(Product product, List<Store> storeList) {
		this.product = product;
		this.storeList = storeList;
		this.timestamp = new Date();
	}

	public TransactionDetail(Product product) {

		this.product = product;
		this.storeList = new ArrayList<Store>();
		this.timestamp = new Date();
	}

	public TransactionDetail() {
		// TODO Auto-generated constructor stub
		this.timestamp = new Date();
	}

	// Setters and Getters
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Store> getStoreList() {
		return storeList;
	}

	public void setStoreList(List<Store> storeList) {
		this.storeList = storeList;
	}

	public void addStoreToList(Store store) {
		this.storeList.add(store);
	}

	public int getTransactionID() {
		return transactionID;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	// Returns a String representation of the TransactionDetail Object
	@Override
	public String toString() {
		return "TransactionDetail [transactionID=" + transactionID + ", product=" + product + ", timestamp=" + timestamp
				+ "]";
	}

}
