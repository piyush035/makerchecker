/**
 * 
 */
package com.java.ee.maker.checker.common.constant;

/**
 * @author piygupta2
 *
 */
public enum TransactionStatus {
	ACCEPTED(1), REJECTED(2), PENDING(3);
	private int code;

	TransactionStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
