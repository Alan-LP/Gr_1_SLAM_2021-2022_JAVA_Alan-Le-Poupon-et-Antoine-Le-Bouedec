package gsb.tests.utils;

import gsb.utils.ServiceUtils;

public class ServiceUtilsTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String num1 = "07494";
		System.out.println(ServiceUtils.isStringNumeric(num1));
		String num2 = "67G90";
		System.out.println(ServiceUtils.isStringNumeric(num2));
	}

}
