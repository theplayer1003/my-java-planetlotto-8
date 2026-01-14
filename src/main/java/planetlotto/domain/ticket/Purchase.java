package planetlotto.domain.ticket;

import java.time.LocalDateTime;

public class Purchase {
    private final Long id;
    private final int amount;
    private final LocalDateTime purchaseDate;
    private final Tickets tickets;
}
