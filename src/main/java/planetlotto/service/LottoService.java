package planetlotto.service;

import java.util.List;
import planetlotto.domain.match.LottoResult;
import planetlotto.domain.ticket.LottoTicket;
import planetlotto.domain.ticket.Tickets;
import planetlotto.domain.win.WinningLotto;

public class LottoService {

    public Tickets buyTicketsFromAmount(int amount) {
        final Tickets tickets = Tickets.buyTickets(amount);

        return tickets;
    }

    public LottoResult calculateResult(List<Integer> mainNumber, int bonusNumber, Tickets tickets) {
        final LottoTicket lottoTicket = LottoTicket.of(mainNumber);

        final WinningLotto winningLotto = WinningLotto.of(lottoTicket, bonusNumber);

        return tickets.matchAll(winningLotto);
    }
}
