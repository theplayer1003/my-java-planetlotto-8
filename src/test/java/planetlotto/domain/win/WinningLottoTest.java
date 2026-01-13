package planetlotto.domain.win;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static planetlotto.util.LottoFixture.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import planetlotto.domain.match.Prize;
import planetlotto.domain.ticket.LottoNumber;
import planetlotto.domain.ticket.LottoTicket;
import planetlotto.util.LottoFixture;

class WinningLottoTest {

    @Test
    void of_CreateSuccess() {
        final WinningLotto winningLotto = WinningLotto.of(LottoTicket.of(List.of(1, 2, 3, 4, 5)), 6);

        assertThat(winningLotto).isNotNull();
        assertThat(winningLotto.getMainNumber()).isEqualTo(LottoTicket.of(List.of(1, 2, 3, 4, 5)));
        assertThat(winningLotto.getBonusNumber()).isEqualTo(new LottoNumber(6));
    }

    @Test
    void of_CreateFail_bonusNumberDuplicate() {
        assertThatThrownBy(() -> WinningLotto.of(LottoTicket.of(List.of(1, 2, 3, 4, 5)), 5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("보너스 번호는 다른 로또 번호와 중복될 수 없습니다.");
    }

    @Test
    void match() {
        final WinningLotto winningLotto = createWinningLotto(6, 1, 2, 3, 4, 5);
        final LottoTicket prizeFirst = createLottoTicket(1, 2, 3, 4, 5);
        final LottoTicket prizeSecond = createLottoTicket(1, 2, 3, 4, 6);

        final Prize expectedFirst = winningLotto.match(prizeFirst);
        final Prize expectedSecond = winningLotto.match(prizeSecond);

        assertThat(expectedFirst).isEqualTo(Prize.FIRST);
        assertThat(expectedSecond).isEqualTo(Prize.SECOND);
    }
}