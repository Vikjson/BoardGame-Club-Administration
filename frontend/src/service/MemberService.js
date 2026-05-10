import {Member} from "../entities/Member.js";
import ApiService from "./ApiService.js";

class MemberService {
    constructor() {
        this.apiService = new ApiService();
    }

    async getAll(){
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

    async registerNewMember(member) {
        try {
            await this.apiService.postData('/members', member);
        } catch (e) {
            console.error('Failed to register member ' + member.name + '.');
            throw e;
        }
    }
}

export default MemberService;