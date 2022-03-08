package topic1;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceConfig {

    private static HikariDataSource slaveDb;
    private static HikariDataSource masterDb;

   static  {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/homework?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC");
        config.setUsername("root");
        config.setPassword("root");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");
        config.addDataSourceProperty("useLocalSessionState", "true");
        config.addDataSourceProperty("useLocalTransactionState", "true");
        config.addDataSourceProperty("rewriteBatchedStatements", "true");
        config.addDataSourceProperty("cacheResultSetMetadata", "true");
        config.addDataSourceProperty("cacheServerConfiguration", "true");
        config.addDataSourceProperty("elideSetAutoCommits", "true");
        config.addDataSourceProperty("maintainTimeStats", "false");
       slaveDb = new HikariDataSource(config);
       HikariConfig config2 = new HikariConfig();
       config2.setJdbcUrl("jdbc:mysql://localhost:3306/homework?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC");
       config2.setUsername("root");
       config2.setPassword("root");
       config2.addDataSourceProperty("cachePrepStmts", "true");
       config2.addDataSourceProperty("prepStmtCacheSize", "250");
       config2.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
       config2.addDataSourceProperty("useServerPrepStmts", "true");
       config2.addDataSourceProperty("useLocalSessionState", "true");
       config2.addDataSourceProperty("useLocalTransactionState", "true");
       config2.addDataSourceProperty("rewriteBatchedStatements", "true");
       config2.addDataSourceProperty("cacheResultSetMetadata", "true");
       config2.addDataSourceProperty("cacheServerConfiguration", "true");
       config2.addDataSourceProperty("elideSetAutoCommits", "true");
       config2.addDataSourceProperty("maintainTimeStats", "false");
       masterDb = new HikariDataSource(config2);
    }

    public static Connection getConnection() {
        try {
            return slaveDb.getConnection();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    public static Connection getMsConnection() {
        try {
            return masterDb.getConnection();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
