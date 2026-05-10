import ApiService from './ApiService.js'

class GameSessionService {
    constructor() {
        this.apiService = new ApiService()
    }

    async getAll() {
        const participants = await this.apiService.getData('/sessionparticipants')

        const sessionsMap = new Map()

        for (const participant of participants) {
            const session = participant.gameSession
            const sessionId = session.sessionId

            if (!sessionsMap.has(sessionId)) {
                sessionsMap.set(sessionId, {
                    sessionId: session.sessionId,
                    date: session.date,
                    game: session.game,
                    participants: []
                })
            }

            sessionsMap.get(sessionId).participants.push({
                id: participant.id,
                member: participant.member,
                score: participant.score,
                winner: participant.winner
            })
        }

        return Array.from(sessionsMap.values())
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