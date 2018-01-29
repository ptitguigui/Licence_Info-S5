import Game from './Game.js';

import '../style/style.css';
import saucerSrc from '../images/flyingSaucer-petit.png';

var setup = function () {

    let theCanvas = document.getElementById("stars");
    let game = new Game(theCanvas);
    game.constructor = undefined;
    Object.getPrototypeOf(game).constructor = undefined;


    window.addEventListener('keydown', game.keyDownActionHandler.bind(game));
    window.addEventListener('keyup', game.keyUpActionHandler.bind(game));

    let newSaucerButton = document.getElementById("nouvelleSoucoupe");
    newSaucerButton.addEventListener("click", game.addSaucer.bind(game));
    document.getElementById("newSaucerButtonImg").src = saucerSrc;

    let infiniteSaucers = document.getElementById("flotteSoucoupes");
    infiniteSaucers.addEventListener("click", game.infiniteSaucers.bind(game));
    document.getElementById("infiniteSaucersButtonImg").src = saucerSrc;

    game.animate();

};

window.addEventListener("DOMContentLoaded", setup);
