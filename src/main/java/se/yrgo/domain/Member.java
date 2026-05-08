package se.yrgo.domain;

import jakarta.persistence.*;
import org.jspecify.annotations.NonNull;

/**
 * Data model for a member of the board game club.
 */
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberId;

    @Column(nullable = false)
    private String name;
    private String email;
    private boolean membershipFeePaid;
    private int totalWins;
    @Column(nullable = false)
    private int age;

    public Member() {
    }

    /**
     * Constructs a Member object, without setting an ID. Use for HTTP POST requests, wherein client omits the ID param.
     *
     * @param name              Members full name. Requred, cannot be null or empty string.
     * @param email
     * @param membershipFeePaid
     * @param totalWins
     * @param age               Members age. Required, cannot be less than 1.
     * @throws IllegalArgumentException if any required parameters are missing or invalid.
     */
    public Member(@NonNull String name, String email, boolean membershipFeePaid, int totalWins, int age) {
        if (age < 1) {
            throw new IllegalArgumentException("Invalid 'age' parameter.");
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException("The required 'name' parameter is empty.");
        }

        this.name = name;
        this.email = email;
        this.membershipFeePaid = membershipFeePaid;
        this.totalWins = totalWins;
        this.age = age;
    }


    /**
     * Constructs a Member object, complete with all properties. Use when sending data to a client who needs the ID value
     * of the Member.
     *
     * @param memberId
     * @param name
     * @param email
     * @param membershipFeePaid
     * @param totalWins
     * @param age
     */
    public Member(int memberId, String name, String email, boolean membershipFeePaid, int totalWins, int age) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.membershipFeePaid = membershipFeePaid;
        this.totalWins = totalWins;
        this.age = age;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isMembershipFeePaid() {
        return membershipFeePaid;
    }

    public int getTotalWins() {
        return totalWins;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMembershipFeePaid(boolean membershipFeePaid) {
        this.membershipFeePaid = membershipFeePaid;
    }

    public void setTotalWins(int totalWins) {
        this.totalWins = totalWins;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * For debugging. TODO: delete later!
     *
     * @return
     */
    @Override
    public String toString() {
        return "Member{" +
                "id=" + memberId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", membershipFeePaid=" + membershipFeePaid +
                ", totalWins=" + totalWins +
                ", age=" + age +
                '}';
    }
}
