package planetlotto.infra.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;

public class DataSourceConfig {

    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/my_java_planetlotto";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "test1234";

    private static DataSource dataSource;

    private DataSourceConfig() {
    }

    public static DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = createDataSource();
        }

        return dataSource;
    }

    private static DataSource createDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(JDBC_URL);
        config.setUsername(USERNAME);
        config.setPassword(PASSWORD);

        config.setMaximumPoolSize(10);
        config.setConnectionTimeout(30000);
        config.setPoolName("planetlotto");

        return new HikariDataSource(config);
    }

}
