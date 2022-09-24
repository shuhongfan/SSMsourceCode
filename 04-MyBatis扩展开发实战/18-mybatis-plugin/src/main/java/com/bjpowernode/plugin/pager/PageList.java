package com.bjpowernode.plugin.pager;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 封装一个查询结果的List类
 *
 * @param <E>
 */
public class PageList<E> extends ArrayList<E> {

    //当前是第几页
    private int index;

    //每页显示多少条
    private int rows;

    //数据总条数
    private int total;

    public PageList(Collection<E> collection, int index, int rows, int total) {
        //collection是从数据库查询出来的结果
        super(collection);
        this.index = index;
        this.rows = rows;
        this.total = total;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
