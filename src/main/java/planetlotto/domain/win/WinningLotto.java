package planetlotto.domain.win;

import java.util.Objects;
import planetlotto.domain.match.LottoResult;
import planetlotto.domain.match.Prize;
import planetlotto.domain.ticket.LottoNumber;
import planetlotto.domain.ticket.LottoTicket;
import planetlotto.domain.ticket.Tickets;

public class WinningLotto {
    private final LottoTicket mainNumber;
    private final LottoNumber bonusNumber;

    public WinningLotto(LottoTicket mainNumber, LottoNumber bonusNumber) {
        Objects.requireNonNull(mainNumber);
        Objects.requireNonNull(bonusNumber);

        this.mainNumber = mainNumber;
        this.bonusNumber = bonusNumber;
    }

    public static WinningLotto of(LottoTicket mainNumber, int bonusNumber) {
        final LottoNumber bonus = new LottoNumber(bonusNumber);
        validateDuplicate(mainNumber, bonus);

        return new WinningLotto(mainNumber, bonus);
    }

    private static void validateDuplicate(LottoTicket mainNumber, LottoNumber bonus) {
        if (mainNumber.contains(bonus)) {
            throw new IllegalArgumentException("보너스 번호는 다른 로또 번호와 중복될 수 없습니다.");
        }
    }

    public Prize match(LottoTicket target) {
        final int matchCount = mainNumber.countMatch(target);
        final boolean bonusMatch = target.checkBonus(bonusNumber);

        return Prize.valueOf(matchCount, bonusMatch);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof WinningLotto that)) {
            return false;
        }
        return Objects.equals(mainNumber, that.mainNumber) && Objects.equals(bonusNumber,
                that.bonusNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mainNumber, bonusNumber);
    }

    public LottoTicket getMainNumber() {
        return mainNumber;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
