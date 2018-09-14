
var ROOTURL = "";
//定义消息提示插件
(function() {
    var $;
    $ = jQuery;
    $.tips = function(message, status, delay,width) {
        delay = delay||3000;
        width = width||"100%";
        $.bootstrapGrowl(message, {
            type: status,
            offset: {
                from: "top",
                amount: 20
            },
            align: 'center',
            delay:delay,
            width:width
            // allow_dismiss: false//是否显示关闭按钮
        });
    };
    $.errrorTips = function(message,delay) {
        delay = delay || 10000;
        $.tips(message,"warning",delay,"100%");
    };
    $.successTips = function(message,delay) {
        $.tips(message,"success",delay);
    };
    $.infoTips = function(message,delay) {
        $.tips(message,"info",delay);
    };
    $.confirm = function (message,rollBackFunction) {
        bootbox.confirm(message,function (result) {
            if(!result){
                return true;
            }
            rollBackFunction();
        });
    }

}).call(this);

/**
 * 展示有权限的控件
 */
function hideAuthDom() {
    var userAuth = user.roleList;
    $("[requireauth]").each(function (index, element) {
        var requireAuth = $(this).attr("requireauth");
        // 判断当前用户角色是否有相应权限
        if ($.inArray(requireAuth,userAuth)<0) {
            $(this).remove();
        } else {
            $(this).removeAttr("requireauth");
        }
    });
}
/**
 * 注册 ajax 全局事件, 用於展示等待頁面
 */
$(document).ajaxSend(function(event, request, settings) {
    $("body").append("<div id=\"loading\">\n" +
        "    <div id=\"loading-center\">\n" +
        "        <div id=\"loading-center-absolute\">\n" +
        "            <div class=\"object\" id=\"first_object\"></div>\n" +
        "            <div class=\"object\" id=\"second_object\"></div>\n" +
        "            <div class=\"object\" id=\"third_object\"></div>\n" +
        "        </div>\n" +
        "    </div>\n" +
        "</div>");
});
$(document).ajaxStop(function(event, xhr, settings) {
    $("#loading").remove();
});
$(document).ajaxSend(function(event,xhr,options) {
    // $.successTips(options);
});
/**
 * 注册 ajax 全局事件 结束
 */
