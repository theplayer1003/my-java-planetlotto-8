package planetlotto.infra.jdbc;

import java.sql.SQLException;
import javax.sql.DataSource;

public class JdbcLottoRepository {
    private final DataSource dataSource;

    public JdbcLottoRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save() throws SQLException {

    }
}
