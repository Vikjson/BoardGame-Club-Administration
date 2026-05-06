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
    private int id;

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
     * @param id
     * @param name
     * @param email
     * @param membershipFeePaid
     * @param totalWins
     * @param age
     */
    public Member(int id, String name, String email, boolean membershipFeePaid, int totalWins, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.membershipFeePaid = membershipFeePaid;
        this.totalWins = totalWins;
        this.age = age;
    }

    public int getId() {
        return id;
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

    /**
     * For debugging. TODO: delete later!
     *
     * @return
     */
    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", membershipFeePaid=" + membershipFeePaid +
                ", totalWins=" + totalWins +
                ", age=" + age +
                '}';
    }
}
