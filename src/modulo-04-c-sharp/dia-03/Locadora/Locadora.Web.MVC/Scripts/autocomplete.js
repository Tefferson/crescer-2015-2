$(document).ready(function () {

    var textoBusca = $(".texto-busca").autocomplete({
        source: '/Relatorio/Autocomplete',
        minLength: 2,
        select: function (event, ui) {
            event.preventDefault();
            if (ui.item) {
                if (ui.item.value === '*') {
                    $('.texto-busca').val('');
                    $('.form-filtro').submit();
                } else {
                    location.href = '/Jogo/Detalhes/' + ui.item.value;
                }
            }
        },
        focus: function (event, ui) { event.preventDefault(); $(".texto-busca").val(ui.item.label); }
    });

    textoBusca.data("autocomplete")._renderItem = function (ul, item) {
        var newItem = $("<li>").data("item.autocomplete", item);
        if (item.icon) {
            newItem.append("<img width='50' height=50 src=" + item.icon + " />")
        }
        newItem.append("<a>" + item.label + "</a>")
            .appendTo(ul);

        if (item.value === '*') newItem.addClass('item-ver-tudo');
        return newItem;
    };

    textoBusca.data("autocomplete")._renderMenu = function (ul, items) {
        var self = this;
        console.log(self);
        $.each(items, function (index, item) {
            if (index < 5)
            { self._renderItem(ul, item); }
        });
        self._renderItem(ul, { label: 'Ver tudo', value: '*' });
        $('.ui-menu').addClass("jogo-lista-autocomplete");
    };

    var clienteBusca = $('.cliente-busca').autocomplete({
        source: '/Locacao/Autocomplete',
        minLength: 2,
        select: function (event, ui) {
            event.preventDefault();
            if (ui.item) {
                location.href = '/Jogo/Detalhes/' + ui.item.value;
            }
        },
        focus: function (event, ui) { event.preventDefault(); $(".cliente-busca").val(ui.item.label); }
    });

    clienteBusca.data("autocomplete")._renderMenu = function (ul, items) {
        var self = this;
        $.each(items, function (index, item) {
            if (index < 5)
            { self._renderItem(ul, item); }
        });
        $('.ui-menu').addClass("cliente-lista-autocomplete");
    };
});