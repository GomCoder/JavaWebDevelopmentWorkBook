package org.zerock.jdbcex.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;

/**
 * HikariDatasource를 이용하여 이에 대한 처리를 쉽게 사용할 수 있도록 생성한 클래스
 */
public enum ConnectionUtil {

    INSTANCE;

    private HikariDataSource ds;

    /**
     * HiikariDatasource 구성
     */
    ConnectionUtil() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setJdbcUrl("jdbc:mariadb://localhost:3308/webdb");
        config.setUsername("webuser");
        config.setPassword("webroot");
        config.addDataSourceProperty("cachePreStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        ds = new HikariDataSource(config);
    }

    /**
     * 외부에서 Connection을 얻을 수 있도록 구성
     * @return
     * @throws Exception
     */
    public Connection getConnection() throws Exception {
        return ds.getConnection();
    }
}
