function Jogador(options){
  options = options || {};
  this.nome = options.nome;
  this.pontuacao = options.pontuacao;
  this.id = options.id;
}

Jogador.prototype.toString = function(){
  return this.nome + ' ' + this.pontuacao;
}

Jogador.prototype.getPontuacao = function(){
  return this.pontuacao;
}
