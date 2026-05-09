package se.yrgo.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.yrgo.data.GameDao;
import se.yrgo.domain.Game;
import se.yrgo.error.GameNotFoundException;

import java.time.LocalDate;
import java.util.List;

@Service("gameService")
@Transactional
public class GameServiceProductionImpl implements GameService {
    private final GameDao gameDao;

    @Autowired
    public GameServiceProductionImpl(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    @Override
    public List<Game> getAllGames() {
        return gameDao.getAllGames();
    }

    @Override
    public Game getGameById(int gameId) {
        try {
            return gameDao.getGameById(gameId);
        } catch (NoResultException e) {
            throw new GameNotFoundException("Game with id " + gameId + " not found");
        }
    }

    @Override
    public Game getGameByName(String name) {
        try {
            return gameDao.getGameByName(name);
        } catch (NoResultException e) {
            throw new GameNotFoundException("Game with name " + name + " not found");
        }
    }

    @Override
    public void deleteGame(int gameId) {
        Game gameToDelete = getGameById(gameId);
        gameDao.deleteGame(gameToDelete);
    }

    @Override
    public void purchaseGame(Game newGame) {
        try {
            gameDao.createGame(newGame);
        } catch (EntityExistsException e) {
            throw new RuntimeException("Game" + newGame.getGameName() + " already exists");
        }
    }

    @Override
    public void updateGame(int gameId, String gameName, LocalDate purchaseDate, int totalPlayers, String category, int recommendedAge, int averagePlayTime, String description, String memberComment) {
        Game gameToUpdate = getGameById(gameId);

        if (gameName != null) gameToUpdate.setGameName(gameName);
        if (purchaseDate != null) gameToUpdate.setPurchaseDate(purchaseDate);
        if (totalPlayers > 0) gameToUpdate.setTotalPlayers(totalPlayers);
        if (category != null && !category.isBlank()) gameToUpdate.setCategory(category);
        if (recommendedAge > 0) gameToUpdate.setRecommendedAge(recommendedAge);
        if (averagePlayTime > 0) gameToUpdate.setAveragePlayTime(averagePlayTime);
        if (description != null) gameToUpdate.setDescription(description);
        if (memberComment != null) gameToUpdate.setMemberComment(memberComment);
        gameDao.updateGame(gameToUpdate);
    }
}
