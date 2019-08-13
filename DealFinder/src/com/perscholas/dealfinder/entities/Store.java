package com.perscholas.dealfinder.entities;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
@NamedQueries({ @NamedQuery(query = "SELECT e FROM Store e", name = "getAllStores"),
		@NamedQuery(query = "SELECT e FROM Store e WHERE e.storeId = :storeId", name = "getStoreByStoreID") })
/**
 * The Store class represents different Stores that carry the Product we are
 * searching for.
 *
 */
public class Store {

	// Parameters/Columns
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(updatable = false, nullable = false, length = 50)
	private int storeId;

	@Basic
	@Column(nullable = false, length = 50)
	private String name;

	@Basic
	@Column(nullable = false, length = 50)
	private String location;

	@Basic
	@Column(nullable = false, length = 50)
	private String couponPolicy;

	// Define one to many relationship with Product Entity
	@OneToMany(targetEntity = Product.class)
	private List<Product> productList;

	public Store(String name, String location) {
		this.name = name;
		this.location = location;
		this.productList = new ArrayList<Product>();
	}

	public Store(String name, String location, String couponPolicy) {
		this.name = name;
		this.location = location;
		this.couponPolicy = couponPolicy;
		this.productList = new ArrayList<Product>();
	}

	public Store() {
	}

	// Setters and Getters
	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCouponPolicy() {
		return couponPolicy;
	}

	public void setCouponPolicy(String couponPolicy) {
		this.couponPolicy = couponPolicy;
	}

	public void addProductToStore(Product prod) {
		Product myProd = prod.clone(prod);
		// myProd.setPrice(price);
		productList.add(myProd);
	}

	// Searches the Product List for the Product, returns True/False
	public boolean isInStore(int prodID) {

		boolean result = false;
		for (Product product : productList) {
			if (product.getProdID() == prodID) {
				result = true;
				break;
			}
		}
		return result;
	}

	// Searches the Product List to retrieve the Product from the list
	public Product findInStore(int prodID) {

		Product foundProduct = null;
		for (Product product : productList) {
			if (product.getProdID() == prodID) {
				foundProduct = product;
				break;
			}
		}
		return foundProduct;

	}

	// Returns a String representation of the Store
	@Override
	public String toString() {
		return "Store [storeId=" + storeId + ", name=" + name + ", location=" + location + ", couponPolicy="
				+ couponPolicy + ", productList=" + productList + "]";
	}

	// Returns a formatted String representation of the Store
	public String printProduct(int prodID) {
		Product prod = this.findInStore(prodID);
		return String.format("Store ID: %-5d  Store Name: %-12s  %-12s Price: $%.2f", this.storeId, this.name, prod,
				prod.getPrice());
	}

	// Method to Print all the Products to the console
	public void printAllProducts() {

		for (Product prod : productList) {
			System.out.println(prod);
		}
	}

}
