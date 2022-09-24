package com.bjpowernode.service.impl;

import com.bjpowernode.mapper.GoodsMapper;
import com.bjpowernode.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private GoodsMapper goodsMapper;

	@Transactional(rollbackFor = Exception.class)
	public int a (Integer id) {
		System.out.println(new Date());
		int update = goodsMapper.updateByPrimaryKeyStore(id);
		//抛出异常
		int a = 1 / 0;
		return update;

		//TODO 事务是否回滚？
		//TODO 添加了rollbackFor = Exception.class，事务是否回滚？
		//throw new IOException();
	}
}