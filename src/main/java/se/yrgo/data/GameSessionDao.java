package se.yrgo.data;

import se.yrgo.domain.GameSession;
import java.time.LocalDate;
import java.util.List;


public interface GameSessionDao {
    List<GameSession> getAllGameSessions();

    GameSession getById(int id);

    List<GameSession> getByGameId(int gameId);

    List<GameSession> getByDate(LocalDate date);

    void create(GameSession session);

    void update(GameSession session);

    void delete(int id);
}
