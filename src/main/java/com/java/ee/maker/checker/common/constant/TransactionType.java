/**
 * 
 */
package com.java.ee.maker.checker.common.constant;

/**
 * @author piygupta2
 *
 */
public enum TransactionType {
	CREDIT(1), DEBIT(2);
	private int code;

	TransactionType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
