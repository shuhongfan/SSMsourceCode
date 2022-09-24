package com.bjpowernode.service;

import java.io.IOException;

public interface GoodsService {

	public int updateByPrimaryKeyStore  (Integer id);

	public int updateByPrimaryKeyStore2 (Integer id);

	public int a (Integer id) throws IOException;

	public int b (Integer id);
}