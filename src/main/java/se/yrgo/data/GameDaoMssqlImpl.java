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
    public List<Game> listAllGames() {
        return em.createQuery("SELECT game FROM Game AS game", Game.class)
                .getResultList();
    }

    @Override
    public Game findGameById(int id) {
        return em.createQuery("SELECT game FROM Game AS game WHERE game.gameId = :gameId", Game.class)
                .getSingleResult();
    }

    @Override
    public Game findGameByName(String name) {
        return em.createQuery("SELECT game FROM Game AS game WHERE game.gameName = :gameName", Game.class)
            .setParameter("gameName", name)    
            .getSingleResult();
    }

    @Override
    public void createGame(Game newGame) {
        em.persist(newGame);
    }

    @Override
    public void deleteGame(int id) {

    }

    @Override
    public void updateGame(Game newGame) {

    }
}
