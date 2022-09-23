package cinema.requests;

import cinema.model.Seat;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StatisticResponse {
    private int current_income = 0;
    private int number_of_available_seats = 81;
    private int number_of_purchased_tickets = 0;

    public void buyTicket(Seat seat) {
        current_income += seat.getPrice();
        number_of_purchased_tickets++;
        number_of_available_seats--;
    }

    public void returnTicket(Seat seat) {
        current_income -= seat.getPrice();
        number_of_purchased_tickets--;
        number_of_available_seats++;
    }

    public int getCurrent_income() {
        return current_income;
    }

    public int getNumber_of_available_seats() {
        return number_of_available_seats;
    }

    public int getNumber_of_purchased_tickets() {
        return number_of_purchased_tickets;
    }
}
