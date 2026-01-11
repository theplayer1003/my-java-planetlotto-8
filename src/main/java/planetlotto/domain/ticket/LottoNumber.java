package planetlotto.domain.ticket;

public record LottoNumber(int number) {

    public LottoNumber(int number) {
        validateNumberRange(number);
        this.number = number;
    }

    private void validateNumberRange(int number) {
        if (number > 30 || number < 1) {
            throw new IllegalArgumentException("로또 번호는 1부터 30 사이의 숫자여야 합니다.");
        }
    }
}
