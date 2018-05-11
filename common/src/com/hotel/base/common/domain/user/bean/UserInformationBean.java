package com.hotel.base.common.domain.user.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hotel.base.common.domain.user.dto.UserInformationDto;
import com.hotel.base.common.tech.bean.DefaultBean;

@Entity
@Table(name = "userinformation")
public class UserInformationBean implements DefaultBean {

	/**
	 * 	//@OneToOne(fetch = FetchType.LAZY, mappedBy = "country", cascade = CascadeType.ALL)
	 */
	private static final long serialVersionUID = 2423777175819944671L;

	@Id
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "firstname", nullable = false)
	private String firstName;

	@Column(name = "lastname", nullable = false)
	private String lastName;

	@Column(name = "mobileNumber", nullable = false)
	private Long mobileNumber;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "age", nullable = false)
	private int age;


	@Column(name = "addressId", nullable = false)
	private long addressId;

	@Column(name = "isCustomer", nullable = false)
	private String isCustomer;

	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}



	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}



	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}



	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	/**
	 * @return the mobileNumber
	 */
	public Long getMobileNumber() {
		return mobileNumber;
	}



	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}



	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}



	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}



	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}



	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}



	/**
	 * @return the addressId
	 */
	public long getAddressId() {
		return addressId;
	}



	/**
	 * @param addressId the addressId to set
	 */
	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}



	/**
	 * @return the isCustomer
	 */
	public String getIsCustomer() {
		return isCustomer;
	}



	/**
	 * @param isCustomer the isCustomer to set
	 */
	public void setIsCustomer(String isCustomer) {
		this.isCustomer = isCustomer;
	}



	public UserInformationDto getUserInformationDto(){
		
		UserInformationDto userInformationDto = new UserInformationDto();
		userInformationDto.setId(this.id);
		userInformationDto.setFirstName(this.firstName);
		userInformationDto.setLastName(this.lastName);
		userInformationDto.setMobileNumber(this.mobileNumber);
		userInformationDto.setEmail(this.email);
		userInformationDto.setAge(this.age);
		userInformationDto.setAddressId(this.addressId);
		userInformationDto.setIsCustomer(this.isCustomer);
		return userInformationDto;
			}
}