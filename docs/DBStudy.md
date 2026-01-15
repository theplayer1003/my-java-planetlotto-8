`boolean isValid(int timeout) throws SQLException;`
타임 아웃: 연결 성사까지 대기할 시간. 이 시간이 지나도록 연결이 안되면 실패한걸로 간주한다.
isValid: DB에 Ping을 던져, 즉 실제로 네트워크 패킷을 보내 물리적으로 연결이 된건지 확인하는 방식
`assertThat(connection.isValid(1)).isTrue();`
1초 동안 대답이 오면 true, 초과하면 false 반환. 0을 넣을 경우 무한대기에 들어간다.


- Purchase 테이블
구매 결과

- LottoTicket 테이블
로또 한 장. 하나의 구매 내역에 여러 장의 로또가 매칭되야 한다. 테이블을 분리하고 1:N 으로 부모 키를 가짐

- LottoNumber 테이블
로또 한장에 해당하는 번호들