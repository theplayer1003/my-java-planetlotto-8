package planetlotto.domain.ticket;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class LottoTicketTest {

    @Test
    void of_CreateSuccess() {
        final LottoTicket lottoTicket = createLottoTicket(1, 2, 3, 4, 5);

        assertThat(lottoTicket).isNotNull();

        // 내부 값 비교는 어떻게 하지? LottoNumber 를 생성해서 비교하나?
        assertThat(lottoTicket.getLottoNumbers())
                .extracting("number")
                .containsExactlyInAnyOrder(1, 2, 3, 4, 5);
    }

    @Test
    void of_CreateFail_Duplicate() {
        assertThatThrownBy(() -> createLottoTicket(1, 2, 3, 4, 4))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("당첨 번호는 중복되지 않은 5개의 숫자여야 합니다.");
    }

    @Test
    void contains() {
        final LottoTicket lottoTicket = createLottoTicket(1, 2, 3, 4, 5);

        final boolean expectedTrue = lottoTicket.contains(new LottoNumber(1));
        final boolean expectedFalse = lottoTicket.contains(new LottoNumber(6));

        assertThat(expectedTrue).isTrue();
        assertThat(expectedFalse).isFalse();
    }

    @Test
    void countMatch() {
        final LottoTicket lottoTicket = createLottoTicket(1, 2, 3, 4, 5);
        final LottoTicket matchFive = createLottoTicket(1, 2, 3, 4, 5);
        final LottoTicket matchFour = createLottoTicket(1, 2, 3, 4, 6);

        final int expectedFive = lottoTicket.countMatch(matchFive);
        final int expectedFour = lottoTicket.countMatch(matchFour);

        assertThat(expectedFive).isEqualTo(5);
        assertThat(expectedFour).isEqualTo(4);
    }

    @Test
    void checkBonus() {
        final LottoTicket lottoTicket = createLottoTicket(1, 2, 3, 4, 5);

        final boolean expectedTrue = lottoTicket.checkBonus(new LottoNumber(1));
        final boolean expectedFalse = lottoTicket.checkBonus(new LottoNumber(6));

        assertThat(expectedTrue).isTrue();
        assertThat(expectedFalse).isFalse();
    }

    private static LottoTicket createLottoTicket(Integer... numbers) {
        return LottoTicket.of(List.of(numbers));
    }
}