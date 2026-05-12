package se.yrgo.integrationtest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import se.yrgo.domain.Game;
import se.yrgo.domain.GameSession;
import se.yrgo.domain.Member;
import se.yrgo.domain.SessionParticipant;
import se.yrgo.error.EntityNotFoundException;
import se.yrgo.service.GameService;
import se.yrgo.service.GameSessionService;
import se.yrgo.service.MemberService;
import se.yrgo.service.SessionParticipantService;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/test-application.xml")
@Transactional
public class SessionParticipantIntegrationTest {

    @Autowired
    private SessionParticipantService participantService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private GameSessionService gameSessionService;

    @Autowired
    private GameService gameService;

    @Test
    public void testCreateAndGetById() {

        Member member = new Member("Harry Potter", "harry@test.com", true, 100, 17);
        memberService.createMember(member);

        Game game = new Game("Catann", LocalDate.now(), 4, "Strategy", 10,
                60, "desc", "comment");

        gameService.purchaseGame(game);

        GameSession session =
                gameSessionService.createGameSession(game.getGameId(), LocalDate.now());

        SessionParticipant participant =
                new SessionParticipant(member, session, 100, true);

        participantService.createSessionParticipant(participant);

        SessionParticipant found =
                participantService.getById(participant.getId());

        assertEquals(100, found.getScore());
        assertTrue(found.isWinner());
    }

    @Test
    public void testGetAll() {

        int sizeBefore = participantService.getAllSessionParticipants().size();

        Member member = new Member("Hermione", "hermione@test.com", true, 100, 18);
        memberService.createMember(member);

        Game game = new Game("Catan new", LocalDate.now(), 4, "Strategy", 10, 60, "desc", "comment");
        gameService.purchaseGame(game);

        GameSession session = gameSessionService.createGameSession(
                game.getGameId(),
                LocalDate.now()
        );

        SessionParticipant participant =
                new SessionParticipant(member, session, 200, false);

        participantService.createSessionParticipant(participant);
        int sizeAfter = participantService.getAllSessionParticipants().size();
        assertEquals(sizeBefore + 1, sizeAfter);
    }

    @Test
    public void testGetByMemberId() {

        Member member = new Member("Ron", "ron@test.com", true, 100, 18);
        memberService.createMember(member);

        Game game = new Game("Monopoly", LocalDate.now(), 6, "Family",
                8, 120, "Property trading game", "Can be long");
        gameService.purchaseGame(game);

        GameSession session = gameSessionService.createGameSession(
                game.getGameId(),
                LocalDate.now()
        );
        SessionParticipant participant =
                new SessionParticipant(member, session, 50, false);

        participantService.createSessionParticipant(participant);
        var result = participantService.getByMemberId(member.getMemberId());
        assertFalse(result.isEmpty());
    }

    @Test
    public void testDeleteParticipant() {

        Member member = new Member("Draco", "draco@test.com", true, 100, 18);
        memberService.createMember(member);

        Game game = new Game("Monopolyn", LocalDate.now(), 6, "Family",
                8, 120, "Property trading game", "Can be long");
        gameService.purchaseGame(game);

        GameSession session = gameSessionService.createGameSession(
                game.getGameId(),
                LocalDate.now()
        );

        SessionParticipant participant =
                new SessionParticipant(member, session, 300, true);

        participantService.createSessionParticipant(participant);

        int id = participant.getId();
        participantService.deleteSessionParticipant(id);
        assertThrows(EntityNotFoundException.class, () ->
                participantService.getById(id));
    }

    @Test
    public void testUpdateParticipant() {

        Member member = new Member("Luna", "luna@test.com", true, 100, 17);
        memberService.createMember(member);

        Game game = new Game("Dune", LocalDate.now(), 4, "Strategy",
                12, 90, "Sci-fi strategy game", "Very fun");
        gameService.purchaseGame(game);

        GameSession session = gameSessionService.createGameSession(
                game.getGameId(),
                LocalDate.now()
        );

        SessionParticipant participant =
                new SessionParticipant(member, session, 10, false);

        participantService.createSessionParticipant(participant);

        SessionParticipant saved =
                participantService.getById(participant.getId());

        saved.setScore(999);
        saved.setWinner(true);

        SessionParticipant updated =
                participantService.updateSessionParticipant(saved);

        assertEquals(999, updated.getScore());
        assertTrue(updated.isWinner());
    }
}