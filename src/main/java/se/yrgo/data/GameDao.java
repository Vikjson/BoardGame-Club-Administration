package se.yrgo.data;

import se.yrgo.domain.Game;

import java.util.List;

public interface GameDao {
    public List<Game> listAllGames();
    public Game findGameById(int id);
    public Game findGameByName(String name);
    public void createGame(Game newGame);
    public void deleteGame(int id);
    public void updateGame(Game newGame);

}
