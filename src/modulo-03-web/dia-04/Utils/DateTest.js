// Ano 2000 é bissexto
console.assert(new Date(2000,1,1).bissexto(), 'Ano 2000 é bissexto!!!');

//Ano 2015 não é bissexto
console.assert(!new Date(2015,1,1).bissexto(), 'Ano 2015 não é bissexto!!!');
