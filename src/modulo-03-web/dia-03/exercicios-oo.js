function isItem(item){
  return this.item.constructor===Item;
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

CarrinhoDeCompras.prototype.adicionarItem = function (item) {
  if(isItem(item)){
    this.itens.push(item);
  }
};

CarrinhoDeCompras.prototype.atualizarQuantidade = function (sku, quantidade) {
  this.itens[this.indexOfItem(sku)].quantidade += quantidade;
};

CarrinhoDeCompras.prototype.indexOfItem = function (sku) {
  return this.itens.map(function(elem){return elem.sku}).indexOf(sku);
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
var item = new Item('sku','feijão',2,5);
var item2 = new Item('sku2','arroz',3,2);
var item3 = new Item('sku3','alface',1,0.5);
var item4 = new Item('sku4','copos 10 unidades',1,6.49);
basket.adicionarItem(item);
basket.adicionarItem(item2);
basket.adicionarItem(item3);
basket.adicionarItem(item4);

//carrinho tem 4 items
console.assert(4 === basket.itens.length, 'Quantidade de itens incorretos!!!');

//carrinho possui 3 itens após remoção
basket.removerItem('sku3');
console.assert(3 === basket.itens.length, 'Quantidade de itens incorretos!!!');

//carrinho possui feijão, arroz e copo
for(var i=0,len=basket.itens.length,esperado=['feijão','arroz','copos 10 unidades'];i<len;i++){
  console.assert(esperado[i] === basket.itens[i].descricao, 'Descrição do item incorreta!!!');
}

//
