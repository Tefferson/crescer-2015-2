function Jogo(options){
  options = options || {};
  this.palavra=options.palavra;
  this.dica=options.dica;
  this.maxErros=options.maxErros;
  this.erros=0;
  this.estado='jogando';
  this.pontos=0;
};

Jogo.prototype.init = function(){
  this.letras = this.palavra.split('').map(
    function(elem){
      return {letra:elem, visivel:elem===' '};
    }
  );
};

Jogo.prototype.chutarLetra = function(letra){
  if(this.palavra.contains(letra)){
    this.mostrarLetra(letra);
    if(this.verificarCompletude()){
      this.estado = 'vitoria';
    }
  }else{
    this.incrementarErros();
  }
};

Jogo.prototype.novaPartida = function(options){
  this.estado = 'jogando';
  this.palavra = options.palavra;
  this.dica = options.dica;
  this.init();
};

Jogo.prototype.incrementarErros = function(){
  this.erros++;
  if(this.perdeu()){
    this.estado = 'derrota';
  }
};

Jogo.prototype.perdeu = function(){
  return this.erros>this.maxErros;
};

Jogo.prototype.chutarPalavra = function(palavra){
  this.estado=this.palavra===palavra?'vitoria':'derrota';
};

Jogo.prototype.getPalavra = function(){
  var letrasVisiveis = this.letras.map(
    function(elem){
      return elem.visivel?elem.letra:'_';
    }
  );
  return letrasVisiveis.join('');
};

Jogo.prototype.getDica = function(){
  return this.dica;
}

Jogo.prototype.getPontos = function(){
  return this.pontos;
}

Jogo.prototype.verificarCompletude = function(){
  return this.letras.reduce(
    function(acc, elem){
      return acc = acc && elem.visivel;
    }
  );
}

Jogo.prototype.mostrarLetra = function(letra){
  this.letras.forEach(
    function(elem){
      if(elem.letra===letra){elem.visivel=true;}
    }
  );
};
