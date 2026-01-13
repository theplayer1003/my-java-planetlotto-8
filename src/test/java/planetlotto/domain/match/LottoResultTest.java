package planetlotto.domain.match;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.EnumMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class LottoResultTest {

    @Test
    void LottoResult_CreateSuccess() {
        final LottoResult lottoResult = createResultMap();

        assertThat(lottoResult).isNotNull();
    }

    @Test
    void getStatistics() {
        final LottoResult lottoResult = createResultMap();

        final Map<Integer, Integer> statistics = lottoResult.getStatistics();

        assertThat(statistics.get(Prize.FIRST.getIndex())).isEqualTo(0);
        assertThat(statistics.get(Prize.SECOND.getIndex())).isEqualTo(1);
        assertThat(statistics.get(Prize.NONE.getIndex())).isEqualTo(100);
    }

    private static LottoResult createResultMap() {
        return new LottoResult(new EnumMap<>(
                Map.of(Prize.FIRST, 0,
                        Prize.SECOND, 1,
                        Prize.THRID, 1,
                        Prize.FORTH, 0,
                        Prize.FIFTH, 0,
                        Prize.NONE, 100)));
    }
}