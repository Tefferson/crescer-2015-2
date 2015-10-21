function CarrinhoDeComprasChantagista(){
  CarrinhoDeCompras.call(this);
};

CarrinhoDeComprasChantagista.prototype.forcarCompra = function(){
  if(!this.intervalId){
    this.intervalId = setInterval(function(){
      this.itens.forEach(function(elem){
        elem.valorUnitario*=1.1;
      });
    }.bind(this), 5000);
  }
};

CarrinhoDeComprasChantagista.prototype.concluirPedido = function () {
  clearInterval(this.intervalId);
  delete this.intervalId;
};

CarrinhoDeComprasChantagista.prototype = Object.create(CarrinhoDeCompras.prototype);
