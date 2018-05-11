package com.hotel.base.common.domain.user.dto;

import com.rennover.hotel.common.valueobject.DomainObject;

/**
 * @author Piyush
 */
public class UserInformationDto extends DomainObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5282669020137928112L;

	public UserInformationDto() {

	}

	/**
	 * Propriété anneeCollecte
	 */
	private String firstName;

	public static final String FIRST_NAME = "firstName";

	public String getFirstName() {

		return nullToEmpty(firstName);

	}

	public void setFirstName(String newValue) {
		setFirstName0(newValue);
	}

	private final void setFirstName0(String newValue) {
		String oldValue = firstName;
		if (!equals(oldValue, newValue)) {

			firstName = trimString(newValue);

			setDirty(true);
			firePropertyChange(FIRST_NAME, oldValue, newValue);
		}
	}

	private String lastName;
	public static final String LAST_NAME = "lastName";

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return nullToEmpty(lastName);
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String newValue) {
		setLastName0(newValue);
	}

	private final void setLastName0(String newValue) {
		String oldValue = lastName;
		if (!equals(oldValue, newValue)) {

			lastName = trimString(newValue);

			setDirty(true);
			firePropertyChange(LAST_NAME, oldValue, newValue);
		}
	}

	private Long mobileNumber;

	public static final String MOBILE_NUMBER = "mobileNumber";

	/**
	 * @return the lastName
	 */
	public Long getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setMobileNumber(Long newValue) {
		setMobileNumber0(newValue);
	}

	private final void setMobileNumber0(Long newValue) {
		Long oldValue = mobileNumber;
		if (!equals(oldValue, newValue)) {

			mobileNumber = newValue;

			setDirty(true);
			firePropertyChange(MOBILE_NUMBER, oldValue, newValue);
		}
	}

	private String email;

	public static final String EMAIL = "email";

	/**
	 * @return the email
	 */
	public String getEmail() {
		return nullToEmpty(email);
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String newValue) {
		setEmail0(newValue);
	}

	private final void setEmail0(String newValue) {
		String oldValue = email;
		if (!equals(oldValue, newValue)) {

			email = newValue;

			setDirty(true);
			firePropertyChange(EMAIL, oldValue, newValue);
		}
	}

	private int age;

	public static final String AGE = "age";

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(int newValue) {
		setAge0(newValue);
	}

	private final void setAge0(int newValue) {
		int oldValue = age;
		if (!equals(oldValue, newValue)) {

			age = newValue;

			setDirty(true);
			firePropertyChange(AGE, oldValue, newValue);
		}
	}

	public Object clone() {
		UserInformationDto clone = (UserInformationDto) super.clone();
		return clone;
	}

	private int id;

	private long addressId;

	private String isCustomer;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the age
	 */
	/**
	 * @return the addressId
	 */
	public long getAddressId() {
		return addressId;
	}

	/**
	 * @param addressId
	 *            the addressId to set
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
	 * @param isCustomer
	 *            the isCustomer to set
	 */
	public void setIsCustomer(String isCustomer) {
		this.isCustomer = isCustomer;
	}

}
