import Mobile from './Mobile.js';

import saucerSrc from '../images/flyingSaucer-petit.png';

export default class Saucer extends Mobile {
    constructor(aCanvas, x, y, game) {
        super(aCanvas, saucerSrc, x, y, -3, 0);
        this.game = game;
    }

    move() {
        super.move();

        if (this.x <= 0) {
            this.game.removeSaucer(this);
        }
    }
}
