$(document).ready(function() {

    setiframeSize();

    $(window).on("resize", function(){
           setiframeSize();
    });

});

var setiframeSize = function() {
    //iframe
    var miframe = $("#main_iframe");
    if (miframe.length > 0) {
        miframe.height($(window).height()-60);
    }
}