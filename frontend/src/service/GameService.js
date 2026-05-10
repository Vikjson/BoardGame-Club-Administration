import ApiService from './ApiService.js'
import { Game } from '../entities/Game.js'

class GameService {
    constructor() {
        this.apiService = new ApiService()
    }

    async getAll() {
        const json = await this.apiService.getData('/games')
        return json.map(game => new Game(game))
    }

    async create(game) {
        return await this.apiService.postData('/games', game)
    }

    async delete(gameId) {
        return await this.apiService.deleteData(`/games/${gameId}`)
    }
}

export default GameService