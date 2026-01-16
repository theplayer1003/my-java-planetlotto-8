package planetlotto.service;

import java.util.List;
import planetlotto.domain.match.LottoResult;
import planetlotto.domain.ticket.LottoTicket;
import planetlotto.domain.ticket.Purchase;
import planetlotto.domain.ticket.Tickets;
import planetlotto.domain.win.WinningLotto;
import planetlotto.infra.jdbc.JdbcLottoRepository;

public class LottoService {
    private final JdbcLottoRepository lottoRepository;

    public LottoService(JdbcLottoRepository lottoRepository) {
        this.lottoRepository = lottoRepository;
    }

    public BuyTicketsResponseDto buyTicketsFromAmount(int amount) {
        final Tickets tickets = Tickets.buyTickets(amount);
        final Purchase purchase = Purchase.buy(amount, tickets);

        final Long purchaseId = lottoRepository.save(purchase);

        return DtoMapper.from(tickets, purchaseId);
    }

    public LottoResult calculateResult(List<Integer> mainNumber, int bonusNumber, Long purchaseId) {
        final Purchase purchase = lottoRepository.findById(purchaseId);
        final Tickets tickets = purchase.getTickets();

        final LottoTicket lottoTicket = LottoTicket.of(mainNumber);

        final WinningLotto winningLotto = WinningLotto.of(lottoTicket, bonusNumber);

        return tickets.matchAll(winningLotto);
    }
}
