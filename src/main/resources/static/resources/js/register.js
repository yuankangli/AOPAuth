/**
 * Created by Administrator on 2018-08-13.
 */
onload = function() {
    /*
        注册验证两次密码是否正确的判断
     */
    var p2 = document.forms["registerForm"].password_confirm;
    p2.oninput = function() {
        checkPwd(this);
    }
};
function backToLogin() {
    // 返回登录页面
    window.location.replace("login.html");
}
function checkPwd(obj) {
    var p1 = document.forms["registerForm"].password;
    var p1Value = p1.value;
    var p2Value = obj.value;
    if (p1Value === p2Value) {
        obj.setCustomValidity("");
    } else {
        obj.setCustomValidity("两次密码输入不一致");
    }
}
function register () {
    // 禁用註冊按鈕
    $(".form ")
    var checked = checkForm("registerForm");
    if (!checked) {
        return ;
    }
    // 注册账号
    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: ROOTURL + "/user",//url
        data: $('#registerForm').serialize(),
        success: function (result) {
            // console.log(result);//打印服务端返回的数据(调试用)
            if (result.success === true) {
                $.successTips("注册成功,即将跳转首页")
                //登陆成功,进行页面跳转
                //手机页面登录其他网址
                let toUrl = localStorage.getItem("currentUrl");
                localStorage.removeItem("currentUrl");
                if(!toUrl){
                    toUrl = "demo1.html";
                }
                window.location.replace(toUrl);
            } else {
                $.errrorTips(result.json.data);
            }
        },
        complete: function (result) {

        }
    });
}