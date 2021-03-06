
/*
 * define here type ImageSlider
 */
var ImageSlider = function(aCanvas){
  this.myCanvas = aCanvas;
  this.position;

  this.context = aCanvas.getContext("2d");

}

ImageSlider.prototype.initImages = function(imgAvant, imgApres){

  this.img1 = new Image();
  this.img1.src = imgAvant;

  this.img2 = new Image();
  this.img2.addEventListener("load", this.init.bind(this));

  this.img2.src = imgApres;
}

ImageSlider.prototype.init = function(){
  this.myCanvas.width = this.img1.width;
  this.myCanvas.height = this.img1.height;
  this.position = this.myCanvas.width/2;

  this.dessine();

  this.myCanvas.addEventListener("keydown",keyAction.bind(this));
}

ImageSlider.prototype.dessine = function(){

  // ctx.drawImage(image, sx, sy, sWidth, sHeight,
  //dx, dy, dWidth, dHeight);
    this.context.clearRect(0,0, this.myCanvas.width, this.myCanvas.height);

    this.context.drawImage(this.img1, 0, 0, this.position, this.img1.height,
      0,0, this.position, this.img1.height);

    this.context.drawImage(this.img2, this.position, 0, this.img2.width, this.img2.height,
      this.position,0, this.img2.width, this.img2.height);

    this.context.fillStyle = "rgb(255,0,0)";
    this.context.fillRect(this.position,0,5,this.myCanvas.height);
}

// la fonction d'écoute, comme toute fonction d'écoute elle dispose d'un paramètre 'event'
var keyAction = function(event) {
   // on peut placer ici du code qui est exécuté dans tous les cas avant le traitement des touches
   switch (event.key) {
      case "ArrowLeft":
      case "Left":
        this.position -= 5;
      break;
      case "ArrowRight":
      case "Right":
        this.position += 5;
      break;
      case "X":       
      case "x":       
        this.position = this.myCanvas.width/2;
      break;
      default: return;
   }
  this.dessine();
   event.preventDefault();   // pour désactiver l'action éventuelle par défaut liée à la touche pressée
}
