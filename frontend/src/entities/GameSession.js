import { SessionParticipant } from './SessionParticipant.js'

/**
 * Data model for a played game session.
 */
export class GameSession {
    constructor(initObj) {
        this.gameSessionId = initObj.gameSessionId ?? initObj.id
        this.gameId = initObj.gameId
        this.date = initObj.date
        this.game = initObj.game
    }
}