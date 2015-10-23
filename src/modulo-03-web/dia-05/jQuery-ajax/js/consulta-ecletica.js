$('body:eq(0)').css({'text-align':'center'});
$('ul:eq(0)').css({'list-style':'none'});
$('#submit').click(consultarArtista);

function consultarArtista(){
  var nomeAConsultar = $('#artista').val().split(" ").join("+");
  $('ul:eq(0)').empty();
  nomeAConsultar.length &&
  $.get('https://api.spotify.com/v1/search?q='+nomeAConsultar+'&type=artist').done(function(data) {
    data.artists.items.length &&
    consultarAlbuns(data.artists.items[0].id);
  });
};

function consultarAlbuns(idArtista){
  $.get('https://api.spotify.com/v1/artists/'+idArtista+'/albums?limit=50')
  .done(function(data) {
    var albuns = data.items;
    albuns.forEach(function(elem) {
      elem.images.length &&
      $('.albuns').append(
        $('<li>')
        .append($('<img>')
        .attr('src', elem.images[1].url))
      )
    });
  });
};
