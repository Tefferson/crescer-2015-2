$(".form-filtro").submit(function (event) {

    var ordem = $('.ordem')[0];

    if (!!ordem) {
        $('<input />').attr('type', 'hidden')
       .attr('name', "ordem")
       .attr('value', ordem.options[ordem.selectedIndex].value)
       .appendTo('.form-filtro');
    }

    return true;
});