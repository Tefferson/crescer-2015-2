function Jogador(options){
  options = options || {};
  this.nome = options.nome;
  this.pontuacao = options.pontuacao;
}

Jogador.prototype.toString = function(){
  return this.nome + ' ' + this.pontuacao;
}
