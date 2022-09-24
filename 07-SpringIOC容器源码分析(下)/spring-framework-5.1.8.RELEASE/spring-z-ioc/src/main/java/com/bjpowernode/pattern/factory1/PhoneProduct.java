package com.bjpowernode.pattern.factory1;

/**
 * 手机产品
 */
public class PhoneProduct implements Product {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Product makeProduct() {
		PhoneProduct phoneProduct = new PhoneProduct();
		phoneProduct.setName("手机");
		return phoneProduct;
	}

	@Override
	public String toString() {
		return "PhoneProduct{" +
				"name='" + name + '\'' +
				'}';
	}
}