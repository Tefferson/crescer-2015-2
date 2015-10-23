function Controller(options){
  options = options || {};
};

Controller.prototype.initGame = function(){
  this.palavra='PALAVRA';
  this.jogo = new Jogo({palavra:this.palavra,maxErros:2, encerrarPartida:this.encerrarPartida});
  this.jogo.init();
  $('.palavra')[0].innerHTML=this.jogo.getPalavra();
}

Controller.prototype.init = function () {
  this.initGame();
  $('.botao').click(function(e){this.verificarCompletude(e.toElement.value);}.bind(this));
};

Controller.prototype.verificarCompletude = function(letra){
  this.jogo.chutarLetra(letra);
  if(this.jogo.estado==='derrota'){
    this.initGame();
  }else if(this.jogo.estado==='vitoria'){
    alert('vitória!!!');this.palavra='PALAVRA';
    this.jogo = new Jogo({palavra:this.palavra,maxErros:2, encerrarPartida:this.encerrarPartida});
    this.jogo.init();
    $('.palavra')[0].innerHTML=this.jogo.getPalavra();
  }
  $('.palavra')[0].innerHTML=this.jogo.getPalavra();
}

var controller = new Controller();
controller.init();

function hideForm(){
  var $toggle = $('.btnid');
  $toggle.click(function(e){
    $('.form-container').toggleClass('ocultar');
    $('body div:eq(1)').toggleClass('ocultar');
    $('body div:eq(2)').toggleClass('teclado');
  });
};
hideForm();
