import ApiService from './ApiService.js'
import { GameSession } from '../entities/GameSession.js'

class GameSessionService {
    constructor() {
        this.apiService = new ApiService()
    }

    async getAll() {
        const json = await this.apiService.getData('/gamesessions')
        return json.map(session => new GameSession(session))
    }

    async create(gameId, date) {
        return await this.apiService.postData('/gamesessions', {
            gameId,
            date
        })
    }

    async update(sessionId, gameId, date) {
        const params = new URLSearchParams()

        if (gameId !== null && gameId !== undefined) {
            params.append('gameId', gameId)
        }

        if (date) {
            params.append('date', date)
        }

        return await this.apiService.putData(`/gamesessions/${sessionId}?${params}`)
    }

    async delete(sessionId) {
        return await this.apiService.deleteData(`/gamesessions/${sessionId}`)
    }
}

export default GameSessionService