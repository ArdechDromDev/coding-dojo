class Player {

    constructor() {
        this._point = 0;
        this._name = "";
    }

    playerWinPoint() {
        this._point++;

    }

    getPoint(){
        return this._point;
    }

}

module.exports = Player;
