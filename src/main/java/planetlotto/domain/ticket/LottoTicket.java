package planetlotto.domain.ticket;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class LottoTicket {
    private Long id;
    private Long purchase_id;
    private LocalDateTime createdAt;

    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(List<LottoNumber> lottoNumbers) {
        Objects.requireNonNull(lottoNumbers);

        this.lottoNumbers = lottoNumbers;
    }

    public LottoTicket(List<LottoNumber> lottoNumbers, LocalDateTime createdAt, Long id) {
        this.lottoNumbers = lottoNumbers;
        this.createdAt = createdAt;
        this.id = id;
    }

    public static LottoTicket of(List<Integer> numbers) {
        validateDuplicate(numbers);
        final List<LottoNumber> lottoNumbers = toLottoNumbers(numbers);

        return new LottoTicket(lottoNumbers);
    }

    private static void validateDuplicate(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);

        if (numbers.size() != uniqueNumbers.size()) {
            throw new IllegalArgumentException("당첨 번호는 중복되지 않은 5개의 숫자여야 합니다.");
        }
    }

    private static List<LottoNumber> toLottoNumbers(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();

        for (Integer number : numbers) {
            final LottoNumber lottoNumber = new LottoNumber(number);
            lottoNumbers.add(lottoNumber);
        }

        return lottoNumbers;
    }

    public boolean contains(LottoNumber target) {
        return lottoNumbers.contains(target);
    }

    public int countMatch(LottoTicket target) {
        return (int) lottoNumbers.stream()
                .filter(target::contains)
                .count();
    }

    public boolean checkBonus(LottoNumber bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof LottoTicket that)) {
            return false;
        }
        return Objects.equals(lottoNumbers, that.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(lottoNumbers);
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    public List<Integer> getNumbers() {
        return lottoNumbers.stream()
                .map(LottoNumber::number)
                .sorted()
                .toList();
    }

    public void assignId(long ticketId) {
        if (this.id != null) {

        }

        this.id = ticketId;
    }

    public Long getId() {
        return this.id;
    }
}
