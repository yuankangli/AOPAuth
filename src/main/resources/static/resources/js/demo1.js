window.onload=function(){
    // 初始化内容
    // $.successTips("进入页面！");
    myAjax({
        //几个参数需要注意一下
        type: "GET",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: ROOTURL + "/demo1",//url
        success: function (result) {
            var data = result.json.data;
            $("body").append("<h1>"+data+"</h1>")
        }
    });
};
function common() {
    myAjax({
        //几个参数需要注意一下
        type: "GET",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: ROOTURL + "/demo1/common",//url
        success: function (result) {
            var data = result.json.data;
            $.successTips(data);
        }
    });
}
function add() {
    myAjax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: ROOTURL + "/demo1",//url
        success: function (result) {
            var data = result.json.data;
            $.successTips(data);
        }
    });
}
function update() {
    myAjax({
        //几个参数需要注意一下
        type: "PUT",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: ROOTURL + "/demo1",//url
        success: function (result) {
            var data = result.json.data;
            $.successTips(data);
        }
    });
}
function del() {
    myAjax({
        //几个参数需要注意一下
        type: "DELETE",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: ROOTURL + "/demo1",//url
        success: function (result) {
            var data = result.json.data;
            $.successTips(data);
        }
    });
}
function getRoles() {
    var role = ["demo1","demo1:add","demo1:delete","demo1:update"];
    user.roleList = role;
    localStorage.setItem("user", JSON.stringify(user));
    // 刷新页面
    location.reload();
}
function logout() {
    myAjax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: ROOTURL + "/user/logout",//url
        success: function (result) {
            window.location.replace("login.html");
        }
    });
}