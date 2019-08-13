package com.perscholas.dealfinder.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.perscholas.dealfinder.validation.EmailConstraint;
import com.perscholas.dealfinder.validation.PasswordConstraint;

@Entity
@Table
@NamedQueries({ @NamedQuery(query = "SELECT e FROM User e", name = "getAllUsers"),
		@NamedQuery(query = "SELECT e FROM User e WHERE e.sEmail = :sEmail", name = "getUserByEmail"),
		@NamedQuery(query = "SELECT e FROM User e WHERE e.sUserID = :sUserID", name = "getUserByID"),
		@NamedQuery(query = "SELECT e FROM User e WHERE e.sEmail = :sEmail AND e.sPass = :sPass", name = "getUserByEmailAndPassword")// ,
})
/**
 * The User class represents a User who can log into the system and access the
 * Search apabilities along with other features.
 *
 */
public class User {

	// Parameters/Columns
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(updatable = false, nullable = false, length = 50)
	private int sUserID;

	@Basic
	@Column(unique = true, nullable = false, length = 150)
	@EmailConstraint
	private String sEmail;

	@Basic
	@Column(nullable = false, length = 50)
	@Size(min = 2, max = 20, message = "First name length must be between {2} and {1}")
	private String sFirstName;

	@Basic
	@Column(nullable = false, length = 50)
	@Size(min = 2, max = 30, message = "Last name length must be between {2} and {1}")
	private String sLastName;

	@Basic
	@Column(nullable = false, length = 50)
	@PasswordConstraint
	private String sPass;

	// Define one to many relationship with Product Entity
	@OneToMany(targetEntity = TransactionDetail.class, cascade = CascadeType.PERSIST)
	private List<TransactionDetail> transactionDetails;

	// Constructors using fields and empty Constructor
	public User(String sEmail, String sFirstName, String sLastName, String sPass) {
		this.sEmail = sEmail;
		this.sFirstName = sFirstName;
		this.sLastName = sLastName;
		this.sPass = sPass;
		this.transactionDetails = new ArrayList<TransactionDetail>();
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	// Setters and Getters
	public int getsUserID() {
		return sUserID;
	}

	// Getters and Setters
	public String getsEmail() {
		return sEmail;
	}

	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}

	public String getsFirstName() {
		return sFirstName;
	}

	public void setsFirstName(String sFirstName) {
		this.sFirstName = sFirstName;
	}

	public String getsLastName() {
		return sLastName;
	}

	public void setsLastName(String sLastName) {
		this.sLastName = sLastName;
	}

	public String getsPass() {
		return sPass;
	}

	public void setsPass(String sPass) {
		this.sPass = sPass;
	}

	public List<TransactionDetail> getTransactionDetail() {
		return transactionDetails;
	}

	public void setTransactionDetail(List<TransactionDetail> transactionDetail) {
		this.transactionDetails = transactionDetail;
	}

	public void addTransactionDetailToList(TransactionDetail transactionDetail) {
		this.transactionDetails.add(transactionDetail);
	}

	// Override to String method
	@Override
	public String toString() {
		return "User [sUserID=" + sUserID + ", sEmail=" + sEmail + ", sFirstName=" + sFirstName + ", sLastName="
				+ sLastName + ", sPass=" + sPass + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sEmail == null) ? 0 : sEmail.hashCode());
		result = prime * result + ((sFirstName == null) ? 0 : sFirstName.hashCode());
		result = prime * result + ((sPass == null) ? 0 : sPass.hashCode());
		return result;
	}

	// Override equals method for testing
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (sEmail == null) {
			if (other.sEmail != null)
				return false;
		} else if (!sEmail.equals(other.sEmail))
			return false;
		if (sFirstName == null) {
			if (other.sFirstName != null)
				return false;
		} else if (!sFirstName.equals(other.sFirstName))
			return false;
		if (sPass == null) {
			if (other.sPass != null)
				return false;
		} else if (!sPass.equals(other.sPass))
			return false;
		return true;
	}

}
