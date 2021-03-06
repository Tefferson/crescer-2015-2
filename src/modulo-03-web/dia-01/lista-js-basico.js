function isFunction(fn){
  return typeof fn === 'function';
}

function isNumber(number){
  return typeof number === 'number';
}

function daisyGame(petals) {
  if(isNumber(petals)){
    if(!(petals%2)) return 'Love me not';
    return 'Love me';
  }else{
    throw new Error('Not a number');
  }
};

function maiorTexto(arr) {
  var maior = '';
  for(var i=0, len = arr.length;i<len;i++){
    if(arr[i].length>maior.length) maior = arr[i];
  }
  return maior;
};

function imprime(arr, func) {
  if(isFunction(func)) arr.forEach(function(elem,i){func(elem);});
};

function fiboSum(n){
  if(!isNaN(n)){
    if(n>0){
      var phi = 1.618033988749895;
      var sqrtOf5 = Math.sqrt(5);
      n+=2;
      return Math.round(Math.pow(phi,n)/sqrtOf5)-1;
    }
  }
  return 0;
};

function excelis(ref){
  if(typeof ref === 'string'){
    if(!ref.search(/^[A-Z]+$/)){
      var col = 0;
      for(var i=0;i<ref.length;i++){
        var charCode = ref.charCodeAt(i)-64;
        col=col*26 + charCode;
      }
      return col;
    }
  }
};

function queroCafe(mascada, precos){
  return precos.filter(function(elem){return elem<=mascada;})
  .sort(function(a,b){return a>b;})
  .join();
};
