function Banco(options){
  options = options || {};
  this.baseURL = 'http://localhost:3000/';
};

Banco.prototype.buscarJogadores = function(args){
  $.get(this.baseURL+'pessoas'+(args.query || ''))
  .done(function(data) {
    args.callback({data:data, nome:args.nome
      ,callback:args.cbCallback
      ,self:args.self}
    );
  });
};

Banco.prototype.buscarOuCriarJogador = function(args){
  if(!!args.data){
    if(args.data.length>0){
      args.callback({jogador:new Jogador(
        {nome:args.data[0].nome
          ,pontuacao:args.data[0].pontuacao}
        )
      }
    );
  }else{
    args.self.criarJogador({callback:args.self.buscarOuCriarJogador
      ,data:[{nome:args.nome,pontuacao:0}]
      ,self:args.self,cbCallback:args.callback});
    }
  }else{
    this.buscarJogadores({nome:args.nome
      ,query:'?nome='+args.nome
      ,callback:this.buscarOuCriarJogador
      ,cbCallback:args.callback,self:this}
    );
  }
};

Banco.prototype.criarJogador = function(args){
  $.post('http://localhost:3000/pessoas',{ nome: args.data[0].nome, pontuacao: 0 })
  .done(function(){
    args.callback(
      {callback:args.cbCallback
        ,data:[{nome:args.data[0].nome,pontuacao:0}],self:args.self
      }
    );
  });
};

Banco.prototype.topRanking = function(args){
  if(!!args.data){
    args.callback(args.data.map(function(elem){
      return new Jogador({pontuacao:elem.pontuacao,nome:elem.nome});
    }));
  }else if(!!args.callback){
    this.buscarJogadores({query:'?_sort=pontuacao&_order=DESC&_end=5'
    ,callback:this.topRanking
    ,cbCallback:args.callback});
  }
};

Banco.prototype.buscarPalavra = function(tamanhoMinimo){
  var randomId = Math.random()*61;
  var query = this.baseURL+ 'palavras' + '?idpalavra_gte='+~~randomId+'&idpalavra_lte='+~~randomId;
  $.get(query)
  .done(function(data) {
    console.log(data);
  });
}
