/**
 * Data model for a board game.
 */
export class Game {
    constructor(initObj) {
        this.gameId = initObj.gameId ?? initObj.id
        this.name = initObj.name
        this.purchaseDate = initObj.purchaseDate
        this.players = initObj.players
        this.category = initObj.category
        this.age = initObj.age
        this.averagePlayTime = initObj.averagePlayTime
        this.description = initObj.description
        this.clubComment = initObj.clubComment
    }
}