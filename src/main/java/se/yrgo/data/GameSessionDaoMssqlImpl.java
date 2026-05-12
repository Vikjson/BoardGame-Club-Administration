package se.yrgo.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import se.yrgo.domain.Game;
import se.yrgo.domain.GameSession;

import java.time.LocalDate;
import java.util.List;

/**
 * Data Access Object (DAO) implementation for {@link GameSession}.
 * <p>
 * Provides database operations for managing GameSession entities using JPA EntityManager.
 * This class handles basic CRUD operations such as create, read, update, and delete.
 */
@Repository
public class GameSessionDaoMssqlImpl implements GameSessionDao{
    
@PersistenceContext
    private EntityManager em;

    @Override
    public List<GameSession> getAllGameSessions() {
        return em.createQuery("SELECT gameSession FROM GameSession AS gameSession", GameSession.class)
                .getResultList();
    }

    @Override
    public GameSession getById(int id) {
        return em.createQuery( "SELECT gameSession FROM GameSession AS gameSession WHERE gameSession.sessionId = :id", GameSession.class)
        .setParameter("id", id)
        .getSingleResult();
    }

    @Override
    public List<GameSession> getByGameId(int gameId) {
        return em.createQuery("SELECT gameSession FROM GameSession AS gameSession WHERE gameSession.game.gameId = :gameId", GameSession.class)
        .setParameter("gameId", gameId)
        .getResultList();
    }

    @Override
    public List<GameSession> getByDate(LocalDate date) {
        return em.createQuery("SELECT gameSession FROM GameSession AS gameSession WHERE gameSession.date = :date", GameSession.class )
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public GameSession createGameSession(int gameId, LocalDate date) {
        Game game = em.find(Game.class, gameId);

        GameSession session = new GameSession(game, date);

        em.persist(session);

        return session;
    }

    @Override
    public void update(GameSession session) {
        em.merge(session);
    }

    @Override
    public void delete(int id) {
        GameSession session = getById(id);
        em.remove(session);
    }
}
