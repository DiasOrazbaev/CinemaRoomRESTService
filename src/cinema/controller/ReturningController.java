package cinema.controller;

import cinema.model.CinemaRoom;
import cinema.requests.ReturnRequest;
import cinema.requests.StatisticResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
public class ReturningController {
    private final CinemaRoom cinemaRoom;
    private final StatisticResponse response;

    public ReturningController(CinemaRoom cinemaRoom, StatisticResponse response) {
        this.cinemaRoom = cinemaRoom;
        this.response = response;
    }

    @PostMapping("/return")
    public ResponseEntity<Object> returnTicket(@RequestBody ReturnRequest request) {
        if (!cinemaRoom.isSold(UUID.fromString(request.token()))) {
            return new ResponseEntity<>(Map.of("error", "Wrong token!"), HttpStatus.BAD_REQUEST);
        }
        var seat = cinemaRoom.returnTicket(UUID.fromString(request.token()));
        response.returnTicket(seat);
        return new ResponseEntity<>(Map.of("returned_ticket", seat), HttpStatus.OK);
    }
}
