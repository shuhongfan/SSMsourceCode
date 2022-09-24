package com.bjpowernode.mapper;

import com.bjpowernode.model.Goods;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsMapper {

	@Delete("delete from goods where id = ")
    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

	int updateByPrimaryKeyStore(Integer id);
}