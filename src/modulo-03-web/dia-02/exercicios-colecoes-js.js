function ordenaPorNacionais(clubes){
	return ordenarPorTitulo(0,clubes);
};
function ordenaPorContinentais(clubes){
	return ordenarPorTitulo(1,clubes);
};
function ordenaPorMundiais(clubes){
	return ordenarPorTitulo(2,clubes);
};

function somarPorNacionais(clubes){
	return somarTitulos(0,clubes);
};
function somarPorContinentais(clubes){
	return somarTitulos(1,clubes);
};
function somarPorTodosOsTitulos(clubes){
	return somarTitulos(0,clubes)+somarTitulos(1,clubes)+somarTitulos(2,clubes);
};

function ordenarPorTitulo(tipoTitulo, clubes){
	return clubes.sort(function(a,b){
		return a.titulos[tipoTitulo].qtd < b.titulos[tipoTitulo].qtd;
	});
};

function somarTitulos(tipoTitulo, clubes){
	return clubes.reduce(function(acumulador,elemento){
		return acumulador+elemento.titulos[tipoTitulo].qtd}, 0);
	};

	function apenasOsMelhores(clubes){
		return clubes.filter(function(e){
			return e.titulos[0].qtd>18;
		});
	};
