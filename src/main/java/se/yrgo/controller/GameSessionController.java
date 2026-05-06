package se.yrgo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.yrgo.domain.GameSession;
import se.yrgo.service.GameSessionService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/gamesessions")
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

    @GetMapping("/{id}")
    public GameSession getById(@PathVariable int id) {
        return gameSessionService.getById(id);
    }

    @GetMapping("/game/{gameId}")
    public List<GameSession> getByGameId(@PathVariable int gameId) {
        return gameSessionService.getByGameId(gameId);
    }

    @GetMapping("/date/{date}")
    public List<GameSession> findByDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return gameSessionService.findByDate(localDate);
    }

    @PostMapping
    public String create(@RequestBody GameSession session) {
        gameSessionService.create(session);
        return "Game session created.";
    }

    @PutMapping
    public String update(@RequestBody GameSession session) {
        gameSessionService.update(session);
        return "Game session updated.";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        gameSessionService.delete(id);
        return "Game session deleted.";
    }
}