package se.yrgo.integrationtest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.domain.Member;
import se.yrgo.error.MemberNotFoundException;
import se.yrgo.service.MemberService;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration("/test-application.xml")
@Transactional
public class MemberIntegrationTest {
    @Autowired
    private MemberService memberService;

    @Test
    public void testGetByEmail() {
        Member newMember = new Member("Harry Potter", "potter@hogwarts.com", true, 100, 17);
        memberService.createMember(newMember);
        Member foundMember = memberService.getByEmail(newMember.getEmail());
        assertEquals(newMember, foundMember);
    }

    @Test
    public void testGetAll() {
        var allMembersPrev = memberService.getAllClubMembers();
        Member newMember = new Member("Harry Potter", "potter@hogwarts.com", true, 100, 17);
        memberService.createMember(newMember);

        var allMembersNew = memberService.getAllClubMembers();
        assertEquals(allMembersPrev.size() + 1, allMembersNew.size());
    }

    @Test
    public void testDeleteMember() {
        Member newMember = new Member("Harry Potter", "potter@hogwarts.com", true, 100, 17);
        memberService.createMember(newMember);

        var savedMember = memberService.getByEmail(newMember.getEmail());
        assertEquals(newMember, savedMember);

        memberService.deleteMember(savedMember.getMemberId());
        assertThrows(MemberNotFoundException.class, () ->
                memberService.getByEmail(newMember.getEmail()));
    }

    @Test
    public void testUpdateMember() {
        Member newMember = new Member("Harry Potter", "potter@hogwarts.com", true, 100, 17);
        memberService.createMember(newMember);
        var savedMember = memberService.getByEmail(newMember.getEmail());
        assertEquals(newMember, savedMember);

        String newName = "Harry James Potter";
        Member memberUpdateData = new Member(newName, null, null, null, savedMember.getAge());

        memberService.updateMember(savedMember.getMemberId(), memberUpdateData);
        savedMember = memberService.getByEmail(newMember.getEmail());

        assertEquals( newName, savedMember.getName());
    }
}
