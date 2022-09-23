package cinema.controller;

import cinema.handler.SeatsHandler;
import cinema.model.CinemaRoom;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeatController {
    private final CinemaRoom cinemaRoom;

    public SeatController(CinemaRoom cinemaRoom) {
        this.cinemaRoom = cinemaRoom;
    }

    @GetMapping("/seats")
    public ResponseEntity<Object> getCinemaRoom() {
        return new ResponseEntity<>(SeatsHandler.toJSON(cinemaRoom), HttpStatus.OK);
    }
}
