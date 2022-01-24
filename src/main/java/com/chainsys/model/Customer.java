package com.chainsys.model;

public class Customer {
	private String username;
	private String Password;
	private int customerid;
	private String firstName;
	private String LastName;
	private String Address;
	private long phonenumber;
	private String emailid;

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
		return phonenumber;
	}

	public void setPhonenumber(long phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
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
		this.phonenumber = phonenumber;
		this.emailid = emailid;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String username, String password, String firstName, String lastName, String address,
			long phonenumber, String emailid) {
		super();
		this.username = username;
		this.Password = password;
		this.firstName = firstName;
		this.LastName = lastName;
		this.Address = address;
		this.phonenumber = phonenumber;
		this.emailid = emailid;
	}

	@Override
	public String toString() {
		return "username=" + username + ", Password=" + Password + ", customerid=" + customerid
				+ ", firstName=" + firstName + ", LastName=" + LastName + ", Address=" + Address + ", phonenumber="
				+ phonenumber + ", emailid=" + emailid ;
	}

}
