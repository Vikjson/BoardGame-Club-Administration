import {Member} from "../entities/Member.js";
import ApiService from "./ApiService.js";

class MemberService {
    constructor() {
        this.apiService = new ApiService();
    }

    async getAll() {
        try {
            const members = [];
            const json = await this.apiService.getData('/members');

            for (const member of json) {
                members.push(new Member(member));
            }
            return members;
        } catch (e) {
            console.error('Error fetching all members.' + e.message);
            throw e;
        }
    }

    async getByEmail(email) {
        try {
            return await this.apiService.getData(`/members/email/${email}`)
        } catch (e) {
            console.error('Error fetching member with email ' + email);
            throw e;
        }
    }

    async registerNewMember(member) {
        try {
            await this.apiService.postData('/members', member);
        } catch (e) {
            console.error('Failed to register member ' + member.name + '.');
            throw e;
        }
    }

    async updateMember(id, member) {
        try {
            await this.apiService.putData(`/members/${id}`, member);
        } catch (e) {
            console.error('Failed to update member ' + member.name + '.');
            throw e;
        }
    }

    async deleteMember(id) {
        try {
            await this.apiService.deleteData(`/members/${id}`);
        } catch (e) {
            if (e.cause === 409) {
                alert('Den här medlemmen är registrerad i spelomgångar som inte kan tas bort. ' +
                    'Var god att välj \'Arkivera\' istället. (Upcoming feature, finns ej i nuläget.)')
                return;
            }
            throw e;
        }
    }
}

export default MemberService;