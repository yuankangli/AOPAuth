$(function () {
    var item = localStorage.getItem("errorMsg");
    if (item !== "undefined" && item != null) {
        $.errrorTips(item);
        localStorage.removeItem("errorMsg");
    }
});
function login() {
    var checked = checkForm("loginForm");
    if (!checked) {
        return ;
    }
    //判断帐号密码是否正确
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: ROOTURL + "/user/login",//url
        data: $('#loginForm').serialize(),
        success: function (result) {
            // console.log(result);//打印服务端返回的数据(调试用)
            if (result.success === true) {
                // 将登陆讯息持久化到页面上
                var user = result.json.data;
                var userStr = JSON.stringify(user);
                localStorage.setItem("user", userStr);
                //登陆成功,进行页面跳转，如果之前没有访问页面，跳往主页，否则跳往登陆前页面
                var toUrl = localStorage.getItem("currentUrl");
                localStorage.removeItem("currentUrl");
                if(!toUrl){
                    toUrl = "demo1.html";
                }
                window.location.replace(toUrl);
            } else {
                $.errrorTips(result.json.data);
                $("form[name='password']").val("");
            }
        }
    });
}