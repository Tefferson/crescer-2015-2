function isItem(verificar){
  return verificar.constructor===Item;
}

function Item(sku,descricao,quantidade,valorUnitario){
  this.sku=sku;
  this.descricao=descricao;
  this.quantidade=quantidade;
  this.valorUnitario=valorUnitario;
};

function CarrinhoDeCompras(){
  this.itens = [];
};

CarrinhoDeCompras.prototype.removerItem = function (sku) {
  return this.itens.splice(this.indexOfItem(sku), 1);
};

CarrinhoDeCompras.prototype.adicionarItem = function (newItem) {
  if(isItem(newItem)){
    this.itens.push(newItem);
  }
};

CarrinhoDeCompras.prototype.atualizarQuantidade = function (sku, quantidade) {
  this.itens[this.indexOfItem(sku)].quantidade = quantidade;
};

CarrinhoDeCompras.prototype.indexOfItem = function (sku) {
  return this.itens.map(function(elem){return elem.sku;}).indexOf(sku);
};

CarrinhoDeCompras.prototype.calcularTotal = function () {
  var valorTotal = this.itens
  .map(function(elem){return elem.calcularSubTotal();})
  .reduce(function(acumulador, elem){return acumulador+elem;});
  return valorTotal * (this.sortearDesconto()?0.9:1);
};

CarrinhoDeCompras.prototype.sortearDesconto = function () {
  return Math.random()<=0.4;
};

Item.prototype.calcularSubTotal = function () {
  return this.quantidade * this.valorUnitario;
};

var basket = new CarrinhoDeCompras();
var feijao = new Item('sku','feijão',2,5);
var arroz = new Item('sku2','arroz',3,2);
var alface = new Item('sku3','alface',1,0.5);
var copo10Unidades = new Item('sku4','copo 10 unidades',1,6.49);
basket.adicionarItem(feijao);
basket.adicionarItem(arroz);
basket.adicionarItem(alface);
basket.adicionarItem(copo10Unidades);

//carrinho tem 4 items
console.assert(4 === basket.itens.length, 'Quantidade de itens incorretos: ', basket.itens.length);

//carrinho possui 3 itens após remoção
basket.removerItem(alface.sku);
console.assert(3 === basket.itens.length, 'Quantidade de itens incorretos: ',basket.itens.length);

//carrinho possui feijão, arroz e copo
for(var i=0,len=basket.itens.length,esperado=['feijão','arroz','copo 10 unidades'];i<len;i++){
  console.assert(esperado[i] === basket.itens[i].descricao, 'Descrição do item incorreta: ',basket.itens[i].descricao);
}

//valor total é 22.99 sem desconto ou 20.69 com desconto
basket.adicionarItem(alface);
var esperadoSemDesconto = 22.99;
var esperadoComDesconto = 20.69;
var valorTotal = parseFloat((basket.calcularTotal()).toFixed(2));
console.assert((esperadoComDesconto===valorTotal)||(esperadoSemDesconto===valorTotal)
, 'Valor total incorreto: ',valorTotal);

//Subtotal da alface é 0.5
var subTotal = alface.calcularSubTotal();
console.assert(subTotal===0.5, 'Subtotal incorreto: ',subTotal);
