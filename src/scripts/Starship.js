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

    move(force) {

        if (!force && !this.isMoving()) {
            return;
        }

        let tempY = -1;

        if (this.moving === MoveState.UP) {
            this.vy = -this.vy;
        }
        tempY = this.y += this.vy;

        if (this.yIsInBounds(tempY)) {
            super.move();
            if (this.moving === MoveState.UP) {
                this.vy = -this.vy;
            }
        } else {
            console.log("refused");
        }
        console.log("actual y:" + this.y);
    }

    yIsInBounds(y) {
        console.log("new Y=" + y + " height = " + this.myCanvas.height);
        console.log("y < this.myCanvas.height: " + (y < this.myCanvas.height));

        return (y > 0) && (y < this.myCanvas.height);
    }

    stopMove() {
        this.moving = MoveState.NONE;
    }


    isMoving() {
        return this.moving != MoveState.NONE;
    }
}
