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
`http://localhost:8080` 查看效果(密码全部都为123456)

#### 使用说明
 以 demo1:add 权限进行说明  
1. 后端权限  
在 Controller 上加注解  
`@RequireAtuh("demo1:add")`  
2. 前端控件权限  
控件属性添加  
`requireAuth="demo1:add" `   

#### 参与贡献

1. Fork 本项目
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request


#### 码云特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. 码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5. 码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6. 码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)