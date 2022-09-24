package com.bjpowernode.service.impl;

import com.bjpowernode.mapper.GoodsMapper;
import com.bjpowernode.model.Goods;
import com.bjpowernode.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl implements GoodsService {

	// MapperFactoryBean
	@Autowired
	private GoodsMapper goodsMapper;

	public Goods selectByPrimaryKey(Integer id) {
		return goodsMapper.selectByPrimaryKey(id);
	}
}