$(document).ready(function () {
    $(".texto-busca").autocomplete({
        source: '/Relatorio/Autocomplete',
        minLength: 2,
        select: function (event, ui) {
            if (ui.item) {
                if (ui.item.value === '*') {
                    $('.form-filtro').submit();
                } else {
                    location.href = '/Jogo/Detalhes/' + ui.item.value;
                }
            }
        }
    }).data("autocomplete")._renderItem = function (ul, item) {
        var newItem = $("<li>").data("item.autocomplete", item);
        if (item.icon) {
            newItem.append("<img width='50' height=50 src=" + item.icon + " />")
        }
        newItem.append("<a>" + item.label + "</a>")
            .appendTo(ul);

        if (item.value === '*') newItem.addClass('item-ver-tudo');
        return newItem;
    };
});
$.ui.autocomplete.prototype._renderMenu = function (ul, items) {
    var self = this;
    $.each(items, function (index, item) {
        if (index < 5)
        { self._renderItem(ul, item); }
    });
    self._renderItem(ul, { label: 'Ver tudo', value: '*' });
}