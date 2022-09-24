package com.bjpowernode.pattern.factory1;

/**
 * 产品工厂
 */
public class ProductFactory {

	public static Product makeProduct(String type) {
		Product product = null;
		switch (type) {

			case "phone":
				product = new PhoneProduct();
				break;

			case "computer":
				product = new ComputerProduct();
				break;

			default:
				throw new UnsupportedOperationException("不支持该产品的生产");
		}
		return product;
	}
}