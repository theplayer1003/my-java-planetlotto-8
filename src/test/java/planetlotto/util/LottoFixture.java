package planetlotto.util;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import planetlotto.domain.match.LottoResult;
import planetlotto.domain.match.Prize;
import planetlotto.domain.ticket.LottoTicket;
import planetlotto.domain.ticket.Tickets;
import planetlotto.domain.win.WinningLotto;

public class LottoFixture {

    public static LottoResult createResultMap() {
        return new LottoResult(new EnumMap<>(
                Map.of(Prize.FIRST, 0,
                        Prize.SECOND, 1,
                        Prize.THRID, 1,
                        Prize.FORTH, 0,
                        Prize.FIFTH, 0,
                        Prize.NONE, 100)));
    }

    public static LottoTicket createLottoTicket(Integer... numbers) {
        return LottoTicket.of(List.of(numbers));
    }

    public static WinningLotto createWinningLotto(Integer bonus, Integer... numbers) {
        return WinningLotto.of(createLottoTicket(numbers), bonus);
    }

    public static Tickets createTickets(LottoTicket... tickets) {
        return new Tickets(List.of(tickets));
    }
}
