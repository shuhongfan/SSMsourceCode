package com.bjpowernode.service.impl;

import com.bjpowernode.mapper.GoodsMapper;
import com.bjpowernode.model.Goods;
import com.bjpowernode.service.GoodsService;
import com.bjpowernode.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.util.Date;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsMapper goodsMapper;

	@Autowired
	private OrderService orderService;

	//@Transactional(rollbackFor = Throwable.class)
	protected void aaa() {

	}

	@Transactional(rollbackFor = Throwable.class, isolation = Isolation.REPEATABLE_READ)
	public int a (Integer id) {
		System.out.println(new Date());
		int update = goodsMapper.updateByPrimaryKeyStore(id);
		//抛出异常
		//int a = 1 / 0;
		return update;

		//TODO 事务是否回滚？
		//TODO 添加了rollbackFor = Exception.class，事务是否回滚？
		//throw new IOException();
	}

	/**
	 * 没有事务注解
	 *
	 * @param id
	 * @return
	 */
	//@Transactional(rollbackFor = Throwable.class, isolation = Isolation.REPEATABLE_READ)
	public int b (Integer id) {
		/*System.out.println(new Date());
		int update = goodsMapper.updateByPrimaryKeyStore(id);
		//抛出异常
		int a = 1 / 0;
		return update;*/

		//return a(id);

		//调用另一个service的a方法
		return orderService.a(id);
	}

	/**
	 * 声明式事务管理：xml + 注解 结合起来使用的
	 *
	 * @param id
	 * @return
	 */
	/*@Transactional(transactionManager="transactionManager", readOnly = false,
			isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED,
			noRollbackFor = IOException.class, rollbackFor = Exception.class, timeout = -1)*/
	public int updateByPrimaryKeyStore(Integer id) {

		System.out.println(new Date());

		int update = goodsMapper.updateByPrimaryKeyStore(id);

		//抛出异常
		int a = 1 / 0;

		return update;
	}

	/**
	 * 减库存 会出现超卖
	 *
	 * @param id
	 * @return
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 */
	/*@Transactional(transactionManager="transactionManager", readOnly = false,
			isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED,
			noRollbackFor = FileNotFoundException.class, rollbackFor = Exception.class, timeout = -1)*/
	public int updateByPrimaryKeyStore2 (Integer id) {
		System.out.println(new Date());

		int update = 0;

		//查一下商品库存
		Goods goods = goodsMapper.selectByPrimaryKey(id);

		//判断库存是否大于0
		if (goods.getStore() > 0) {
			//库存大于0，可以减库存
			update = goodsMapper.updateByPrimaryKeyStore(id);

			if (update > 0) {
				System.out.println("减库存成功，可以下订单");
			} else {
				System.out.println("减库存失败，不能下订单");
				throw new RuntimeException("减库存失败");
			}
		}
		//返回结果
		return update;
	}
}