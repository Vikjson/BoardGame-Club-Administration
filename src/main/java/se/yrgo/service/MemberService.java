package se.yrgo.service;

import se.yrgo.domain.Member;

import java.util.List;

public interface MemberService {
    List<Member> getAllClubMembers();

    Member getById(int id);

    void createMember(Member newMember);

    void deleteMember(int id);

    void updateMember(Member memberToUpdate);

    Member getByEmail(String email);
}
