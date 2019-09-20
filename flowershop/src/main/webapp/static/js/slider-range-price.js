$(document).ready(function() {

  $( function() {
    var min = $( "#price_min" ).val();
    var max = $( "#price_max" ).val();

    $( "#slider-range" ).slider({
      range: true,
      min: 0,
      max: 300,
      values: [ min, max],
      slide: function( event, ui ) {
        min = ui.values[ 0 ];
        max = ui.values[ 1 ];

        $("#price_min").val(min);
        $("#price_max").val(max);
      }
    });

     $( "#price_min" ).val($( "#slider-range" ).slider( "values", 0));
     $( "#price_max" ).val($( "#slider-range" ).slider( "values", 1));

  } );
});