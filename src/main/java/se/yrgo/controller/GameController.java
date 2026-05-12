package se.yrgo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.yrgo.domain.Game;
import se.yrgo.error.GameNotFoundException;
import se.yrgo.service.GameService;

import java.time.LocalDate;
import java.util.List;

/**
 * REST controller for managing Game resources.
 * <p>
 * Provides HTTP endpoints for retrieving, creating, updating, and deleting
 * {@link Game} entities. Acts as the entry point between the client and
 * the {@link GameService} layer.
 * <p>
 * Delegates all business logic to the service layer and is responsible for:
 * <ul>
 *     <li>Handling HTTP requests and responses</li>
 *     <li>Validating basic input parameters</li>
 *     <li>Mapping service exceptions to HTTP responses</li>
 * </ul>
 */
@RestController
@RequestMapping("/games")
@CrossOrigin(origins = "http://localhost:5173")
public class GameController {
    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/{id}")
    public Game getGameById(@PathVariable int id) {
        if (id < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid game id.");
        }
        try {
            Game game = gameService.getGameById(id);

            return game;
        } catch (GameNotFoundException e) {
            System.err.println(e.getMessage());
            throw new GameNotFoundException(e);
        }
    }

    @GetMapping("/name/{name}")
    public Game getGameByName(@PathVariable String name) {
        if (name == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid game name.");
        }
        try {
            Game game = gameService.getGameByName(name);
            return game;
        } catch (GameNotFoundException e) {
            System.err.println(e.getMessage());
            throw new GameNotFoundException(e);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable int id) {
        if(id < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid game id.");
        }
        gameService.deleteGame(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void purchaseGame(@RequestBody Game newGame) {
        if (newGame == null
                || newGame.getGameName() == null
                || newGame.getGameName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid game data.");
        }
            gameService.purchaseGame(newGame);
    }

    @PutMapping("/{id}")
    public void updateGame(
            @PathVariable int id,
            @RequestParam(required = false) String gameName,
            @RequestParam(required = false) LocalDate purchaseDate,
            @RequestParam(required = false) int totalPlayers,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) int recommendedAge,
            @RequestParam(required = false) int averagePlayTime,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String memberComment
    ) {
        if (id < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid 'id' parameter.");
        }
        gameService.updateGame(id, gameName, purchaseDate, totalPlayers, category, recommendedAge, averagePlayTime, description, memberComment);
    }
}
