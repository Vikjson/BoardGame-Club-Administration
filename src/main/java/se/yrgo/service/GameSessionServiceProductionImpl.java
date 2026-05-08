package se.yrgo.service;

import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.yrgo.data.GameSessionDao;
import se.yrgo.domain.Game;
import se.yrgo.domain.GameSession;

import java.time.LocalDate;
import java.util.List;

@Service
public class GameSessionServiceProductionImpl implements GameSessionService {

    private GameSessionDao gameSessionDao;

    @Autowired
    public GameSessionServiceProductionImpl(GameSessionDao gameSessionDao) {
        this.gameSessionDao = gameSessionDao;
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
    public void createGameSession(GameSession session) {
        gameSessionDao.create(session);
    }

    @Override
    public void updateGameSession(int id, Integer gameId, LocalDate date) {
        GameSession session = gameSessionDao.getById(id);

        if (date != null) {
            session.setDate(date);
        }

        if (gameId != null) {
            Game game = gameDao.getById(gameId);
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