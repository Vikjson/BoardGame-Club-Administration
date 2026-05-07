package se.yrgo.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.yrgo.data.GameDao;
import se.yrgo.domain.Game;

import java.util.List;

@Service("gameService")
@Transactional
public class GameServiceProductionImpl implements GameService{

    @Autowired
    private GameDao gameDao;

    @Autowired
    public GameServiceProductionImpl(GameDao gameDao) {
        this.gameDao = gameDao;
    }
    @Override
    public List<Game> getAllGames() {
        return gameDao.listAllGames();
    }

    @Override
    public Game getGameById(int id) {
        return gameDao.findGameById(id);
    }

    @Override
    public Game getGameByName(String name) {
        return gameDao.findGameByName(name);
    }

    @Override
    public Game deleteGameById(int id) {
        return gameDao.findGameById(id);
    }

    @Transactional
    @Override
    public void purchaseGame(Game newGame) {
        gameDao.createGame(newGame);
    }
}
