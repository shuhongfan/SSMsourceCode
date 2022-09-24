package com.bjpowernode.pattern.factory1;


public class Client {

	public static void main(String[] args) {

		Product phoneProduct = ProductFactory.makeProduct("phone");
		System.out.println(phoneProduct.makeProduct());

		Product computerProduct = ProductFactory.makeProduct("computer");
		System.out.println(computerProduct.makeProduct());

		/**
		 * 通过简单工厂模式，使用者不需要要创建的类的具体名字，只要知道该类对应的参数"phone"或者"computer"就可以了；
		 *
		 * 当我们需要增加一种产品时，例如电视，这个时候我们需要先定义一个电视产品类实现Product接口。
		 * 除此之外我们还要修改ProductFactory工厂类的代码，增加一个case，这违背了开闭原则：对扩展开放，对修改关闭。
		 *
		 * 在实际应用中，很可能产品是一个多层次的树状结构，简单工厂可能就不太适用了。
		 *
		 *
		 * 简单工厂模式（静态工厂方法模式） 总结：
		 * 1、工厂类是整个简单工厂模式的核心；
		 * 2、根据条件，工厂类决定创建哪个具体的类对象；
		 * 3、通过工厂类，使用者不用考虑具体类对象的创建过程；
		 * 4、当需要添加新的类，则就需要修改工厂类；
		 * 5、当系统中类型的增多，会导致工厂类的膨胀；
		 */
	}
}