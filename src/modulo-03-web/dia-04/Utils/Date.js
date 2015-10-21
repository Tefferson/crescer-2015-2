Date.prototype.bissexto = function(){
  var ano = this.getFullYear();
  return (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);
};
