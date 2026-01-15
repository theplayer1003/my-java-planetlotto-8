package planetlotto.infra.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import javax.sql.DataSource;
import planetlotto.domain.ticket.LottoTicket;
import planetlotto.domain.ticket.Purchase;
import planetlotto.infra.config.DataSourceConfig;

public class JdbcLottoRepository {
    private final DataSource dataSource;

    public JdbcLottoRepository() {
        this.dataSource = DataSourceConfig.getDataSource();
    }

    public Long save(Purchase purchase) {
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);

            savePurchase(conn, purchase);
            saveLottoTickets(conn, purchase);

            conn.commit();


        } catch (SQLException e) {
            rollback(conn);

            e.printStackTrace();
        } finally {
            close(conn);
        }

        return purchase.getId();
    }

    private void savePurchase(Connection conn, Purchase purchase) throws SQLException {
        String sql = "INSERT INTO purchase (amount, purchase_date) VALUES (?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, purchase.getAmount());
            pstmt.setTimestamp(2, Timestamp.valueOf(purchase.getPurchaseDate()));
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    final long generatedId = rs.getLong(1);
                    purchase.assignId(generatedId);
                } else {
                    throw new SQLException("Purchase ID 생성 실패");
                }
            }
        }
    }

    private void saveLottoTickets(Connection conn, Purchase purchase) throws SQLException {
        String sql = "INSERT INTO lotto (purchase_id, created_at) VALUES (?, ?)";
        final List<LottoTicket> tickets = purchase.getTickets().getTickets();

        for (LottoTicket ticket : tickets) {
            try (final PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setLong(1, purchase.getId());
                pstmt.setTimestamp(2, Timestamp.valueOf(purchase.getPurchaseDate()));
                pstmt.executeUpdate();

                try (final ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        final long ticketId = rs.getLong(1);
                        ticket.assignId(ticketId);
                        saveLottoNumbers(conn, ticketId, ticket.getNumbers());
                    }
                }
            }
        }
    }

    private void saveLottoNumbers(Connection conn, long ticketId, List<Integer> lottoNumbers) throws SQLException {
        String sql = "INSERT INTO lotto_number (lotto_id, number) VALUES (?, ?)";

        try (final PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (Integer lottoNumber : lottoNumbers) {
                pstmt.setLong(1, ticketId);
                pstmt.setInt(2, lottoNumber);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        }
    }

    private static void rollback(Connection conn) {
        try {
            if (conn != null) {
                conn.rollback();
            }
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
    }

    private static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
