/**
 * 
 */
package com.java.ee.maker.checker.common.bean;

import java.math.BigDecimal;

/**
 * @author piygupta2
 *
 */
public class Transaction {

	private String name;
	private Integer accountNumber;
	private Integer type;
	private Integer id;
	private Integer userId;
	private Integer approverId;
	private Integer status;
	private String remark;
	private String apprejnote;
	private BigDecimal amount;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the accountNumber
	 */
	public Integer getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber
	 *            the accountNumber to set
	 */
	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the approverId
	 */
	public Integer getApproverId() {
		return approverId;
	}

	/**
	 * @param approverId
	 *            the approverId to set
	 */
	public void setApproverId(Integer approverId) {
		this.approverId = approverId;
	}

	/**
	 * @return the apprejnote
	 */
	public String getApprejnote() {
		return apprejnote;
	}

	/**
	 * @param apprejnote the apprejnote to set
	 */
	public void setApprejnote(String apprejnote) {
		this.apprejnote = apprejnote;
	}
}