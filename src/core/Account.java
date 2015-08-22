package core;

import java.util.Date;

public class Account {

	public static enum showItems {
		mail, mobile, both
	};
	public static enum AccountType {
		standard, checker, admin
	}
	public static enum AccountSex {
		male, female
	}
	private Date birthDate;
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	private String userName, password, firstName, lastName, picture, mobile, mail;
	private int id;
	private showItems contact;
	private AccountType type;
	private AccountSex sex;
	
	public AccountSex getSex() {
		return sex;
	}
	public void setSex(AccountSex sex) {
		this.sex = sex;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public AccountType getType() {
		return type;
	}
	public void setType(AccountType type) {
		this.type = type;
	}
	public String getContact() {
		return "" + contact;
	}
	public void setContact(showItems contact) {
		this.contact = contact;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		String ans = "";
		ans = ans.concat(userName + " " + firstName + " " + contact + " " + type);
		return ans;
	}
	
}
