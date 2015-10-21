//Força compra e conclui pedido
var basket = new CarrinhoDeComprasChantagista();
var farofa = new Item('farofa','saco de farofa',2,3);
basket.adicionarItem(farofa);

basket.forcarCompra();
console.assert(!!basket.intervalId,'Id do intervalo não está definido!!!');
basket.concluirPedido();
console.assert(!basket.intervalId,'Id do intervalo está definido!!!');

//Pedido valor total aumenta após 5 segundos
var basket = new CarrinhoDeComprasChantagista();
var farofa = new Item('farofa','saco de farofa',2,3);
basket.adicionarItem(farofa);
basket.forcarCompra();

setTimeout(function(){
  console.assert(
    function(){
      var total = parseFloat(basket.calcularTotal().toFixed(2));
      return (total === 6.6) || (total === 5.94);
    }()
    ,'Compra não está sendo forçada!!!');
  },5001
);

basket.concluirPedido;
