package com.bjpowernode.plugin.readwrite;

public class DataSourceHolder {

    //当前线程变量副本
    private static final ThreadLocal<DataSourceType> holder = new ThreadLocal<>();

    /**
     * 获取数据源类型
     *
     * @return
     */
    public static DataSourceType getDataSourceType() {
        DataSourceType dataSourceType = holder.get();
        if (dataSourceType == null) {
            //默认是读库
            dataSourceType = DataSourceType.READ;
        }
        return dataSourceType;
    }

    /**
     * 设置数据源
     *
     * @param dataSourceType
     */
    public static void setDataSourceType(DataSourceType dataSourceType) {
        holder.set(dataSourceType);
    }
}