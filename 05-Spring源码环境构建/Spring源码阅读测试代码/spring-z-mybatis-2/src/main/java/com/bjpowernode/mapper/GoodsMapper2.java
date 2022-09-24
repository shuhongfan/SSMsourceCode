package com.bjpowernode.mapper;

import com.bjpowernode.model.Goods;

public interface GoodsMapper2 {

    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

	int updateByPrimaryKeyStore(Integer id);
}