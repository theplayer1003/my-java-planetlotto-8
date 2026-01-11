package planetlotto.ui;

import planetlotto.domain.ticket.Tickets;
import planetlotto.service.LottoService;
import planetlotto.ui.view.InputView;
import planetlotto.ui.view.OutputView;

public class LottoController {
    private final LottoService lottoService;

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    public void run() {
        final Tickets tickets = buyTickets();
    }

    private Tickets buyTickets() {
        final int amount = InputView.askAmount();

        final Tickets tickets = lottoService.buyTicketsFromAmount(amount);

        return tickets;
    }
}
