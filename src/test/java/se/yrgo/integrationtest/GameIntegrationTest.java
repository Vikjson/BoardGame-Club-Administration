package se.yrgo.integrationtest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import se.yrgo.domain.Game;
import se.yrgo.error.GameNotFoundException;
import se.yrgo.service.GameService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/test-application.xml")
@Transactional
public class GameIntegrationTest {
    @Autowired
    private GameService gameService;

    @Test
    public void testGetGameByName() {
        Game newGame = new Game("Wyrmspan", LocalDate.now(), 4, "Strategy", 10,
                90, "Trading and building game","Very fun");
        gameService.purchaseGame(newGame);
        Game foundGame = gameService.getGameByName("Catan");
        assertEquals("Catan", foundGame.getGameName());
    }

    @Test
    public void testGetAllGames() {
        var gamesBefore = gameService.getAllGames();
        Game newGame = new Game( "Chess", LocalDate.now(), 2, "Classic", 6,
                60, "Board strategy game", "Popular game");
        gameService.purchaseGame(newGame);

        var gamesAfter = gameService.getAllGames();
        assertEquals(gamesBefore.size() + 1, gamesAfter.size());
    }

    @Test
    public void testDeleteGame() {
        Game newGame = new Game( "Monopoly", LocalDate.now(), 6, "Family", 8,
                120, "Property trading game", "Can be long");

        gameService.purchaseGame(newGame);
        Game savedGame = gameService.getGameByName("Monopoly");
        assertNotNull(savedGame);

        gameService.deleteGame(savedGame.getGameId());
        assertThrows(GameNotFoundException.class, () ->
                gameService.getGameByName("Monopoly"));
    }

    @Test
    public void testUpdateGame() {
        Game newGame = new Game( "Uno", LocalDate.now(), 10, "Card Game", 7,
                30, "Fast card game", "Easy to learn");

        gameService.purchaseGame(newGame);
        Game savedGame = gameService.getGameByName("Uno");
        String updatedName = "UNO Deluxe";

        gameService.updateGame(savedGame.getGameId(), updatedName, null, 0, null,
                0,0, null, null);

        Game updatedGame = gameService.getGameByName(updatedName);
        assertEquals(updatedName, updatedGame.getGameName());
    }
}
