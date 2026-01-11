# java-planetlotto-precourse

# 기능구현 목록

- [ ] 로또 티켓
    - [ ] 로또 티켓은 중복되지 않은 5개의 로또 번호를 가진다
    - [x] 로또 번호는 1 부터 30 사이의 숫자이다
    - [ ] 로또 1장의 가격은 500원 이다.

- [ ] 당첨 번호
    - [ ] 당첨 번호는 중복되지 않은 숫자 5개와 보너스 번호 1개를 가진다

- [ ] 당첨 금액
    - [ ] 1등, 5개 번호, 보너스 번호 false, 100_000_000
    - [ ] 2등, 4개 번호, 보너스 번호 true, 10_000_000
    - [ ] 3등, 4개 번호, 보너스 번호 false, 1_500_000
    - [ ] 4등, 3개 번호, 보너스 번호 true, 500_000
    - [ ] 5등, 2개 번호, 보너스 번호 true, 5_000
    - [ ] 미당첨, 1개 번호, 보너스 번호 false, 0

# 지정 에러 메시지

- `[ERROR]` prefix
- 로또 번호는 1부터 30 사이의 숫자여야 합니다.

# 애플리케이션 흐름

- `LottoController` 가 `InputView` 에게 구입 금액을 요청한다
    - `InputView` 가 `inputView.askAmount()` 를 실행해 `int` 를 반환한다
- `LottoController` 가 `LottoService` 에게 구입금액을 넘겨주며 로또 티켓 생성을 요청한다
    - `LottoService` 가 `LottoTicket` 에게 구입금액을 넘겨주며 로또 티켓 생성을 요청한다
        - `LottoTicket` 가 구입금액을 검증한다
            - 구입금액은 500 보다 큰 정수여야 한다
        - `LottoTicket` 가 스스로를 생성해 반환한다
    - `LottoService` 가 `LottoTicket`을 반환한다
- `LottoController` 가 `OutputView` 에게 알맞은 값을 넘겨주며 출력을 요청한다

- `LottoController` 가 `InputView` 에게 당첨 번호를 요청한다
    - `InputView` 가 `inputView.askWinningLotto()` 를 실행해 `List<Integer>` 를 반환한다
- `LottoController` 가 `InputView` 에게 보너스 번호를 요청한다
    - `InputView` 가 `inputview.askBonusNumber()` 를 실행해 `int` 를 반환한다
- `LottoController` 가 `LottoService` 에게 `List<Integer>` 와 `int` 와 `LottoTicket` 를 넘겨주며 당첨 결과를 요청한다
    - `LottoService` 는 `WinningLotto` 를 생성해 반환한다

# 도메인 식별

- `LottoNumber`: 로또 숫자
    - 로또 숫자는 1부터 30 사이의 숫자이다

- `LottoTicket`: 로또 티켓 1장

- `Tickets`: 구매한 로또 티켓들

- `WinningLotto`: 1등 당첨 번호 및 보너스 번호

- `LottoResult`: 당첨 결과

- `Prize`: 추첨 정보