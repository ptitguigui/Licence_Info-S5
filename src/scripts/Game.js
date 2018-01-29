import Starship from './Starship.js';
import Saucer from './Saucer.js';


export default class Game {
    constructor(aCanvas) {
        this.myCanvas = aCanvas;
        this.raf = undefined;
	this.context = this.myCanvas.getContext('2d');

	this.starship = undefined;
	this.saucers = [];
	this.score = 0;

        this.init();
    }

    init() {
        this.starship = new Starship(this.myCanvas, 40, this.myCanvas.height / 2);
        this.saucers = [];
        this.score = 0;
    }

    addSaucer() {
        let x = this.myCanvas.width;
        let y = Math.floor(Math.random() * this.myCanvas.height);

        this.saucers.push(new Saucer(this.myCanvas, x, y));
        console.log("added saucer");
    }

    infiniteSaucers() {
        // nothing
    }

    removeSaucer(saucer) {
        this.saucers = this.saucers.filter(e => e !== saucer);
    }

    setScore(updatedScore) {
        this.score = updatedScore;
    }

    animate() {
        this.context.clearRect(0, 0, this.myCanvas.width, this.myCanvas.height);

        this.starship.move();
        this.starship.draw();

        for (let singleSaucer of this.saucers) {
            singleSaucer.move();
            singleSaucer.draw();
        }

        this.raf = window.requestAnimationFrame(this.animate.bind(this));
    }

    keyDownActionHandler(event) {
        switch (event.key) {
            case "ArrowUp":
            case "Up":
                this.starship.moveUp();
                break;
            case "ArrowDown":
            case "Down":
                this.starship.moveDown();
                break;
            default:
                return;
        }
        event.preventDefault();
    }


}
