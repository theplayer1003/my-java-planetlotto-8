package planetlotto.service;

import java.util.List;
import planetlotto.domain.ticket.LottoTicket;
import planetlotto.domain.ticket.Tickets;

public class DtoMapper {

    public static BuyTicketsResponseDto from(Tickets tickets, Long purchaseId) {

        return new BuyTicketsResponseDto(
                tickets.getTickets().stream()
                        .map(LottoTicket::getNumbers)
                        .toList(),
                purchaseId);
    }
}
