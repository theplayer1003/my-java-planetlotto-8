package planetlotto.domain.ticket;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class LottoNumberGenerator {

    public static List<LottoNumber> generateFiveNumber() {
        final List<Integer> integers = Randoms.pickUniqueNumbersInRange(1, 30, 5);

        List<LottoNumber> ticket = new ArrayList<>();

        for (Integer integer : integers) {
            final LottoNumber lottoNumber = new LottoNumber(integer);
            ticket.add(lottoNumber);
        }

        return ticket;
    }
}
