import ApiService from './ApiService.js'

class StatisticsService {
    constructor() {
        this.apiService = new ApiService()
    }

    async getAllTimeWins() {
        return await this.apiService.getData('/statistics/wins/all-time')
    }

    async getMostPlayedGames() {
        return await this.apiService.getData('/statistics/most-played-games')
    }
}

export default StatisticsService