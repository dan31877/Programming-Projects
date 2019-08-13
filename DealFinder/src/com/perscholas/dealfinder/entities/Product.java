package com.perscholas.dealfinder.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

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

@NamedQueries({ @NamedQuery(query = "SELECT e FROM Product e", name = "getAllProducts"),
		@NamedQuery(query = "SELECT e FROM Product e WHERE e.prodID = :prodID", name = "getProductByProdID"),
		@NamedQuery(query = "SELECT e FROM Product e WHERE e.name LIKE CONCAT('%',:name,'%')", name = "getProductsByName") })

@Entity
@Table
/**
 * The Product class represents a Product with information about the Product and
 * Manufacturer
 *
 */
public class Product {

	// Parameters/Columns https://thoughts-on-java.org/jpa-generate-primary-keys/
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(updatable = false, nullable = false, length = 50)
	private int prodID;

	@Basic
	@Column(nullable = false, length = 250)
	private String name;

	@Basic
	@Column(nullable = true, length = 50)
	private double price;

	@Basic
	@Column(nullable = false, length = 50)
	private double priceRange;

	@Basic
	@Column(nullable = false, length = 250)
	private String manufacturer;

	@Basic
	@Column(nullable = false, length = 500)
	private String manufacturerSite;

	public Product(String name, double priceRange) {
		this.name = name;
		this.priceRange = priceRange;
	}

	public Product(String name, double price, String manufacturer) {
		this.name = name;
		this.price = price;
		this.manufacturer = manufacturer;
	}

	public Product(String name, String manufacturer) {
		this.name = name;
		this.manufacturer = manufacturer;
	}

	public Product(String name, double priceRange, String manufacturer, String manufacturerSite) {
		this.name = name;
		this.priceRange = priceRange;
		this.manufacturer = manufacturer;
		this.manufacturerSite = manufacturerSite;
	}

	public Product() {

	}

	// Setters and Getters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProdID() {
		return prodID;
	}

	public void setProdID(int prodID) {
		this.prodID = prodID;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	// Generates the price of a Product within a certain range
	public String generatePrice() {
		int addSubtract = (int) (Math.random() * 2.0 + 1);
		double newPrice;
		DecimalFormat df = new DecimalFormat("#0.00");
		double interval = Math.random() * (priceRange * 0.15);
		if (addSubtract == 1) {
			newPrice = priceRange + interval;
		} else {
			newPrice = priceRange - interval;
		}
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(newPrice);
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public double getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(double priceRange) {
		this.priceRange = priceRange;
	}

	public String getManufacturerSite() {
		return manufacturerSite;
	}

	public void setManufacturerSite(String manufacturerSite) {
		this.manufacturerSite = manufacturerSite;
	}

	// Returns a deep clone of the Product
	public Product clone(Product prod) {
		Product cloneProd = new Product();
		cloneProd.setName(prod.getName());
		cloneProd.setManufacturer(prod.getManufacturer());
		cloneProd.setProdID(prod.getProdID());
		return cloneProd;
	}

	// Returns a String representation of the Product
	@Override
	public String toString() {
		return "Product [prodID=" + prodID + ", name=" + name + ", price=" + price + ", priceRange=" + priceRange
				+ ", manufacturer=" + manufacturer + ", manufacturerSite=" + manufacturerSite + "]";
	}
}
