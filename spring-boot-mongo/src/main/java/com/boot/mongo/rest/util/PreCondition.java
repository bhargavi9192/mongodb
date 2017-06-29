package com.boot.mongo.rest.util;

/**
 * 
 * @author srinivasyalamanchili
 *
 */
public final class PreCondition {
	/**
	 * 
	 * @param expression
	 * @param errorMessageTemplate
	 * @param errorMessageArguments
	 */
	public static void isTrue(boolean expression, String errorMessageTemplate, Object... errorMessageArguments) {
		isTrue(expression, String.format(errorMessageTemplate, errorMessageArguments));
	}

	/**
	 * 
	 * @param expression
	 * @param errorMessage
	 */
	public static void isTrue(boolean expression, String errorMessage) {
		if (!expression) {
			throw new IllegalArgumentException(errorMessage);
		}
	}

	/**
	 * 
	 * @param string
	 * @param errorMessage
	 */
	public static void notEmpty(String string, String errorMessage) {
		if (string.isEmpty()) {
			throw new IllegalArgumentException(errorMessage);
		}
	}

	/**
	 * 
	 * @param reference
	 * @param errorMessage
	 */
	public static void nullCheck(Object reference, String errorMessage) {
		if (reference == null) {
			throw new NullPointerException(errorMessage);
		}
	}
}
