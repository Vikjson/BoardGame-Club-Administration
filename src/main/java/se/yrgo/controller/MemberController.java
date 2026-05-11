package se.yrgo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.yrgo.domain.Member;
import se.yrgo.error.MemberNotFoundException;
import se.yrgo.service.MemberService;

import java.util.List;

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
        if (id < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid 'id' parameter.");
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
        if (!email.contains("@")) {
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
        System.out.println("---------- Member to add: " + newMember);
        if (newMember.getMemberId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client is trying to set a value on readonly param 'id'");
        }

        memberService.createMember(newMember);
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Integer id) {
        if (id < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid 'id' parameter.");
        }

        memberService.deleteMember(id);
    }

    @PutMapping("/{id}")
    public void updateMember(
            @PathVariable Integer id,
            @RequestBody Member member
    ) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid 'id' parameter.");
        }

        memberService.updateMember(id, member);
    }
}
