package se.yrgo.domain;

import jakarta.persistence.*;

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

    public Member(String name, String email, boolean membershipFeePaid, int totalWins, int age) {
        this.name = name;
        this.email = email;
        this.membershipFeePaid = membershipFeePaid;
        this.totalWins = totalWins;
        this.age = age;
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
