package se.yrgo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.yrgo.domain.Member;
import se.yrgo.service.MemberService;

@RestController
@RequestMapping("/members")
public class MemberController {
    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

//    @GetMapping
//    public String getAll() {
//        try {
//
//        }
//    }

    @GetMapping("/{id}")
    public String getById(@PathVariable int id) {
        Member member = memberService.getById(id);
        return member.getName();
    }
}
