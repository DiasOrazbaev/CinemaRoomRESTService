package cinema.response;

import cinema.model.Seat;

import java.util.UUID;

public class TicketResponse {
    private final UUID token;
    private final Seat ticket;

    public TicketResponse(Seat ticket) {
        this.token = UUID.randomUUID();
        this.ticket = ticket;
    }

    public UUID getToken() {
        return token;
    }

    public Seat getTicket() {
        return ticket;
    }
}
