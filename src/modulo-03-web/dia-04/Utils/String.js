String.prototype.palindromo = function(){
  var selfUpperCase = this.toUpperCase();
  return selfUpperCase === selfUpperCase.split('').reverse().join('');
};
