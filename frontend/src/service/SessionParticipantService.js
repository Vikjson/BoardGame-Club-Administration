import ApiService from './ApiService.js'
import { SessionParticipant } from '../entities/SessionParticipant.js'

class SessionParticipantService {
    constructor() {
        this.apiService = new ApiService()
    }

    async getAll() {
        const json = await this.apiService.getData('/sessionparticipants')
        return json.map(p => new SessionParticipant(p))
    }

    async getBySessionId(sessionId) {
        const json = await this.apiService.getData(`/sessionparticipants/session/${sessionId}`)
        return json.map(p => new SessionParticipant(p))
    }

    async create(participant) {
        return await this.apiService.postData('/sessionparticipants', participant)
    }

    async delete(id) {
        return await this.apiService.deleteData(`/sessionparticipants/${id}`)
    }

    async update(id,sessionParticipant){
        return await this.apiService.putData(`/sessionparticipants/${id}`, sessionParticipant)
    }
}

export default SessionParticipantService