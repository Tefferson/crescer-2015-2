function isItem(verificar){
  return verificar.constructor===Item;
}

function Item(sku,descricao,quantidade,valorUnitario){
  this.sku=sku;
  this.descricao=descricao;
  this.quantidade=quantidade;
  this.valorUnitario=valorUnitario;
};

//Subtotal da alface é 0.5
var subTotal = alface.calcularSubTotal();
console.assert(subTotal===0.5, 'Subtotal incorreto: ',subTotal);
