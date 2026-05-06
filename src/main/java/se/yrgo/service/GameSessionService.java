package se.yrgo.service;

import se.yrgo.domain.GameSession;

import java.time.LocalDate;
import java.util.List;

public interface GameSessionService {

    List<GameSession> getAllGameSessions();

    GameSession getById(int id);

    List<GameSession> getByGameId(int gameId);

    List<GameSession> findByDate(LocalDate date);

    void create(GameSession session);

    void update(GameSession session);

    void delete(int id);
}