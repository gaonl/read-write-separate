package com.zoe.common.rw;

import com.zoe.common.rw.annotation.MasterSlaveEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ConditionalOnBean(name = {MasterSlaveEnum.MASTER_NAME, MasterSlaveEnum.SLAVE_NAME})
public class RWDataSourceConfiguration {
    @Autowired(required = true)
    @Qualifier(value = MasterSlaveEnum.MASTER_NAME)
    private DataSource master;

    @Autowired(required = true)
    @Qualifier(value = MasterSlaveEnum.SLAVE_NAME)
    private DataSource slave;

    /**
     * 数据源
     *
     * @return 返回配置的数据源
     */
    @Bean("dataSource")
    @Qualifier("dataSource")
    @Primary
    @DependsOn(value = {MasterSlaveEnum.MASTER_NAME, MasterSlaveEnum.SLAVE_NAME})
    @ConditionalOnBean(name = {MasterSlaveEnum.MASTER_NAME, MasterSlaveEnum.SLAVE_NAME})
    DataSource dataSource() {
        ZoeReadWriteDataSource dataSource = new ZoeReadWriteDataSource();

        Map<Object, Object> targetDataSources = new HashMap<>(2);
        targetDataSources.put(MasterSlaveEnum.MASTER.getName(), master);
        targetDataSources.put(MasterSlaveEnum.SLAVE.getName(), slave);
        dataSource.setTargetDataSources(targetDataSources);

        return dataSource;
    }
}
