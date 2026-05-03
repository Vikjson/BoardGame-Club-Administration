package se.yrgo.service;

import se.yrgo.domain.Game;

import java.util.List;

public interface GameService {
    public List<Game> getAllGames();
    public Game getGameById(int id);
    public Game getGameByName(String name);
    public Game deleteGameById(int id);
    public void purchaseGame(Game newGame);

}
