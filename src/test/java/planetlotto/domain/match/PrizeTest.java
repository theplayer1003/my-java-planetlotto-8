package planetlotto.domain.match;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PrizeTest {

    @Test
    void valueOf_CreateSuccess() {
        final Prize first = Prize.valueOf(5, false);
        final Prize second = Prize.valueOf(4, true);
        final Prize third = Prize.valueOf(4, false);
        final Prize none = Prize.valueOf(2, false);

        assertThat(first.getDescription()).isEqualTo("1등");
        assertThat(second.getDescription()).isEqualTo("2등");
        assertThat(third.getDescription()).isEqualTo("3등");
        assertThat(none.getDescription()).isEqualTo("미당첨");
    }
}