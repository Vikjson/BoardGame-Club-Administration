package se.yrgo.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.yrgo.data.SessionParticipantDao;
import se.yrgo.domain.SessionParticipant;
import se.yrgo.error.EntityNotFoundException;

import java.util.List;

@Transactional
@Service
public class SessionParticipantServiceProdImpl implements SessionParticipantService {
    private SessionParticipantDao sessionParticipantDao;

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
    public SessionParticipant getById(int participantId) {
        SessionParticipant sp = sessionParticipantDao.getById(participantId);
        if (sp == null) {
            throw new EntityNotFoundException("Participant not found");
        }
        return sp;
    }

    @Override
    public List<SessionParticipant> getBySessionId(int sessionId) {
        List<SessionParticipant> sessionParticipants = sessionParticipantDao.getAllSessionParticipants();

        if (sessionParticipants.isEmpty()) {
            throw new EntityNotFoundException("No participants are registered in database.");
        }

        List<SessionParticipant> participantsOfSessionId = sessionParticipants.stream().filter(p -> p.getSessionId() == sessionId)
                .toList();

        if (participantsOfSessionId.isEmpty()) {
            throw new EntityNotFoundException("Members of session with id " + sessionId + " has not yet been registered.");
        }
        return participantsOfSessionId;
    }

    @Override
    public List<SessionParticipant> getByMemberId(int id) {

        List<SessionParticipant> sessionParticipants = sessionParticipantDao.getAllSessionParticipants();

        if (sessionParticipants.isEmpty()) {
            throw new EntityNotFoundException("No participants are registered in database.");
        }

        List<SessionParticipant> participantsOfMemberId = sessionParticipants.stream().filter(p -> p.getMemberId() == id)
                .toList();

        if (participantsOfMemberId.isEmpty()) {
            throw new EntityNotFoundException("Member of id " + id + " has not yet played in any sessions.");
        }
        return participantsOfMemberId;
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
