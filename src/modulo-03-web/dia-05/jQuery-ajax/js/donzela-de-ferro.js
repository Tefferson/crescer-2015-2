$('ul:eq(0)').css({'list-style':'none','text-align':'center'});

$.get('https://api.spotify.com/v1/artists/6mdiAmATAx73kdxrNrnlao/albums?limit=50').done(function(data) {
  var albuns = data.items;
  albuns.forEach(function(elem) {
    $('.albuns').append(
      $('<li>')
      .append($('<img>')
      .attr('src', elem.images[1].url))
    )
  });
});
