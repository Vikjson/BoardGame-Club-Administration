package se.yrgo.service;

import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.yrgo.data.GameSessionDao;
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
        }
        catch (NoResultException e) {
            throw new RuntimeException("The game session with id " + id + " could not be found.");
        }
    }

    @Override
    public List<GameSession> getByGameId(int gameId) {
        return gameSessionDao.getByGameId(gameId);
    }

    @Override
    public List<GameSession> findByDate(LocalDate date) {
        return gameSessionDao.findByDate(date);
    }

    @Override
    public void create(GameSession session) {
        gameSessionDao.create(session);
    }

    @Override
    public void update(GameSession session) {
        gameSessionDao.update(session);
    }

    @Override
    public void delete(int id) {
        try {
            gameSessionDao.delete(id);
        }
        catch (NoResultException e) {
            throw new RuntimeException("The game session with id " + id + " could not be deleted because it does not exist.");
        }
    }
}