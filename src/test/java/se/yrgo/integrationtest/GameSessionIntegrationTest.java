package se.yrgo.integrationtest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.domain.Game;
import se.yrgo.domain.GameSession;
import se.yrgo.service.GameService;
import se.yrgo.service.GameSessionService;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/test-application.xml")
@Transactional
public class GameSessionIntegrationTest {
    @Autowired
    private GameSessionService gameSessionService;

    @Autowired
    private GameService gameService;

    @Test
    public void testGetById() {
        Game game = createGame();

        LocalDate now = LocalDate.now();
        GameSession gameSession = gameSessionService.createGameSession(game.getGameId(), now);

        assertNotNull(gameSession.getSessionId());
        assertEquals(gameSession, gameSessionService.getById(gameSession.getSessionId()));
    }

    @Test
    public void testGetByGameId() {
        Game game = createGame();

        LocalDate now = LocalDate.now();
        gameSessionService.createGameSession(game.getGameId(), now);

        List<GameSession> foundSessions = gameSessionService.getByGameId(game.getGameId());
        assertEquals(1, foundSessions.size());

        assertEquals(game.getGameName(),
                foundSessions.getFirst().getGame().getGameName());
        assertEquals(now, foundSessions.getFirst().getDate());
    }

    @Test
    public void testGetByDate() {
        Game game = createGame();

        LocalDate now = LocalDate.now();
        gameSessionService.createGameSession(game.getGameId(), now);

        List<GameSession> foundSessions = gameSessionService.getByDate(now);
        GameSession savedSession = null;
        for (GameSession session : foundSessions) {
            if (session.getGame().getGameName().equals(game.getGameName())) {
                savedSession = session;
            }
        }
        if (savedSession == null) {
            fail("Could not find a gamesession.");
        }

        assertEquals(game, savedSession.getGame());
    }

    @Test
    public void testGetAll() {
        var allSessionsPrev = gameSessionService.getAllGameSessions();

        Game game = createGame();
        LocalDate now = LocalDate.now();
        gameSessionService.createGameSession(game.getGameId(), now);

        var allSessionsNew = gameSessionService.getAllGameSessions();
        assertEquals(allSessionsPrev.size() + 1, allSessionsNew.size());
    }

    @Test
    public void testDeleteGameSession() {
        Game game = createGame();
        LocalDate now = LocalDate.now();
        var savedSession = gameSessionService.createGameSession(game.getGameId(), now);

        assertNotNull(savedSession.getSessionId());

        gameSessionService.deleteGameSession(savedSession.getSessionId());
        assertThrows(RuntimeException.class, () ->
                gameSessionService.getById(savedSession.getSessionId()));
    }

    @Test
    public void testUpdateGameSession() {
        Game gameOriginal = createGame();
        Game gameToReplaceWith = createGame2();
        LocalDate now = LocalDate.now();
        var savedSession = gameSessionService.createGameSession(gameOriginal.getGameId(), now);

        assertNotNull(savedSession.getSessionId());
        assertEquals(gameOriginal, savedSession.getGame());

        gameSessionService.updateGameSession(savedSession.getSessionId(), gameToReplaceWith.getGameId(), null);
        GameSession updatedSession = gameSessionService.getById(savedSession.getSessionId());

        assertEquals(gameToReplaceWith, updatedSession.getGame());
        assertEquals(now, updatedSession.getDate());
    }

    private Game createGame2() {
        Game game = new Game(
                "ChessForTesting",
                LocalDate.now(),
                2,
                "Traditional",
                10,
                90,
                "A traditional game",
                null);

        gameService.purchaseGame(game);
        return gameService.getGameByName("ChessForTesting");
    }

    private Game createGame() {
        Game game = new Game(
                "MahjongForTesting",
                LocalDate.now(),
                4,
                "Traditional",
                12,
                90,
                "A traditional Chinese game",
                null);

        gameService.purchaseGame(game);
        return gameService.getGameByName("MahjongForTesting");
    }

}