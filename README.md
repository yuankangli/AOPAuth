# AOPAuth

#### 项目介绍
用AOP制作一个简单的权限管理功能

#### 软件架构
使用mysql数据库 + SpringBoot2.0 + AOP功能做的一个简易版权限管理功能


#### 安装教程

1. 修改数据库连接为自己本地数据库,路径为 :  
`src\main\resources\application.yml`
2. 导入数据库内容,文件路径为 :   
`sql\aopauth.sql`
3. 运行 `com.liyk.AOPAuth.AopAuthApplication` 文件,即可访问  
`http://localhost:8080` 查看效果  
(账号:admin,test1,test2  密码全部都为123456)

#### 使用说明
 以 demo1:add 权限进行说明  
1. 后端权限  
在 Controller 上加注解  
`@RequireAtuh("demo1:add")`  
2. 前端控件权限  
控件属性添加  
`requireAuth="demo1:add" `   
