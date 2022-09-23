package cinema.controller;

import cinema.model.CinemaRoom;
import cinema.requests.PurchaseRequest;
import cinema.requests.StatisticResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PurchaseController {
    private final CinemaRoom cinemaRoom;
    private final StatisticResponse response;

    public PurchaseController(CinemaRoom cinemaRoom, StatisticResponse response) {
        this.cinemaRoom = cinemaRoom;
        this.response = response;
    }

    @PostMapping("/purchase")
    public ResponseEntity<Object> purchase(@RequestBody PurchaseRequest request) {
        if (request.row() < 0 || request.column() < 0 || request.row() > 9 || request.column() > 9) {
            return new ResponseEntity<>(Map.of("error", "The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
        }

        var result = cinemaRoom.buySeat(request.row(), request.column());
        if (result == null) {
            return new ResponseEntity<>(Map.of("error", "The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
        }
        response.buyTicket(result.getTicket());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
