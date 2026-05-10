import ApiService from './ApiService.js'
import SessionParticipantService from './SessionParticipantService.js'

class GameSessionService {
    constructor() {
        this.apiService = new ApiService()
        this.sessionParticipantService = new SessionParticipantService()
    }

    async getAll() {
        const sessions = await this.apiService.getData('/gamesessions')

        for (const session of sessions) {
            const sessionId = session.gameSessionId ?? session.id

            session.participants =
                await this.sessionParticipantService.getBySessionId(sessionId)
        }

        return sessions
    }

    async create(gameId, date) {
        return await this.apiService.postData('/gamesessions', {
            gameId,
            date
        })
    }

    async delete(sessionId) {
        return await this.apiService.deleteData(`/gamesessions/${sessionId}`)
    }
}

export default GameSessionService