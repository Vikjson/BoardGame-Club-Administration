package se.yrgo.service;

import se.yrgo.domain.Member;

import java.util.List;

public interface MemberService {
    List<Member> getAllClubMembers();

    Member getById(Integer id);

    void createMember(Member newMember);

    void deleteMember(Integer id);

    Member getByEmail(String email);

    void updateMember(Integer id, Member memberUpdate);
}
