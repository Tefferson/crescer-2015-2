function Controller(options){
  options = options || {};
  this.jogo = new Jogo();
};

Controller.prototype.initGame = function(){
  var maxErros = $('.dificuldade:eq(0)').val()==='normal'?5:2;
  this.tamanhoMinimoPalavra = maxErros===2?13:0;
  $('.botao').prop('disabled', true);
  this.banco.buscarPalavra(
    {
      callback:function(args){
        args.self.palavra=args.palavra;
        args.self.jogo.novaPartida({palavra:args.palavra,dica:args.dica});
        args.self.jogo.init();
        $('.palavra:eq(0)').html(args.self.jogo.getPalavra());
        $('.hint:eq(0)').html(args.self.jogo.getDica());
        $('.botao').prop('disabled', false);
        args.self.updatePontos(args.self.jogo.getPontos());
      }
      , self:this
    }
  );
};

Controller.prototype.updateTela = function(){
  if(this.estado==='informando dados'){
    $('.animation').addClass('ocultar');
    $('.form-container').removeClass('ocultar');
  }else if(this.estado==='jogando'){
    $('.animation').addClass('ocultar');
    $('.palavra').removeClass('ocultar');
    $('.teclado').removeClass('ocultar');
    $('.palpite').removeClass('ocultar');
    $('.opcoes').removeClass('ocultar');
    $('.pontos').removeClass('ocultar');
    $('.dica').removeClass('ocultar');
  }else if(this.estado==='game over'){
    $('.palavra').addClass('ocultar');
    $('.teclado').addClass('ocultar');
    $('.palpite').addClass('ocultar');
    $('.pontos').addClass('ocultar');
    $('.dica').addClass('ocultar');
    $('.game-over').removeClass('ocultar');
  }
}

Controller.prototype.initBanco = function(){
  this.banco = new Banco();
};

Controller.prototype.init = function() {
  this.initBanco();
  $('.botao').click(function(e){this.verificarCompletude(e.toElement)}.bind(this));
  $('#btnPalpite').click(function(){this.verificarCompletude($('#palpite').val().toUpperCase())}.bind(this));
  $('.iniciar').click(function(){this.buscarJogadorPrincipal({nome:$('.nome').val()})}.bind(this));
  $('#reiniciar').click(function(){this.reiniciarJogo()}.bind(this));
  $('#top5').click(function(){this.toogleRanking()}.bind(this));
  $('.showHint').click(function(){this.mostrarDica()}.bind(this));
  this.estado = 'informando dados';
  this.updateTela();
};

Controller.prototype.updatePontos = function(pontos){
  pontos = pontos || this.jogo.getPontos();
  $('.pontos:eq(0)').html(pontos+' pontos/'+this.jogo.erros+' erros');
}

Controller.prototype.mostrarDica = function(){
  $('.showHint').prop('disabled',true);
  $('.hint').removeClass('ocultar');
}

Controller.prototype.buscarJogadorPrincipal = function(args){
  if(!!args.jogador){
    var self = controller;
    self.jogador = args.jogador;
    self.banco.resetarPalavrasRepetidas(self.jogador.nome);
    $('.jogador-info').html(args.jogador.toString());
    self.initGame();
    self.estado = 'jogando';
    self.updateTela();
    self.jogo.maxErros = $('.dificuldade:eq(0)').val()==='normal'?5:2;
  }else{
    var self = this;
    self.banco.buscarOuCriarJogador({callback:self.buscarJogadorPrincipal
      ,nome:args.nome}
    );
  }
};

Controller.prototype.endGame = function(){
  $('.palavra').html('');
  this.estado = 'game over';
  this.updateTela();
};

Controller.prototype.reiniciarJogo = function(){
  this.jogo.erros=0;
  this.jogo.pontos=0;
  this.buscarJogadorPrincipal({jogador:this.jogador});
};

Controller.prototype.verificarCompletude = function(elem){
  if (elem.length > 1){
    this.jogo.chutarPalavra(elem);
  }else{
    this.jogo.chutarLetra(elem.value);
    elem.disabled=true;
  }
  this.updatePontos();
  if(this.jogo.estado==='derrota'){
    $('.showHint').prop('disabled',false);
    $('.hint').addClass('ocultar');
    this.endGame();
  }else if(this.jogo.estado==='vitoria'){
    $('.showHint').prop('disabled',false);
    $('.hint').addClass('ocultar');
    this.postVitoria();
    this.initGame();
  }
  $('.palavra:eq(0)').html(this.jogo.getPalavra());
  this.updatePontos();
  $('#palpite').val('');
};

Controller.prototype.postVitoria = function(){
  this.jogo.pontos+=105;
  this.jogador.pontuacao+=105;
  this.banco.postVitoria(this.jogador);
  alert('vitoria!!!Começando próxima rodada');
};

Controller.prototype.toogleRanking = function(){
  this.loadRanking();
  $('.rank').toggleClass('ocultar');
}

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
