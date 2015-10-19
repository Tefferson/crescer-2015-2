//var clubes = [ { nome: 'Arsenal', titulos: [ { desc: 'Nacionais', qtd: 13 }, { desc: 'Continentais', qtd: 0 }, { desc: 'Mundiais', qtd: 0 } ] }, { nome: 'Manchester United', titulos: [ { desc: 'Nacionais', qtd: 20 }, { desc: 'Continentais', qtd: 3 }, { desc: 'Mundiais', qtd: 2 } ] }, { nome: 'Liverpool', titulos: [ { desc: 'Nacionais', qtd: 18 }, { desc: 'Continentais', qtd: 5 }, { desc: 'Mundiais', qtd: 0 } ] }, { nome: 'Chelsea Football Club', titulos: [ { desc: 'Nacionais', qtd: 5 }, { desc: 'Continentais', qtd: 1 }, { desc: 'Mundiais', qtd: 0 } ] } ];
function ordenaPorNacionais(teams){
	ordenarPorTitulo(0,clubes);
};
function ordenaPorMundiais(teams){
	ordenarPorTitulo(2,clubes);
};
function ordenaPorContinentais(clubes){
	ordenarPorTitulo(1,clubes);
};

function somarPorNacionais(clubes){
	return somarTitulos(0,clubes);
}
function somarPorContinentais(clubes){
	return somarTitulos(1,clubes);
}
function somarPorMundiais(clubes){
	return somarTitulos(2,clubes);
}

function ordenarPorTitulo(tipoTitulo, clubes){
	clubes.sort(
		function(a,b){
			return a.titulos[tipoTitulo].qtd < b.titulos[tipoTitulo].qtd;
		}
	);
};

function somarTitulos(tipoTitulo, clubes){
	var soma=0;
	clubes.forEach(function(clube){
		soma+=clube.titulos[tipoTitulo].qtd;
	});
	return soma;
};

function apenasOsMelhores(clubes){
	return clubes.filter(function(clube){
		return clube.titulos[0].qtd>=18;
	});
}
