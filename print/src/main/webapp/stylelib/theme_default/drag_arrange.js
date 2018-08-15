$(function() {
    $(".b-arrange-active .bac-start").on('click', function() {
        var ajaxurl = $(this).attr('href');
        var brrangebox = $(".b-arrange-active");
        var btnhtml = '<a href="#" class="bac-no m-tooltips"  data-placement="bottom" data-original-title="取消排列"><i class="icon-remove"></i><span>取消排列</span></a> <a href="#" class="bac-ok m-tooltips"  data-placement="bottom" data-original-title="保存排列"><i class="icon-ok"></i><span>保存排列</span></a>';
        $(this).remove();
        brrangebox.html(btnhtml);

        var allblack = $(".m-black-col1 a, .m-black-col2 a, .m-black-col3 a, .m-black-col4 a");
        allblack.append('<span class="m-drag-mark"><i class="icon-move"></i></span>');

        $(".b-arrange-active .bac-no").on('click', function() {
            location.reload();
            return false;
        }).tooltip();

        $(".b-arrange-active .bac-ok").on('click', function() {
            var blackmask = '<span class="bac-mask">&nbsp;</span>';
            brrangebox.append(blackmask);

            var blackjson = "";
            var blackstr = "";
            var b1 = $(".m-black-col1 a");
            var b2 = $(".m-black-col2 a");
            var b3 = $(".m-black-col3 a");
            var b4 = $(".m-black-col4 a");

            blackstr = splice_black_json(1,b1);
            blackstr += splice_black_json(2,b2);
            blackstr += splice_black_json(3,b3);
            blackstr += splice_black_json(4,b4);
            blackstr = blackstr.substring(0,blackstr.length-1);
            blackjson = '['+blackstr+']';

            la_open();

            var updatajson = $.ajax({
                url: ajaxurl,
                type: 'POST',
                // async: false,
                //dataType: 'text',
				contentType: 'application/json',
                processData: false,
                data: blackjson,
                success: function(data){
                    //console.log(msg);
                    if (data == "1"){
                        la_msg("完成!");
                        location.reload();
                    }else{
                        la_msg("失败...");
                        $(".b-arrange-active .bac-mask").remove();
                    }
                },
                error: function() {
                    la_msg("网络异常...");
                    $(".b-arrange-active .bac-mask").remove();
                }
            });

            return false;

        }).tooltip();

        $( ".b-metro-nav .metro-black, .b-applist-nav .app-black").sortable({
            connectWith: "li",
            placeholder: "ui-state-black",
            scrollSpeed:0, //拖动到窗口边缘的时候不滚动
            forcePlaceholderSize: true //强制站位块保持和拖拽块大小一致
            //revert: true  //动画
        });
        
        return false;
    });

    $( ".b-metro-nav .metro-black, .b-applist-nav .app-black").disableSelection();
    //$("body").disableSelection();
});

function splice_black_json(groupid, blackobj) {
    //alert(blackobj.length);
    var returnstr = "";
    $.each(blackobj, function(index) {
        returnstr += '{"name":"'+$(this).attr('data-blackname')+'", "groupid":"'+groupid+'", "sort":"'+(index+1)+'"},'
    });
    //returnstr = returnstr.substring(0,returnstr.length-1);
    return returnstr;
}



