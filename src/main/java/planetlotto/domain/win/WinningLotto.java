package planetlotto.domain.win;

import java.util.Objects;
import planetlotto.domain.ticket.LottoNumber;
import planetlotto.domain.ticket.LottoTicket;

public class WinningLotto {
    private final LottoTicket mainNumber;
    private final LottoNumber bonusNumber;

    public WinningLotto(LottoTicket mainNumber, LottoNumber bonusNumber) {
        Objects.requireNonNull(mainNumber);
        Objects.requireNonNull(bonusNumber);

        this.mainNumber = mainNumber;
        this.bonusNumber = bonusNumber;
    }
}
