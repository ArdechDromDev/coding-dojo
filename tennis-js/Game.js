const Player = require('./Player');

const Scores = [0, 15, 30, 40, "ADV"];


class Game {
    constructor() {
        this._players = [new Player(), new Player()];
    }

    playerWinPoint(player) {
        this._players[player - 1].playerWinPoint()
    }

    playerWin(playerId) {
        var opponentId = playerId === 1 ? 2 : 1
        if (this._players[playerId - 1].getPoint() >= 4) {
            if (this._players[playerId - 1].getPoint() - this._players[opponentId - 1].getPoint() >= 2) {
                return true;
            }
        }
        return false;
    }

    getJoueur1() {
        return humanizePoint(this._players[0].getPoint());
    }

    getJoueur2() {
        return humanizePoint(this._players[1].getPoint());
    }

}

function humanizePoint(point) {
    return Scores[point];
}

module.exports = Game;
