package se.yrgo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.yrgo.domain.Game;
import se.yrgo.domain.Member;
import se.yrgo.error.MemberNotFoundException;
import se.yrgo.service.GameService;
import se.yrgo.service.MemberService;

import java.util.List;

/**
 * REST controller for managing Member resources.
 * <p>
 * Provides HTTP endpoints for retrieving, creating, updating, and deleting
 * {@link Member} entities. Acts as the entry point between the client and
 * the {@link MemberService} layer.
 * <p>
 * Delegates all business logic to the service layer and is responsible for:
 * <ul>
 *     <li>Handling HTTP requests and responses</li>
 *     <li>Validating basic input parameters</li>
 *     <li>Mapping service exceptions to HTTP responses</li>
 * </ul>
 */
@RestController
@RequestMapping("/members")
@CrossOrigin(origins = "http://localhost:5173")
public class MemberController {
    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List<Member> getAll() {
        return memberService.getAllClubMembers();
    }

    @GetMapping("id/{id}")
    public Member getById(@PathVariable Integer id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing 'id' in URL path.");
        }

        try {
            Member member = memberService.getById(id);
            return member;
        } catch (MemberNotFoundException e) {
            System.err.println(e.getMessage());
            throw new MemberNotFoundException(e);
        }
    }

    @GetMapping("email/{email}")
    public Member getByEmail(@PathVariable String email) {
        if (email == null || !email.contains("@")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid email parameter.");
        }

        try {
            Member member = memberService.getByEmail(email);
            return member;
        } catch (MemberNotFoundException e) {
            System.err.println(e.getMessage());
            throw new MemberNotFoundException(e);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createMember(@RequestBody Member newMember) {
        if (newMember == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Unable to read request body correctly. Only JSON format is supported. ");
        }
        if (newMember.getMemberId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client is trying to set a value on readonly param 'id'");
        }

        memberService.createMember(newMember);
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Integer id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing 'id' in URL path.");
        }

        try {
            memberService.deleteMember(id);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public void updateMember(
            @PathVariable Integer id,
            @RequestBody Member member
    ) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing 'id' in URL path.");
        }
        if (member == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Unable to read request body correctly. Only JSON format is supported. ");
        }

        memberService.updateMember(id, member);
    }
}
