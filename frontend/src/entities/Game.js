/**
 * Data model for a board game.
 */
export class Game {
    constructor(initObj) {
        this.gameId = initObj.gameId
        this.gameName = initObj.gameName
        this.purchaseDate = initObj.purchaseDate
        this.totalPlayers = initObj.totalPlayers
        this.category = initObj.category
        this.recommendedAge = initObj.recommendedAge
        this.averagePlayTime = initObj.averagePlayTime
        this.description = initObj.description
        this.memberComment = initObj.memberComment
    }
}