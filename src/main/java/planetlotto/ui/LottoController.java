package planetlotto.ui;

import java.util.List;
import planetlotto.domain.match.LottoResult;
import planetlotto.domain.ticket.LottoTicket;
import planetlotto.domain.ticket.Tickets;
import planetlotto.service.LottoService;
import planetlotto.ui.view.InputView;
import planetlotto.ui.view.OutputView;
import planetlotto.util.RetryHandler;

public class LottoController {
    private final LottoService lottoService;

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    public void run() {
        final Tickets tickets = buyTickets();

        printStatics(tickets);
    }

    private Tickets buyTickets() {
        final Tickets tickets = RetryHandler.retryUntilSuccess(() -> {
            final int amount = InputView.askAmount();

            return lottoService.buyTicketsFromAmount(amount);
        }, OutputView::printErrorMessage);

        final List<List<Integer>> lottos = tickets.getTickets().stream()
                .map(LottoTicket::getNumbers)
                .toList();

        OutputView.printPurchasedLottos(lottos);

        return tickets;
    }

    private void printStatics(Tickets tickets) {
        final List<Integer> integers = RetryHandler.retryUntilSuccess(InputView::askWinningLotto,
                OutputView::printErrorMessage);

        final LottoResult lottoResult = RetryHandler.retryUntilSuccess(() -> {
            final int bonus = InputView.askBonusNumber();

            return lottoService.calculateResult(integers, bonus, tickets);
        }, OutputView::printErrorMessage);

        OutputView.printResult(lottoResult.getStatistics());
    }
}
