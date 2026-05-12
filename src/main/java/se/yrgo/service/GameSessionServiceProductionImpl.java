package se.yrgo.service;

import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import se.yrgo.data.GameDao;
import se.yrgo.data.GameSessionDao;
import se.yrgo.domain.Game;
import se.yrgo.domain.GameSession;
import se.yrgo.domain.SessionParticipant;
import se.yrgo.error.GameSessionDeleteException;
import se.yrgo.error.GameSessionNotFoundException;

import java.time.LocalDate;
import java.util.List;

/**
 * Production implementation of {@link GameSessionService}.
 * <p>
 * Provides business logic for managing Game entities including retrieval,
 * creation, updating, and deletion operations.
 * Acts as an intermediary layer between controllers and the DAO layer.
 * <p>
 * This service is also responsible for:
 * <ul>
 *     <li>Mapping persistence-layer exceptions into application-specific exceptions</li>
 *     <li>Validating business rules before delegating to DAO</li>
 *     <li>Managing transactional operations</li>
 * </ul>
 */
@Transactional
@Service
public class GameSessionServiceProductionImpl implements GameSessionService {

    private final GameSessionDao gameSessionDao;
    private final GameDao gameDao;
    private final SessionParticipantService sessionParticipantService;

    @Autowired
    public GameSessionServiceProductionImpl(
            GameSessionDao gameSessionDao,
            GameDao gameDao,
            SessionParticipantService sessionParticipantService) {

        this.gameSessionDao = gameSessionDao;
        this.gameDao = gameDao;
        this.sessionParticipantService = sessionParticipantService;
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
            throw new GameSessionNotFoundException("The game session with id " + id + " could not be found.");
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
    public GameSession createGameSession(int gameId, LocalDate date) {
        return gameSessionDao.createGameSession(gameId, date);
    }

    @Override
    public void updateGameSession(int id, Integer gameId, LocalDate date) {
        GameSession session = gameSessionDao.getById(id);

        if (date != null) {
            session.setDate(date);
        }

        if (gameId != null) {
            Game game = gameDao.getGameById(gameId);
            session.setGame(game);
        }

        gameSessionDao.update(session);
    }

    @Override
    public void deleteGameSession(int id) {
        try {
            gameSessionDao.getById(id);

            List<SessionParticipant> participants =
                    sessionParticipantService.getBySessionId(id);

            if (!participants.isEmpty()) {
                throw new GameSessionDeleteException("Cannot delete game session because it has participants connected to it.");
            }

            gameSessionDao.delete(id);

        } catch (NoResultException e) {
            throw new GameSessionNotFoundException("The game session with id " + id + " could not be deleted because it does not exist.");
        }
    }
}