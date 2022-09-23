package cinema.handler;

import cinema.model.CinemaRoom;

import java.util.HashMap;
import java.util.Map;

public class SeatsHandler {
    public static Map<String, Object> toJSON(CinemaRoom cinemaRoom) {
        Map<String, Object> map = new HashMap<>();
        map.put("total_rows", cinemaRoom.getTotal_rows());
        map.put("total_columns", cinemaRoom.getTotal_columns());
        map.put("available_seats", cinemaRoom.getSet());
        return map;
    }
}
