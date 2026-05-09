package se.yrgo.data;

import se.yrgo.domain.Game;

import java.util.List;

public interface GameDao {
    List<Game> getAllGames();

    Game getGameById(int gameId);

    Game getGameByName(String gameName);

    void createGame(Game newGame);

    void deleteGame(Game gameToDelete);

    void updateGame(Game gameToUpdate);

}
