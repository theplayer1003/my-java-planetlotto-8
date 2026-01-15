package planetlotto.infra.jdbc;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static planetlotto.util.LottoFixture.*;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import planetlotto.domain.ticket.LottoTicket;
import planetlotto.domain.ticket.Purchase;
import planetlotto.domain.ticket.Tickets;
import planetlotto.infra.config.DataSourceConfig;
import planetlotto.util.LottoFixture;

class JdbcLottoRepositoryTest {

    private JdbcLottoRepository repository;

    @BeforeEach
    void setUp() {
        this.repository = new JdbcLottoRepository();
    }

    @AfterEach
    void tearDown() throws SQLException {
        try (final Connection conn = DataSourceConfig.getDataSource()
                .getConnection(); final Statement stmt = conn.createStatement()) {

            stmt.execute("SET FOREIGN_KEY_CHECKS = 0");

            stmt.execute("TRUNCATE TABLE lotto_number");
            stmt.execute("TRUNCATE TABLE lotto");
            stmt.execute("TRUNCATE TABLE purchase");

            stmt.execute("SET FOREIGN_KEY_CHECKS = 1");
        } catch (SQLException e) {
            throw new RuntimeException("테스트 데이터 정리 실패", e);
        }
    }

    @Test
    void save() {
        final Tickets tickets = createTickets(createLottoTicket(1, 2, 3, 4, 5), createLottoTicket(1, 2, 3, 4, 5),
                createLottoTicket(1, 2, 3, 4, 8), createLottoTicket(10, 11, 12, 13, 14));

        final Purchase purchase = Purchase.buy(2000, tickets);

        final Long savedId = repository.save(purchase);


        assertThat(savedId).isNotNull();

        assertThat(purchase.getId()).isEqualTo(savedId);

        final List<LottoTicket> savedTickets = purchase.getTickets().getTickets();
        assertThat(savedTickets).hasSize(4);
        assertThat(savedTickets.get(0).getId()).isNotNull();
        assertThat(savedTickets.get(1).getId()).isNotNull();
    }
}