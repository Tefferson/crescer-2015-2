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
};

Controller.prototype.initBanco = function(){
  this.banco = new Banco();
}

Controller.prototype.init = function () {
  this.initBanco();
  this.initGame();
  $('.botao').click(function(e){this.verificarCompletude(e.toElement);}.bind(this));
  $('#btnPalpite').click(function(e){this.verificarCompletude($('#palpite').val().toUpperCase());});
//  $('.iniciar').click(function(){this.banco.cadastro($('.nome'));}.bind(this));
};

Controller.prototype.verificarCompletude = function(elem){
  if (elem.length > 1){
    this.jogo.chutarPalavra(elem);
  }else{
    this.jogo.chutarLetra(elem.value);
    elem.disabled=true;
  }

  if(this.jogo.estado==='derrota'){
    this.initGame();
  }else if(this.jogo.estado==='vitoria'){
    this.postVitoria();
    this.initGame();
  }
  $('.palavra')[0].innerHTML=this.jogo.getPalavra();
  $('#palpite').val('');
};

Controller.prototype.postVitoria = function(){
  alert('vitoria!!!');
};

var controller = new Controller();
controller.init();

function hideForm(){
  var $toggle = $('.iniciar');
  $toggle.click(function(e){
    $('.form-container').toggleClass('ocultar');
    $('body div:eq(1)').toggleClass('ocultar');
    $('body div:eq(2)').toggleClass('teclado');
    $('body div:eq(6)').toggleClass('ocultar');
  });
};
hideForm();
