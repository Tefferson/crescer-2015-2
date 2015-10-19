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
  var idx;
  var maior = '';
  for(prop in arr){
    if(typeof arr[prop] === 'string'){
      if(arr[prop].length>maior.length){
        idx = prop;
        maior = arr[idx];
      }
    }
  }
  return arr[idx];
};

function imprime(arr, func) {
  if(typeof func === 'function') for(prop in arr) func(arr[prop]);
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
  var lista="";
  precos.sort(function(a,b){return a>b;});
  for(var i=0; i<precos.length; i++){
    if(precos[i]>mascada) { return lista.substring(0,lista.length-1)};
    lista += precos[i]+',';
  }
};
