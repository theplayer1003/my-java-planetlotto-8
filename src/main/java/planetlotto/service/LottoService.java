package planetlotto.service;

import planetlotto.domain.ticket.Tickets;

public class LottoService {

    public Tickets buyTicketsFromAmount(int amount) {
        final Tickets tickets = Tickets.buyTickets(amount);

        return tickets;
    }
}
