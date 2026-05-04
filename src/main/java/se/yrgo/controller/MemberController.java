package se.yrgo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import se.yrgo.domain.Member;
import se.yrgo.error.MemberNotFoundException;
import se.yrgo.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {
    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createMember(@RequestBody Member member){
        try {
            memberService.createMember(member);
        } catch (Exception e) {
            //Todo: error handling
        }
    }

    @GetMapping
    public List<Member> getAll() {
        return memberService.getAllClubMembers();
    }

    @GetMapping("/{id}")
    public Member getById(@PathVariable int id) {
        try {
            Member member = memberService.getById(id);
            return member;
        }
        catch (MemberNotFoundException e) {
            System.err.println(e.getMessage());
            throw new MemberNotFoundException(e);
        }
        catch (Exception e) {
            //Todo: proper error handling
            throw new RuntimeException("There was an internal server error.");
        }
    }
}
