package planetlotto.domain.ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import planetlotto.domain.match.LottoResult;
import planetlotto.domain.match.Prize;
import planetlotto.domain.win.WinningLotto;

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

    public LottoResult matchAll(WinningLotto winningLotto) {
        EnumMap<Prize, Integer> resultMap = new EnumMap<>(Prize.class);

        for (LottoTicket ticket : tickets) {
            final Prize prize = winningLotto.match(ticket);
            resultMap.merge(prize, 1, Integer::sum);
        }

        return new LottoResult(resultMap);
    }

    public List<LottoTicket> getTickets() {
        return Collections.unmodifiableList(tickets);
    }
}
