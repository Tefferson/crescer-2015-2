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
  this.itens.slice(this.indexOfItem(sku),1);
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

var c = new CarrinhoDeCompras();
var item = new Item('sku','desc',2,5);
var item2 = new Item('sku2','desc2',22,52);
var item3 = new Item('sku3','desc3',2,5);
var item4 = new Item('sku4','desc4',22,52);
c.adicionarItem(item);
c.adicionarItem(item2);
c.adicionarItem(item3);
c.adicionarItem(item4);
