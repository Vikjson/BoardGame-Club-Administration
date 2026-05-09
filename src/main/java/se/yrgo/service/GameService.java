package se.yrgo.service;

import se.yrgo.domain.Game;

import java.time.LocalDate;
import java.util.List;

public interface GameService {
    List<Game> getAllGames();

    Game getGameById(int id);

    Game getGameByName(String name);

    void deleteGame(int id);

    void purchaseGame(Game newGame);

    void updateGame(int gameId, String gameName, LocalDate purchaseDate, int totalPlayers, String category, int recommendedAge, int averagePlayTime, String description, String memberComment);

}
