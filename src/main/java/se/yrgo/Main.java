package se.yrgo;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import se.yrgo.domain.Member;
import se.yrgo.service.MemberService;

import java.util.List;

/**
 * For temporarily testing out database logics. Will be replaced by the servlet later.
 */
public class Main {
    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml")) {
            MemberService memberService = container.getBean(MemberService.class);

            List<Member> members = memberService.getAllClubMembers();
            System.out.println(members.getFirst().getName());
        }

    }
}
