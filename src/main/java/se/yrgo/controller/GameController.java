package se.yrgo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.yrgo.domain.Game;
import se.yrgo.service.GameService;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {
    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        return   ResponseEntity.ok(gameService.getAllGames());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable int id) {
        Game game = gameService.getGameById(id);

        if (game == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(game);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Game> getGameByName(@PathVariable String name) {
        Game game = gameService.getGameByName(name);

        if (game == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(game);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable int id) {
        gameService.deleteGameById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Game> purchaseGame(@RequestBody Game newGame) {
        gameService.purchaseGame(newGame);
        return ResponseEntity.status(HttpStatus.CREATED).body(newGame);
    }
}
