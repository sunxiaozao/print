$(document).ready(function() {
    //alert('111111');

    $('body').iealert({
        support: "ie6",
        title: "您是否注意到您的IE浏览器版本过低？",
        text: "为了更好的浏览和操作体验，我们建议您升级您的浏览器到IE8以上或使用第三方浏览器 <a href='http://firefox.com.cn/'>FireFox</a> 和 <a href='https://www.google.com/intl/zh-CN/chrome/browser/'>Chrome</a>.。要下载一个新的浏览器，请点击“下载”按钮。",
        upgradeTitle: "下载",
        upgradeLink: "http://windows.microsoft.com/zh-cn/windows/upgrade-your-browser"
    });

    //根据浏览器宽度给相应的组件加上class
    isbrowserSize();
        
    //关闭AJAX相应的缓存
    $.ajaxSetup ({ cache: false });

    //系统选择
    var isSystemMenu = false;
    $(".b-select-system .bss-btn").click(function() {
        var bssmenu = $(this).next();
        var bss = $(this).parent();
        if (bssmenu.is(":visible")) {
            bss.removeClass("open");
            bssmenu.slideUp(200);
            isSystemMenu = false;
        }else{
            bss.addClass("open");
            bssmenu.slideDown(200);
            isSystemMenu = true;
        }
        return false;
        //event.stopPropagation();
    });

    //导航 (二级菜单的显示与隐藏)
    $(".b-sidebar-menu .bsm-item > a.bsmi-btn").click(function() {
        //关闭所有已打开的下级菜单
        $(".b-sidebar-menu .bsm-item.open").removeClass("open");
        $(".b-sidebar-menu .bsm-item .bsmi-sub").slideUp(200);
        //打开已点击栏目的下级菜单
        var sub = $(this).next();
        if (sub.is(":visible")) {
            $(this).parent().removeClass("open");
            sub.slideUp(200);
        }else{
            $(this).parent().addClass("open");
            sub.slideDown(500);
        }
        return false;
    });

    //点击空白处执行
    $(document).click(function() {
        //收起系统选择菜单
        if (isSystemMenu){
            $(".b-select-system").removeClass("open");
            $(".b-select-system .bss-menu").slideUp(100);
            //alert("2") 
        }
    });

    //m-cbox缩放内容
    $(".m-cbox .mcht-zoom-btn").click(function() {
        //var mczoombtn = $(this);
        var mczoomicon = $(this).find("i");
        var mcbody = $(this).parent().parent().next();
        if (mcbody.is(":visible")) {
            mcbody.slideUp(200);
            mczoomicon.attr("class","icon-chevron-up");
        }else{
            mcbody.slideDown(200);
            mczoomicon.attr("class","icon-chevron-down");
        }
        return false;
    });

    //m-cbox 删除
    $(".m-cbox .mcht-del-cbox-btn").click(function() {
        var cbox = $(this).parent().parent().parent();
        var isurl = $(this).attr("href");
        if (isurl=== "" || isurl === "#") {
            cbox.fadeOut("fast",function() {
                cbox.remove();
            });
            //cbox.remove();
        }else{
            alert("ajax 访问url地址，处理返回数据,删除href里的URL地址,再点击删除会直接删除这个CBox");
            // $.ajax({
            //     url: isurl,
            //     type: 'GET',
            //     //dataType: 'default: Intelligent Guess (Other values: xml, json, script, or html)',
            //     //data: {param1: 'value1'},
            // })
            // .done(function() {
            //     console.log("success");
            // })
            // .fail(function() {
            //     console.log("error");
            // })
            // .always(function() {
            //     console.log("complete");
            // });
        }
        return false;
    });


    // custom scrollbar
    $(".b-sidebar-scroll, .b-select-system .bss-menu").niceScroll({styler:"fb",cursorcolor:"#999", cursorwidth: '5', background: '#f0f0f0', cursorborderradius: '0', cursorborder: ''});

    $("html").niceScroll({styler:"fb",cursorcolor:"#3399FF", cursorwidth: '8', cursorborderradius: '0', background: '#f0f0f0', cursorborder: '', zindex: '1031'});


    //star rating 
    $(".m-rating .star").click(function() {
        var numstar = $(this).attr("data-star");
        var srurl = $(this).parent().attr("data-geturl")+numstar;
        alert(srurl);
        // $.ajax({
        //     url: srurl,
        //     type: 'GET',
        //     //dataType: 'default: Intelligent Guess (Other values: xml, json, script, or html)',
        //     //data: {param1: 'value1'},
        // })
        // .done(function() {
        //     console.log("success");
        // })
        // .fail(function() {
        //     console.log("error");
        // })
        // .always(function() {
        //     console.log("complete");

        // });
    });

    //tool tips
    $('.m-element').tooltip();
    $('.m-tooltips').tooltip();

    //popovers
    $('.m-popovers').popover();

    //scroller
    $('.m-scroller').slimscroll({
        height: 'auto'
    });


    //checkbox all select
    $('.js-all-select').on("click", function() {
        var rangeName = $(this).attr("data-range-name");
        if (rangeName) {
            $(rangeName+" input:checkbox").prop("checked",this.checked);
        }else{
            $("input:checkbox").prop("checked",this.checked);
        }
    });


    //raty
    if ($('.js-raty').length > 0){
        $('.js-raty').raty({
            path:'stylelib/plugins/raty/img/',
            score: function() {
                return $(this).attr('data-score');
            },
            click: function(score, evt) {
                alert($(this).attr('date-url') + "\nscore: " + score);
            }
        });
    }

});




/*-------------------------------------------------------0
* Resize Action
*/
$(window).bind("resize", function()
{
       //function
       isbrowserSize();
});


/*-------------------------------------------------------
* Custom Function
*/
var isbrowserSize = function() {
    var pageWidth = $("body").width();
    if ( pageWidth <= 767 ) {
        $(".b-top-nav .top-menu .dropdown-menu").addClass('m-max767');
        //.niceScroll({styler:"fb",cursorcolor:"#0066cc", cursorwidth: '5', cursorborderradius: '0', cursorborder: ''});
    }
    if (pageWidth >= 980) {
        var zoombtn = $(".m-cbox .mc-head .mch-smenu-btn");
        var sidebar = $("#main-menu");
        var maincontent = $("#main-content");
        var paggeactions = $(".b-page-actions")
        zoombtn.click(function() {
            if (sidebar.is(":visible")) {
                sidebar.css("display","none");
                maincontent.css("margin-left","0");
                paggeactions.css("margin-left","0");
            }else{
                sidebar.css("display","");
                maincontent.css("margin-left","");
                paggeactions.css("margin-left","");
            }
        });
    }
}


