package se.yrgo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.yrgo.domain.Member;
import se.yrgo.domain.SessionParticipant;
import se.yrgo.service.MemberService;
import se.yrgo.service.SessionParticipantService;

import java.util.List;

@RestController
@RequestMapping("/sessionpatricipants")
public class SessionParticipantController {
    private SessionParticipantService sessionParticipantService;

    @Autowired
    public SessionParticipantController(SessionParticipantService sessionParticipantService) {
        this.sessionParticipantService = sessionParticipantService;
    }

    @GetMapping
    public List<SessionParticipant> getAll() {
        return sessionParticipantService.getAllSessionParticipants();
    }

    @GetMapping("id/{id}")
    public SessionParticipant getSessionParticipantById(@PathVariable int id) {
        if (id < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid 'id' parameter.");
        }
        return sessionParticipantService.getById(id);
    }


    @GetMapping("member/{memberId}")
    public List<SessionParticipant> getByMemberId(@PathVariable int memberId) {
        if (memberId < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid 'memberId' parameter.");
        }
        return sessionParticipantService.getByMemberId(memberID);
    }

    @GetMapping("session/{sessionId}")
    public List<SessionParticipant> getBySessionId(@PathVariable int sessionId) {
        if (sessionId < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid 'sessionId' parameter.");
        }
        return sessionParticipantService.getBySessionId();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createSessionParticipant(@RequestBody SessionParticipant newSessionParticipant) {
        if (newSessionParticipant.getId()!= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client is trying to set a value on readonly param 'id'");
        }

        sessionParticipantService.createSessionParticipant(newSessionParticipant);
    }

    @DeleteMapping("/{id}")
    public void deleteSessionParticipant(@PathVariable int id) {
        if (id < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid 'id' parameter.");
        }
        sessionParticipantService.deleteSessionParticipant(id);
    }


}
