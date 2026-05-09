package se.yrgo.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import se.yrgo.domain.GameSession;

import java.time.LocalDate;
import java.util.List;

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
    public void create(GameSession session) {
        em.persist(session);
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
