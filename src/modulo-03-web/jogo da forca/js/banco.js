function Banco(options){
  options = options || {};
};

Banco.prototype.buscarJogadores = function(args){
  $.get('http://localhost:3000/pessoas')
  .done(function(data) {
    args.callback({data:data, callback:args.cbCallback})
  });
};

Banco.prototype.topRanking = function(args){
  if(!!args.data){
    args.callback(args.data.sort(
      function(a,b){
        return a.pontuacao<b.pontuacao;
      }
    ).map(function(elem){return new Jogador({pontuacao:elem.pontuacao,nome:elem.nome});})
    .slice(0,5));
  }else if(!!args.callback){
    this.buscarJogadores({callback:this.topRanking, cbCallback:args.callback});
  }
};

Banco.prototype.cadastro = function(nome) {
  var jogadores=[];
  var sd='';
  $.get('http://localhost:3000/pessoas')
  .done(function(data) {
    data.forEach(function(elem){
      jogadores.push(elem);
      if(elem.nome===nome){
        sd=elem.nome;
      }
    });
    if(sd===''){
      $.post('http://localhost:3000/pessoas',
      { nome: nome, pontuacao: 0 }
    );
  }else{
    console.log("Nome já consta");
  }
});
};

Banco.prototype.ranking=function(){
  var jogadores=[];
  $.get('http://localhost:3000/pessoas')
  .done(function(data){
    data.forEach(function(elem){
      jogadores.push(elem);
    })
    jogadores.sort(function(elem,elem2){
      return elem.pontuacao < elem2.pontuacao;
    });
    //tenho dados aqui, inicialiar funcao;
    jogadores.forEach(function(elem){
      console.log(elem.nome);
    });
  });
};
