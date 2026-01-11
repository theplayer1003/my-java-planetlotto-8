package planetlotto.domain.ticket;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TicketsTest {

    @Test
    void buyTickets_CreateSuccess(){
        final Tickets expectOneTicket = Tickets.buyTickets(999);
        final Tickets expectTwoTicket = Tickets.buyTickets(1000);

        assertThat(expectOneTicket).isNotNull();
        assertThat(expectTwoTicket).isNotNull();

        assertThat(expectOneTicket.getTickets().size()).isEqualTo(1);
        assertThat(expectTwoTicket.getTickets().size()).isEqualTo(2);
    }

}