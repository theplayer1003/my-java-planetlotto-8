package planetlotto.domain.ticket;

import java.util.List;
import java.util.Objects;

public class LottoTicket {
    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(List<LottoNumber> lottoNumbers) {
        Objects.requireNonNull(lottoNumbers);

        this.lottoNumbers = lottoNumbers;
    }
}
