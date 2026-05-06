package se.yrgo.service;

import se.yrgo.domain.Member;

import java.util.List;

public interface MemberService {
    List<Member> getAllClubMembers();

    Member getById(int id);

    void createMember(Member newMember);

    void deleteMember(int id);

    Member getByEmail(String email);

    void updateMember(int id, String name, String email, Boolean membershipFeePaid, Integer totalWins, Integer age);
}
