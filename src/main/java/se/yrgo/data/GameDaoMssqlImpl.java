package se.yrgo.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import se.yrgo.domain.Game;

import java.util.List;

@Repository
public class GameDaoMssqlImpl implements GameDao {
    @PersistenceContext
    private EntityManager em;


    @Override
    public List<Game> getAllGames() {
        return em.createQuery("SELECT game FROM Game AS game", Game.class)
                .getResultList();
    }

    @Override
    public Game getGameById(int gameId) {
        return em.createQuery("SELECT game FROM Game AS game WHERE game.gameId = :gameId", Game.class)
                .setParameter("gameId", gameId)
                .getSingleResult();
    }

    @Override
    public Game getGameByName(String gameName) {
        return em.createQuery("SELECT game FROM Game AS game WHERE game.gameName = :gameName", Game.class)
                .setParameter("gameName", gameName)
                .getSingleResult();
    }

    @Override
    public void createGame(Game newGame) {
        em.persist(newGame);
    }

    @Override
    public void deleteGame(Game gameToDelete) {
        em.remove(gameToDelete);
    }

    @Override
    public void updateGame(Game gameToUpdate) {
        em.merge(gameToUpdate);
    }
}
