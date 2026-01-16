package planetlotto.service;

import java.util.List;

public record BuyTicketsResponseDto(List<List<Integer>> lottos, Long purchaseId) {
}
