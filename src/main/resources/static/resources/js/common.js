
var ROOTURL = "";
var user = JSON.parse(localStorage.getItem("user"));
/**
 * 表单校验使用html5的表单校验
 * 在input中使用,例如
 * 1.required  --非空,<input type="text" name="myText" required>
 * 2.使用type = numbe、email、URL等  --<input type="email" name="myEmail">
 * 3.pattern    --使用正则,<input type="text" name="creditcardnumber" pattern="[0-9]{16}">
 * 4.maxLength="140"    --限制最大长度<input type="text" name="limitedText" maxLength="140">
 * 5.max="12"   --限制最大值<input type="range" name="ageCheck" max="18">
 * 6.min="1"    --限制最小值<input type="range" name="ageCheck" min="18">
 * 7.stepMismatch   --限制最大值最小值的范围,并要求数值在step中
 *      <input type="range" name="confidenceLevel" min="0" max="100" step="5">
 *      要求0.5.10....95,100
 * 8.customError    --inputField.setCustomValidity("Password values do not match.");
 *      --返回空则说明自定义匹配没问题,否则校验不通过
 *
 * 以上返回的提示均需要自己手动填写
 */
$(function () {
    //查找页面上所有的form表单,在离开焦点后进行表单校验
    activValid();
    // 根据当前登录用户,判断页面控件权限
    hideAuthDom();
    /***
     * <!-- confirm 弹窗组件-->
     <script src="../../../bootstrapExtrends/js/bootbox.min.js"></script>
     <!--提示消息组件-->
     <script src="../../../bootstrapExtrends/js/jquery.bootstrap-growl.js"></script>
     <!--本地化组件-->
     <script src="../../../jquery-localize/jquery.localize.js"></script>
     */
});
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
        $.tips(message,"danger",delay,"100%");
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
    $.alert = function (message) {
        bootbox.alert(message);
    }
}).call(this);
/**
 * 激活 form 表单离开焦点校验事件
 */
function activValid() {
    $("form").each(function () {
        // <input>, <select>, <textarea>
        $(this).find("input,select,textarea").each(function () {
            var _this = $(this);
            _this.on("blur", function () {
                var _parent = _this.parent("div");
                //给父元素添加 was-validated class
                _parent.addClass("was-validated");
            });
        });
    });
}
/**
 * 表单校验, sumbit按钮会自动在提交时校验
 * @param formId 要检验的表格id
 * @returns {boolean}
 */
function checkForm(formId) {
    var _form;
    if(formId instanceof jQuery){
        _form = formId;
    }else{
        _form = $("#"+formId);
    }
    if(_form.length<=0){
        return true;
    }
    var validity = _form[0].checkValidity();
    if(validity){
        return true;
    } else {
        //校验全部字段
        _form.addClass("was-validated");
        return false;
    }
}
/**
 * 展示有权限的控件
 */
function hideAuthDom() {
    if (user === null) {
        return;
    }
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
 * 注册 ajax 全局事件, 主要用於展示等待頁面和一些公有事件处理
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
// 由于该处 success 事件在方法的 success 后执行,无法提前处理 一些异常,故重写了ajax方法
$(document).ajaxSuccess(function(event,xhr,options) {
    // var response = xhr.responseJSON;
});
$( document ).ajaxError(function( event, jqxhr, settings, thrownError ) {
    $.errrorTips("服务器连接异常，请联系管理员")
});
/**
 * 注册 ajax 全局事件 结束
 */

function myAjax(setting) {
    //如果请求参数中出现success方法,接管该方法
    if (typeof setting.success === "function") {
        var successFunction = setting.success;
        setting.success = function (response) {
            // 错误代码为901 时,需要返回登录页面
            if (response.state === 901) {
                window.location.replace("login.html");
                localStorage.setItem("errorMsg", response.json.data);
                return ;
            }
            // 错误代码为902 时,禁用后续事件
            if (response.state === 902) {
                $.errrorTips(response.json.data);
                return ;
            }
            successFunction(response);
        }
    }
    // setting.success =
    $.ajax(setting);
}