package se.yrgo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.yrgo.domain.Game;
import se.yrgo.domain.GameSession;
import se.yrgo.service.GameService;
import se.yrgo.service.GameSessionService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

/**
 * REST controller for managing GameSession resources.
 * <p>
 * Provides HTTP endpoints for retrieving, creating, updating, and deleting
 * {@link GameSession} entities. Acts as the entry point between the client and
 * the {@link GameSessionService} layer.
 * <p>
 * Delegates all business logic to the service layer and is responsible for:
 * <ul>
 *     <li>Handling HTTP requests and responses</li>
 *     <li>Validating basic input parameters</li>
 *     <li>Mapping service exceptions to HTTP responses</li>
 * </ul>
 */
@RestController
@RequestMapping("/gamesessions")
@CrossOrigin(origins = "http://localhost:5173")
public class GameSessionController {

    private GameSessionService gameSessionService;

    @Autowired
    public GameSessionController(GameSessionService gameSessionService) {
        this.gameSessionService = gameSessionService;
    }

    @GetMapping
    public List<GameSession> getAll() {
        return gameSessionService.getAllGameSessions();
    }

    @GetMapping("id/{id}")
    public GameSession getById(@PathVariable int id) {
        if (id < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid 'id' parameter.");
        }

        return gameSessionService.getById(id);
    }

    @GetMapping("game/{gameId}")
    public List<GameSession> getByGameId(@PathVariable int gameId) {
        if (gameId < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid 'gameId' parameter.");
        }

        return gameSessionService.getByGameId(gameId);
    }

    @GetMapping("date/{date}")
    public List<GameSession> getByDate(@PathVariable String date) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            return gameSessionService.getByDate(localDate);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid date parameter. Use format yyyy-MM-dd."
            );
        }
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GameSession createGameSession(@RequestBody Map<String, Object> body) {

        int gameId = (int) body.get("gameId");
        LocalDate date = LocalDate.parse((String) body.get("date"));

        return gameSessionService.createGameSession(gameId, date);
    }

    @PutMapping("/{id}")
    public void updateGameSession(
            @PathVariable int id,
            @RequestParam(required = false) Integer gameId,
            @RequestParam(required = false) String date
    ) {
        if (id < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid 'id' parameter.");
        }

        LocalDate localDate = null;

        if (date != null) {
            try {
                localDate = LocalDate.parse(date);
            } catch (DateTimeParseException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date parameter. Use format yyyy-MM-dd.");
            }
        }

        gameSessionService.updateGameSession(id, gameId, localDate);
    }

    //GameSessions can only be deleted if the session have no participants
    @DeleteMapping("/{id}")
    public void deleteGameSession(@PathVariable int id) {
        if (id < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid 'id' parameter.");
        }

        gameSessionService.deleteGameSession(id);
    }
}