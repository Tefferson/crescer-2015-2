function Controller(options){
  options = options || {};
};

Controller.prototype.initGame = function(){
  $('.botao').prop('disabled', true);
  this.palavra='PALAVRA';
  this.jogo = new Jogo({palavra:this.palavra,maxErros:2, encerrarPartida:this.encerrarPartida});
  this.jogo.init();
  $('.palavra')[0].innerHTML=this.jogo.getPalavra();
  $('.botao').prop('disabled', false);
}

Controller.prototype.init = function () {
  this.initGame();
  $('.botao').click(function(e){this.verificarCompletude(e.toElement);}.bind(this));
};

Controller.prototype.verificarCompletude = function(elem){
  this.jogo.chutarLetra(elem.value);
  elem.disabled=true;
  if(this.jogo.estado==='derrota'){
    this.initGame();
  }else if(this.jogo.estado==='vitoria'){
    this.postVitoria();
    this.initGame();
  }
  $('.palavra')[0].innerHTML=this.jogo.getPalavra();
}

Controller.prototype.postVitoria = function(){
  alert('vitoria!!!');
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
