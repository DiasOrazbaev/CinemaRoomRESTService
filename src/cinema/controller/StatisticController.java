package cinema.controller;

import cinema.requests.StatisticResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StatisticController {

    private final StatisticResponse response;

    public StatisticController(StatisticResponse response) {
        this.response = response;
    }

    @PostMapping("/stats")
    public ResponseEntity<Object> statistic(@RequestParam(required = false) String password) {
        if (password == null || !password.equals("super_secret")) {
            return new ResponseEntity<>(Map.of("error", "The password is wrong!"), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(Map.of(
                "current_income", response.getCurrent_income(),
                "number_of_available_seats", response.getNumber_of_available_seats(),
                "number_of_purchased_tickets", response.getNumber_of_purchased_tickets()
        ), HttpStatus.OK);
    }
}
