package com.chainsys.model;

import java.io.Serializable;

public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;
	private String Password;
	private int customerid;
	private String firstName;
	private String LastName;
	private String Address;
	private long phoneNumber;
	private String emailId;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public long getPhonenumber() {
		return phoneNumber;
	}

	public void setPhonenumber(long phonenumber) {
		this.phoneNumber = phonenumber;
	}

	public String getEmailid() {
		return emailId;
	}

	public void setEmailid(String emailid) {
		this.emailId = emailid;
	}

	public Customer(String username, String password, int customerid, String firstName, String lastName, String address,
			long phonenumber, String emailid) {
		super();
		this.username = username;
		this.Password = password;
		this.customerid = customerid;
		this.firstName = firstName;
		this.LastName = lastName;
		this.Address = address;
		this.phoneNumber = phonenumber;
		this.emailId = emailid;
	}

	public Customer() {
		super();
	}

	public Customer(String username, String password, String firstName, String lastName, String address,
			long phonenumber, String emailid) {
		super();
		this.username = username;
		this.Password = password;
		this.firstName = firstName;
		this.LastName = lastName;
		this.Address = address;
		this.phoneNumber = phonenumber;
		this.emailId = emailid;
	}

	@Override
	public String toString() {
		return "username=" + username + ", Password=" + Password + ", customerid=" + customerid + ", firstName="
				+ firstName + ", LastName=" + LastName + ", Address=" + Address + ", phonenumber=" + phoneNumber
				+ ", emailid=" + emailId;
	}

}
