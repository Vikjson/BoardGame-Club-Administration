package se.yrgo.data;

import se.yrgo.domain.Game;

import java.util.List;

public class GameDaoImpl implements GameDao {
    @Override
    public List<Game> listAllGames() {
        return List.of();
    }

    @Override
    public Game findGameById(int id) {
        return null;
    }

    @Override
    public void createGame(Game newGame) {

    }

    @Override
    public void deleteGame(int id) {

    }

    @Override
    public void updateGame(Game newGame) {

    }
}
