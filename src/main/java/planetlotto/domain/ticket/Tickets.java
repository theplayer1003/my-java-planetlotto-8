package planetlotto.domain.ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tickets {
    private static final int ONE_TICKET_PRICE = 500;

    private final List<LottoTicket> tickets;

    public Tickets(List<LottoTicket> tickets) {
        Objects.requireNonNull(tickets);

        this.tickets = tickets;
    }

    public static Tickets buyTickets(int amount) {
        final int ticketCount = amount / ONE_TICKET_PRICE;
        List<LottoTicket> tickets = new ArrayList<>();

        for (int i = 0; i < ticketCount; i++) {
            final List<LottoNumber> lottoNumbers = LottoNumberGenerator.generateFiveNumber();
            final LottoTicket lottoTicket = new LottoTicket(lottoNumbers);

            tickets.add(lottoTicket);
        }

        return new Tickets(tickets);
    }

    public List<LottoTicket> getTickets() {
        return tickets;
    }
}
