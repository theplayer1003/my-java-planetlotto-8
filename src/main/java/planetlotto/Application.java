package planetlotto;

import planetlotto.service.LottoService;
import planetlotto.ui.LottoController;

public class Application {
    public static void main(String[] args) {
        final LottoController lottoController = new LottoController(new LottoService());

        lottoController.run();
    }
}
