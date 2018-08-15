/*  
    http://www.dailycoding.com/ 
    Topbar message plugin
*/
(function ($) {
    $.fn.showTopbarMsg = function (options) {
    	
        var defaults = {
			message: "Sorry, an error occurred while processing your request.",
            background: "#fff300",
            borderColor: "#000",
            fontColor: "#000",
            height: "60px",
            fontSize: "16px",
            close: "click"
        };
        var options = $.extend(defaults, options);

        var barStyle = " width: 100%;position: fixed;height: " + options.height + ";top: 0px;left: 0px;right: 0px;margin: 0px;display: none; background-color: #fff;";
        var overlayStyle = "height: " + options.height + ";filter: alpha(opacity=80);-moz-opacity: 0.8;-khtml-opacity: 0.8;opacity: 0.8;background-color: " + options.background + ";border: solid 1px " + options.borderColor + ";";
        var messageStyle = " width: 100%;position: absolute;height: " + options.height + ";top: 0px;left: 0px;right: 0px;margin: 0px;";
		var messageTextStyle = "color: " + options.fontColor + ";font-family: MuseoSlab-500, Arial, sans-serif;font-weight: bold;font-size: " + options.fontSize + ";text-align: center;padding: 20px 0px;padding-top:20px;text-align:center;";

        return this.each(function () {
            obj = $(this);

            if ($(".topbarBox").length > 0) {
                // Hide already existing bars
                $(".topbarBox").hide()
                $(".topbarBox").slideUp(200, function () {
                    $(".topbarBox").remove();
                });
            }

            var html = ""
                + "<div class='topbarBox' style='" + barStyle + "'>"
                + "  <div style='" + overlayStyle + "'><div style='" + messageTextStyle + "'>" + options.message + "</div></div>"
                + "  <div style='" + messageStyle + "'>" + obj.html() + "</div>"
                + "</div>"

            if (options.close == "click") {
                $(html).click(function () {
                    $(this).slideUp(200, function () {
                        $(this).remove();
                    });
                }).appendTo($('body')).slideDown(200);
            }
            else {
                $(html).appendTo($('body')).slideDown(200).delay(options.close).slideUp(200, function () {
                    $(this).remove();
                });
            }
            //解决ie6不支持postion:fixed问题，leon:0208
			//if($.browser.msie && parseInt($.browser.version)==6){
			//	$('.topbarBox').PositionFixed({x:0,y:0});
			//}
        });
    };
})(jQuery);