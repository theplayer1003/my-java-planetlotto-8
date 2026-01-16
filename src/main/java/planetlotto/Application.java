package planetlotto;

import planetlotto.infra.jdbc.JdbcLottoRepository;
import planetlotto.service.LottoService;
import planetlotto.ui.LottoController;

public class Application {
    public static void main(String[] args) {
        final LottoController lottoController = new LottoController(new LottoService(new JdbcLottoRepository()));

        lottoController.run();
    }
}
