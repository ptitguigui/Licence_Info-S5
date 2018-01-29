import Mobile from './Mobile.js'
import starshipSrc from '../images/vaisseau-ballon-petit.png';

var MoveState = {UP: 0, DOWN: 1, NONE: 2};

export default class Starship extends Mobile {
    constructor(aCanvas, initX, initY) {
        super(aCanvas, starshipSrc, initX, initY, 0, 8);
        this.moving = MoveState.NONE;
    }

    get up() {
        return this.moving === MoveState.UP;
    }

    get down() {
        return this.moving === MoveState.DOWN;
    }

    moveUp() {
        this.moving = MoveState.UP;
    }

    moveDown() {
        this.moving = MoveState.DOWN;
    }

    move() {
        let tempY;

        if (this.moving === MoveState.UP) {
            tempY = this.y -= this.vy;
            this.vy = -this.vy;
        } else if (this.moving === MoveState.DOWN) {
            tempY = this.y += this.vy;
        }

        if (tempY >= 0 && tempY <= this.myCanvas.height) {
            super.move();
            if (this.moving === MoveState.UP) {
                this.vy = -this.vy;
            }
        }
    }


}
