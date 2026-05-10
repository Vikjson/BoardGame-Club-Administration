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
        await this.apiService.postData('/games', game)
    }

    async update(gameId, game) {
        const params = new URLSearchParams()

        if (game.gameName) params.append('gameName', game.gameName)
        if (game.purchaseDate) params.append('purchaseDate', game.purchaseDate)
        if (game.totalPlayers !== null && game.totalPlayers !== undefined) params.append('totalPlayers', game.totalPlayers)
        if (game.category) params.append('category', game.category)
        if (game.recommendedAge !== null && game.recommendedAge !== undefined) params.append('recommendedAge', game.recommendedAge)
        if (game.averagePlayTime !== null && game.averagePlayTime !== undefined) params.append('averagePlayTime', game.averagePlayTime)
        if (game.description) params.append('description', game.description)
        if (game.memberComment) params.append('memberComment', game.memberComment)

        await this.apiService.putData(`/games/${gameId}?${params.toString()}`)
    }

    async delete(gameId) {
        await this.apiService.deleteData(`/games/${gameId}`)
    }
}

export default GameService