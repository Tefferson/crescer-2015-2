function ordenaPorNacionais(clubes){
	ordenarPorTitulo(0,clubes);
};
function ordenaPorMundiais(clubes){
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
		return clube.titulos[0].qtd>18;
	});
}
