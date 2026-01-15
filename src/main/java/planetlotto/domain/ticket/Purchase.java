package planetlotto.domain.ticket;

import java.time.LocalDateTime;
import java.util.Objects;

public class Purchase {
    private Long id;
    private final int amount;
    private final LocalDateTime purchaseDate;
    private final Tickets tickets;

    private Purchase(Long id, int amount, LocalDateTime purchaseDate, Tickets tickets) {
        this.id = id;
        this.amount = amount;
        this.purchaseDate = purchaseDate;
        this.tickets = tickets;
    }

    public static Purchase buy(int amount, Tickets tickets) {
        validateAmount(amount);
        Objects.requireNonNull(tickets);

        return new Purchase(null, amount, LocalDateTime.now(), tickets);
    }

    private static void validateAmount(int amount) {

    }

    public static Purchase reconstruct(Long id, int amount, LocalDateTime purchaseDate, Tickets tickets) {
        return new Purchase(id, amount, purchaseDate, tickets);
    }

    public void assignId(Long id) {
        if (this.id != null) {

        }

        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public Tickets getTickets() {
        return tickets;
    }
}
