package planetlotto.domain.ticket;

import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class LottoTicketTest {

    @Test
    void of_CreateSuccess() {
        final LottoTicket lottoTicket = LottoTicket.of(List.of(1, 2, 3, 4, 5));

        assertThat(lottoTicket).isNotNull();
        // 내부 값 비교는 어떻게 하지? LottoNumber 를 생성해서 비교하나?
    }

    @Test
    void of_CreateFail_Duplicate(){
        assertThatThrownBy(() -> LottoTicket.of(List.of(1, 2, 3, 4, 4)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("당첨 번호는 중복되지 않은 5개의 숫자여야 합니다.");
    }
}