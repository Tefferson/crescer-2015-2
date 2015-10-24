function Controller(options){
  options = options || {};
};

Controller.prototype.initGame = function(){
  var maxErros = $('.dificuldade:eq(0)').val()==='normal'?5:2;
  this.tamanhoMinimoPalavra = maxErros===2?0:12
  $('.botao').prop('disabled', true);
  this.palavra='PALAVRA';
  this.jogo = new Jogo({palavra:this.palavra
    ,maxErros:maxErros
    ,encerrarPartida:this.encerrarPartida}
  );
  this.jogo.init();
  $('.palavra')[0].innerHTML=this.jogo.getPalavra();
  $('.botao').prop('disabled', false);
};

Controller.prototype.initBanco = function(){
  this.banco = new Banco();
};

Controller.prototype.init = function() {
  this.initBanco();
  this.loadRanking();
  $('.botao').click(function(e){this.verificarCompletude(e.toElement);}.bind(this));
  $('#btnPalpite').click(function(){this.verificarCompletude($('#palpite').val().toUpperCase());});
  $('.iniciar').click(function(){this.buscarJogadorPrincipal({nome:$('.nome').val()})}.bind(this));
};

Controller.prototype.buscarJogadorPrincipal = function(args){
  if(!!args.jogador){
    $('.form-container').toggleClass('ocultar');
    $('body div:eq(1)').toggleClass('ocultar');
    $('body div:eq(2)').toggleClass('teclado');
    $('body div:eq(6)').toggleClass('ocultar');
    controller.jogador = args.jogador;
    controller.initGame();
  }else{
    var self = this;
    self.banco.buscarOuCriarJogador({callback:self.buscarJogadorPrincipal
      ,nome:args.nome}
    );
  }
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

Controller.prototype.loadRanking = function(data){
  if(!!data){
    var rank = $('.rank ul');
    rank.empty();
    data.map(function(elem){
      return elem.nome+' '+elem.pontuacao;
    }).forEach(function(elem){
      rank.append($('<li>'+elem+'</li>'));
    }
  );
}else{
  this.banco.topRanking({callback:this.loadRanking});
}
};

var controller = new Controller();
controller.init();
