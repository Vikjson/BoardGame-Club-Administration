/**
 * Data model for one participant in a game session.
 * Represents the bridge table between Member and GameSession.
 */
export class SessionParticipant {
    constructor(initObj) {
        this.id = initObj.id
        this.member = initObj.member
        this.gameSession = initObj.gameSession
        this.score = initObj.score
        this.winner = initObj.winner
    }
}