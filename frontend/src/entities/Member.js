/**
 * Data model for a club member.
 */

export class Member {
    constructor(initObj) {
        this.memberId = initObj.memberId;
        this.name = initObj.firstName + ' ' + initObj.lastName;
        this.email = initObj.email;
        this.membershipFeePaid = initObj.membershipFeePaid;
        this.totalWins = initObj.totalWins;
        this.age = initObj.age;
    }
}