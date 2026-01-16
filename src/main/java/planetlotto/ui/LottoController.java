package planetlotto.ui;

import java.util.List;
import planetlotto.domain.match.LottoResult;
import planetlotto.domain.ticket.LottoTicket;
import planetlotto.domain.ticket.Tickets;
import planetlotto.service.BuyTicketsResponseDto;
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
        final Long purchaseId = buyTickets();

        printStatics(purchaseId);
    }

    private Long buyTickets() {
        final BuyTicketsResponseDto response = RetryHandler.retryUntilSuccess(() -> {
            final int amount = InputView.askAmount();

            return lottoService.buyTicketsFromAmount(amount);
        }, OutputView::printErrorMessage);

        OutputView.printPurchasedLottos(response.lottos());

        return response.purchaseId();
    }

    private void printStatics(Long purchaseId) {
        final List<Integer> integers = RetryHandler.retryUntilSuccess(InputView::askWinningLotto,
                OutputView::printErrorMessage);

        final LottoResult lottoResult = RetryHandler.retryUntilSuccess(() -> {
            final int bonus = InputView.askBonusNumber();

            return lottoService.calculateResult(integers, bonus, purchaseId);
        }, OutputView::printErrorMessage);

        OutputView.printResult(lottoResult.getStatistics());
    }
}
