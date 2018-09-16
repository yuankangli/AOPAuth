package com.liyk.AOPAuth.controller;


import com.liyk.AOPAuth.framework.common.annotation.RequireAtuh;
import com.liyk.AOPAuth.framework.common.result.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("demo1")
public class Demo1Controller {


    @GetMapping("/common")
    public Result getCommon(){
        return new Result("公有内容");
    }

//    @RequireAtuh("demo1:query")
    @GetMapping
    public Result getAll(){
        return new Result("数据列表");
    }

    @RequireAtuh("demo1:add")
    @PostMapping
    public Result add(){
        return new Result("新增成功");
    }

    @RequireAtuh("demo1:update")
    @PutMapping
    public Result update(){
        return new Result("更新成功");
    }
    @RequireAtuh("demo1:delete")
    @DeleteMapping
    public Result delete(){
        return new Result("删除成功");
    }


}
