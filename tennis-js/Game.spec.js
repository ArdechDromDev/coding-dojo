const {expect} = require('chai');
const Game = require('./Game');

describe('Game', () => {

    let app;

    beforeEach(() => {
        app = require('./app');
    });

    it('should start at 0-0', () => {
        const game = new Game();
        expect(game.getJoueur1()).to.equal(0);
        expect(game.getJoueur2()).to.equal(0);
    });

    it('score is 15-0 when first player win point', () => {
        const game = new Game();
        game.playerWinPoint(1);
        expect(game.getJoueur1()).to.equal(15);
        expect(game.getJoueur2()).to.equal(0);
    });

    it('score is 0-15 when second player win point', () => {
        const game = new Game();
        game.playerWinPoint(2);
        expect(game.getJoueur1()).to.equal(0);
        expect(game.getJoueur2()).to.equal(15);
    });

    it('score should be 30-0 when first player win 2 points for example', () => {
        const game = new Game();
        game.playerWinPoint(1);
        game.playerWinPoint(1);
        expect(game.getJoueur1()).to.equal(30);
        expect(game.getJoueur2()).to.equal(0);
    });

    it('score should be 0-30 when second player win 2 points for example', () => {
        const game = new Game();
        game.playerWinPoint(2);
        game.playerWinPoint(2);
        expect(game.getJoueur1()).to.equal(0);
        expect(game.getJoueur2()).to.equal(30);
    });

    it('score should be 0-40 when second player win 3 points for example', () => {
        const game = new Game();
        game.playerWinPoint(2);
        game.playerWinPoint(2);
        game.playerWinPoint(2);
        expect(game.getJoueur1()).to.equal(0);
        expect(game.getJoueur2()).to.equal(40);
    });

    it('score should be 40-ADV when second player win 4 points for example', () => {
        const game = new Game();
        game.playerWinPoint(1);
        game.playerWinPoint(1);
        game.playerWinPoint(1);
        game.playerWinPoint(2);
        game.playerWinPoint(2);
        game.playerWinPoint(2);
        game.playerWinPoint(2);
        expect(game.getJoueur1()).to.equal(40);
        expect(game.getJoueur2()).to.equal("ADV");
    });

    it('game shouold not be over when score is 40-ADV', () => {
        const game = new Game();
        game.playerWinPoint(1);
        game.playerWinPoint(1);
        game.playerWinPoint(1);
        game.playerWinPoint(2);
        game.playerWinPoint(2);
        game.playerWinPoint(2);
        game.playerWinPoint(2);
        expect(game.playerWin(1)).to.be.false;
        expect(game.playerWin(2)).to.be.false;

    });

    it('second player should win if scored after ADV', () => {
        const game = new Game();
        game.playerWinPoint(1);
        game.playerWinPoint(1);
        game.playerWinPoint(1);
        game.playerWinPoint(2);
        game.playerWinPoint(2);
        game.playerWinPoint(2);
        game.playerWinPoint(2);
        game.playerWinPoint(2);
        expect(game.getJoueur1()).to.equal(40);
        expect(game.playerWin(2)).to.be.true;
    });

    it('second player should win if scored 4 times in a row', () => {
        const game = new Game();
        game.playerWinPoint(2);
        game.playerWinPoint(2);
        game.playerWinPoint(2);
        game.playerWinPoint(2);
        expect(game.getJoueur1()).to.equal(0);
        expect(game.playerWin(2)).to.be.true;
    });

    it('should consider as not win when player score is 0', () => {
        const game = new Game();
        expect(game.getJoueur1()).to.equal(0);
        expect(game.playerWin(1)).to.be.false;
    });
});
