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
    @GetMapping
    public List<Member> getAll() {
        return memberService.getAllClubMembers();
    }

    @GetMapping("id/{id}")
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

    @GetMapping("email/{email}")
    public Member getByEmail(@PathVariable String email) {
        try {
            Member member = memberService.getByEmail(email);
            return member;
        }
        catch (MemberNotFoundException e) {
            System.err.println(e.getMessage());
            throw new MemberNotFoundException(e);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createMember(@RequestBody Member newMember){
        try {
            memberService.createMember(newMember);
        } catch (Exception e) {
            e.printStackTrace();
            //Todo: error handling
        }
    }

    @DeleteMapping("/{email}")
    public void deleteMemberByEmail(@PathVariable String email){
        memberService.deleteMemberByEmail(email);
    }


}
