package com.evently.user.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class RoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        final boolean isReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();

        return isReadOnly ? "slaveDataSource" : "masterDataSource";
    }
}
