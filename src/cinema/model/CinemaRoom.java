package cinema.model;

import cinema.response.TicketResponse;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class CinemaRoom {
    private final int total_rows = 9;
    private final int total_columns = 9;
    private final Set<Seat> set = new HashSet<>();
    private final Map<UUID, Seat> sold = new ConcurrentHashMap<>();

    public CinemaRoom() {
        for (int i = 1; i <= total_rows; i++) {
            for (int j = 1; j <= total_columns; j++) {
                int price = (i <= 4 ? 10 : 8);
                set.add(new Seat(i, j, price));
            }
        }
    }

    public TicketResponse buySeat(int row, int column) {
        var temp = new Seat(row, column, (row <= 4 ? 10 : 8));
        if (set.contains(temp)) {
            set.remove(temp);
            var ticket = new TicketResponse(temp);
            sold.put(ticket.getToken(), ticket.getTicket());
            return ticket;
        }
        return null;
    }

    public Seat returnTicket(UUID token) {
        var seat = sold.remove(token);
        set.add(seat);
        return seat;
    }

    public boolean isSold(UUID token) {
        return sold.containsKey(token);
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public Set<Seat> getSet() {
        return set;
    }
}
