/**
 * Data model for one participant in a game session.
 * Represents the bridge table between Member and GameSession.
 */
export class SessionParticipant {
    constructor(initObj) {
        this.id = initObj.id
        this.memberId = initObj.memberId
        this.sessionId = initObj.sessionId
        this.score = initObj.score
        this.winner = initObj.winner
    }
}