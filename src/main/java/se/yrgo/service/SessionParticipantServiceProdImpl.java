package se.yrgo.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.yrgo.data.GameSessionDao;
import se.yrgo.data.SessionParticipantDao;
import se.yrgo.data.MemberDao;
import se.yrgo.domain.GameSession;
import se.yrgo.domain.Member;
import se.yrgo.domain.SessionParticipant;
import se.yrgo.error.EntityNotFoundException;

import java.util.List;

@Transactional
@Service
public class SessionParticipantServiceProdImpl implements SessionParticipantService {
    private SessionParticipantDao sessionParticipantDao;
    private MemberDao memberDao;
    private GameSessionDao gameSessionDao;

    @Autowired
    public SessionParticipantServiceProdImpl(SessionParticipantDao sessionParticipantDao) {
        this.sessionParticipantDao = sessionParticipantDao;
    }

    @Override
    public void createSessionParticipant(@NonNull SessionParticipant sessionParticipant) {
        try {
            sessionParticipantDao.createSessionParticipant(sessionParticipant);
        } catch (EntityExistsException e) {
            throw new RuntimeException("Session participant already exists");
        }
    }


    @Override
    public SessionParticipant updateSessionParticipant(SessionParticipant participant) {
        System.out.println("UPDATE participant id = " + participant.getId());
        SessionParticipant existingParticipant =
                sessionParticipantDao.getById(participant.getId());



        Member member = memberDao.getById(participant.getMember().getMemberId());
        GameSession gameSession =
                gameSessionDao.getById(participant.getGameSession().getSessionId());

        existingParticipant.setMember(member);
        existingParticipant.setGameSession(gameSession);
        existingParticipant.setScore(participant.getScore());
        existingParticipant.setWinner(participant.isWinner());

        return sessionParticipantDao.updateSessionParticipant(existingParticipant);
    }

    @Override
    public SessionParticipant getById(int participantId) {
        SessionParticipant sp = sessionParticipantDao.getById(participantId);
        if (sp == null) {
            throw new EntityNotFoundException("Participant not found");
        }
        return sp;
    }

    @Override
    public List<SessionParticipant> getBySessionId(int sessionId) {
        List<SessionParticipant> sessionParticipants = sessionParticipantDao.getBySessionId(sessionId);

        if (sessionParticipants.isEmpty()) {
            throw new EntityNotFoundException("No participants are registered in database.");
        }
        return sessionParticipants;
    }

    @Override
    public List<SessionParticipant> getByMemberId(int memberId) {

        List<SessionParticipant> sessionParticipants = sessionParticipantDao.getByMemberId(memberId);

        if (sessionParticipants.isEmpty()) {
            throw new EntityNotFoundException("No participants are registered in database.");
        }
        return sessionParticipants;
    }


    @Override
    public void deleteSessionParticipant(int id) {
        SessionParticipant sessionParticipantToDelete = sessionParticipantDao.getById(id);
        if (sessionParticipantToDelete == null) {
            throw new EntityNotFoundException("Session participant not found");
        }
        sessionParticipantDao.deleteSessionParticipant(sessionParticipantToDelete);
    }

    @Override
    public List<SessionParticipant> getAllSessionParticipants() {
        List<SessionParticipant> participants = sessionParticipantDao.getAllSessionParticipants();

        if (participants.isEmpty()) {
            throw new EntityNotFoundException("Session participants not found");
        }
        return participants;
    }
}
