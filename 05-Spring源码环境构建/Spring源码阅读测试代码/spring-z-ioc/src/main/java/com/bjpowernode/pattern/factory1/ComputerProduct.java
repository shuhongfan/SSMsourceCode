package com.bjpowernode.pattern.factory1;

/**
 * 电脑产品
 */
public class ComputerProduct implements Product {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Product makeProduct() {
		ComputerProduct computerProduct = new ComputerProduct();
		computerProduct.setName("电脑");
		return computerProduct;
	}

	@Override
	public String toString() {
		return "ComputerProduct{" +
				"name='" + name + '\'' +
				'}';
	}
}