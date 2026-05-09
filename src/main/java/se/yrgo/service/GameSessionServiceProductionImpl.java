package se.yrgo.service;

import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import se.yrgo.data.GameDao;
import se.yrgo.data.GameSessionDao;
import se.yrgo.domain.Game;
import se.yrgo.domain.GameSession;

import java.time.LocalDate;
import java.util.List;

@Transactional
@Service
public class GameSessionServiceProductionImpl implements GameSessionService {

    private GameSessionDao gameSessionDao;
    private GameDao gameDao;

    @Autowired
    public GameSessionServiceProductionImpl(GameSessionDao gameSessionDao, GameDao gameDao) {
        this.gameSessionDao = gameSessionDao;
        this.gameDao = gameDao;
    }

    @Override
    public List<GameSession> getAllGameSessions() {
        return gameSessionDao.getAllGameSessions();
    }

    @Override
    public GameSession getById(int id) {
        try {
            return gameSessionDao.getById(id);
        } catch (NoResultException e) {
            throw new RuntimeException("The game session with id " + id + " could not be found.");
        }
    }

    @Override
    public List<GameSession> getByGameId(int gameId) {
        return gameSessionDao.getByGameId(gameId);
    }

    @Override
    public List<GameSession> getByDate(LocalDate date) {
        return gameSessionDao.getByDate(date);
    }


    @Override
    public void createGameSession(int gameId, LocalDate date) {
        Game game = gameDao.findGameById(gameId);
        GameSession session = new GameSession();

        session.setGame(game);
        session.setDate(date);

        gameSessionDao.create(session);
    }

    @Override
    public void updateGameSession(int id, Integer gameId, LocalDate date) {
        GameSession session = gameSessionDao.getById(id);

        if (date != null) {
            session.setDate(date);
        }

        if (gameId != null) {
            Game game = gameDao.findGameById(gameId);
            session.setGame(game);
        }

        gameSessionDao.update(session);
    }

    @Override
    public void deleteGameSession(int id) {
        try {
            gameSessionDao.delete(id);
        } catch (NoResultException e) {
            throw new RuntimeException("The game session with id " + id + " could not be deleted because it does not exist.");
        }
    }
}