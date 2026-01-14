package planetlotto.infra.config;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;

class DataSourceConfigTest {

    @Test
    void getDatasource_Success(){
        final DataSource dataSource = DataSourceConfig.getDataSource();

        try (Connection connection = dataSource.getConnection()) {
            assertThat(connection).isNotNull();

            assertThat(connection.isValid(1)).isTrue();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("DB 연결 실패 " + e.getMessage());
        }
    }
}