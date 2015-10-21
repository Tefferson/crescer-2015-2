function isItem(itemIs){
  return itemIs.constructor===Item;
};

function isString(stringIs){
  return stringIs.constructor===String;
};

function isNumber(numberIs){
  return numberIs.constructor===Number;
};

function Item(sku,descricao,quantidade,valorUnitario){
  if(!(isString(sku) && isString(descricao)
  && isNumber(quantidade)
  && isNumber(valorUnitario))) throw new Error('Argumentos inválidos');
  this.sku=sku;
  this.descricao=descricao;
  this.quantidade=quantidade;
  this.valorUnitario=valorUnitario;
};

Item.prototype.calcularSubTotal = function () {
  return this.quantidade * this.valorUnitario;
};

//Subtotal da alface é 0.5
var alface = new Item('sku3','alface',1,0.5);
var subTotal = alface.calcularSubTotal();
console.assert(subTotal===0.5, 'Subtotal incorreto: ',subTotal);

//Item não é criado com parâmetros inválidos
var esperado;
try{
  item = new Item(1,2,3,4);
}catch(e){
  esperado = "resultado";
}
console.assert(esperado==="resultado", 'Validação de parâmetros inconsistente!!!');
