$(document).ready(function() {

  $( function() {
    var min = $( "#amount_min" ).val();
    var max = $( "#amount_max" ).val();

    $( "#slider-range" ).slider({
      range: true,
      min: 0,
      max: 300,
      values: [ min, max],
      slide: function( event, ui ) {
        min = ui.values[ 0 ];
        max = ui.values[ 1 ];

        $("#amount_min").val(min);
        $("#amount_max").val(max);
      }
    });

     $( "#amount_min" ).val($( "#slider-range" ).slider( "values", 0));
     $( "#amount_max" ).val($( "#slider-range" ).slider( "values", 1));

  } );
});