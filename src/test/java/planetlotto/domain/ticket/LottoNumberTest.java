package planetlotto.domain.ticket;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class LottoNumberTest {

    @Test
    void LottoNumber_CreateSuccess(){
        final LottoNumber lottoNumber = new LottoNumber(1);

        assertThat(lottoNumber).isNotNull();

        assertThat(lottoNumber.number()).isEqualTo(1);
    }

    @Test
    void LottoNumber_CreateFail_InvalidRange(){
        assertThatThrownBy(() -> new LottoNumber(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 1부터 30 사이의 숫자여야 합니다.");

        assertThatThrownBy(() -> new LottoNumber(31))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 번호는 1부터 30 사이의 숫자여야 합니다.");
    }
}