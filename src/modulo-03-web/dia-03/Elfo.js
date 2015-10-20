function Elfo(nome, flechas){
  this.nome = nome;
  this.flechas = flechas || 42;

  Object.defineProperty(this,'flechas',{writable:false});

  Elfo.prototype.atirarFlecha = function (dwarf) {
    console.log('Atirando flecha...');
  };
};
