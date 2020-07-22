package com.zoe.common.rw;

import com.zoe.common.rw.annotation.MasterSlaveEnum;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class ZoeReadWriteDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceName = DataSourceNameHolder.peek();

        if (dataSourceName == null || "".equals(dataSourceName.trim())) {
            return MasterSlaveEnum.MASTER.getName();
        }

        return dataSourceName;
    }
}
