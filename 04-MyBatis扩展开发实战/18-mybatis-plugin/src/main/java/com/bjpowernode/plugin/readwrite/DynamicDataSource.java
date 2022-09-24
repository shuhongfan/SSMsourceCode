package com.bjpowernode.plugin.readwrite;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * key : READ --> value : readDataSource
     * key : WRITE --> value : writeDataSource
     *
     * @return
     */
    protected Object determineCurrentLookupKey() {
        return DataSourceHolder.getDataSourceType().name();
    }
}