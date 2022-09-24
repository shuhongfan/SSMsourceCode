package com.bjpowernode.plugin.pager;

/**
 * 分页参数
 *
 */
public class PageParam {

    //当前是第几页
    private int index = 0;

    //每页显示多少条
    private int rows = Integer.MAX_VALUE;

    public PageParam() {
    }

    public PageParam(int index, int rows) {
        this.index = index;
        this.rows = rows;
    }

    /**
     * 获取查询的起始行，从哪一行开始查
     *
     * @return
     */
    public int getOffset() {
        if (index > 0) {
            return (index - 1) * rows;
        }
        return 0;
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
}