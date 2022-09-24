package com.bjpowernode.service;

import com.bjpowernode.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyGoodsService {

	@Autowired
	private GoodsMapper goodsMapper;

	/**
	 * 有事务管理
	 *
	 * @param id
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public int a (Integer id) {
		int update = goodsMapper.updateByPrimaryKeyStore(id);
		//抛出异常
		//int a = 1 / 0;
		return update;
	}

	@Transactional
	protected int b (Integer id) {
		int update = goodsMapper.updateByPrimaryKeyStore(id);
		//抛出异常
		//int a = 1 / 0;
		return update;
	}

	@Transactional
	int c (Integer id) {
		int update = goodsMapper.updateByPrimaryKeyStore(id);
		//抛出异常
		int a = 1 / 0;
		return update;
	}
}