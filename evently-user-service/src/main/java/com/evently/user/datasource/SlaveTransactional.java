package com.evently.user.datasource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a method as containing queries that can all be executed on slave/replica DB nodes.
 * See {@link MasterSlaveDataSourcesConfiguration} for details.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SlaveTransactional {
}
