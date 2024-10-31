package com.evently.user.datasource;

import lombok.Getter;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * Data source, which routes queries to master and slave DB nodes.
 * See {@link MasterSlaveDataSourcesConfiguration} for details.
 */
public class MasterSlaveRoutingDataSource extends AbstractRoutingDataSource {

    @Getter
    private enum DataSourceType {
        MASTER("masterDataSource"),
        SLAVE("slaveDataSource");

        private final String dataSourceName;

        DataSourceType(String dataSourceName) {
            this.dataSourceName = dataSourceName;
        }
    }

    private static final ThreadLocal<DataSourceType> dataSourceType =
            ThreadLocal.withInitial(() -> DataSourceType.MASTER);

    static void setSlaveDataSource() {
        dataSourceType.set(DataSourceType.SLAVE);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceType.get().getDataSourceName();
    }
}
