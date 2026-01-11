package planetlotto.domain.ticket;

import java.util.List;
import java.util.Objects;

public class Tickets {
    private final List<LottoTicket> tickets;

    public Tickets(List<LottoTicket> tickets) {
        Objects.requireNonNull(tickets);

        this.tickets = tickets;
    }
}
