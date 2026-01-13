package planetlotto.domain.ticket;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import planetlotto.domain.match.LottoResult;
import planetlotto.domain.win.WinningLotto;

class TicketsTest {

    @Test
    void buyTickets_CreateSuccess() {
        final Tickets expectOneTicket = Tickets.buyTickets(999);
        final Tickets expectTwoTicket = Tickets.buyTickets(1000);

        assertThat(expectOneTicket).isNotNull();
        assertThat(expectTwoTicket).isNotNull();

        assertThat(expectOneTicket.getTickets().size()).isEqualTo(1);
        assertThat(expectTwoTicket.getTickets().size()).isEqualTo(2);
    }

    @Test
    void matchAll() {
        final Tickets tickets = createTickets(
                createLottoTicket(1, 2, 3, 4, 5),
                createLottoTicket(1, 2, 3, 4, 6),
                createLottoTicket(7, 8, 9, 10, 11)
        );

        final WinningLotto winningLotto = createWinningLotto(6, 1, 2, 3, 4, 5);

        final LottoResult lottoResult = tickets.matchAll(winningLotto);

        final Map<Integer, Integer> statistics = lottoResult.getStatistics();

        assertThat(statistics.get(1)).isEqualTo(1);
        assertThat(statistics.get(2)).isEqualTo(1);
        assertThat(statistics.get(3)).isEqualTo(0);
        assertThat(statistics.get(4)).isEqualTo(0);
        assertThat(statistics.get(5)).isEqualTo(0);
        assertThat(statistics.get(0)).isEqualTo(1);
    }

    private static WinningLotto createWinningLotto(Integer bonus, Integer... numbers) {
        return WinningLotto.of(createLottoTicket(numbers), bonus);
    }

    private static Tickets createTickets(LottoTicket... tickets) {
        return new Tickets(List.of(tickets));
    }

    private static LottoTicket createLottoTicket(Integer... numbers) {
        return LottoTicket.of(List.of(numbers));
    }
}