package top.harvie.ProjectTeam.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import top.harvie.ProjectTeam.conf.Tools;
import top.harvie.ProjectTeam.dao.pojo.User;
import top.harvie.ProjectTeam.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/api")
@Api(value="User",description = "用户接口")
public class UserController {


    @Autowired
    UserService userService;

    //增加一条用户条目
    @PostMapping(value = "/user")
    @ApiOperation(value="用于增加用户条目",notes="用于增加用户条目")
    public Map add(
            @ApiParam(required = true,name="user",value = "用户信息(不需要填入ID)") @RequestBody User user
    ){
        Map<String,Object> response=new HashMap<>();
        try{
            userService.add(user);
            response.put("status","success");
            response.put("massage",null);
            response.put("data",user);
            return response;
        }catch (Exception e){
            response.put("status","error");
            response.put("massage",e.getMessage());
            return response;
        }
    }

    //根据ID删除一条用户信息
    @PostMapping(value = "/user/delete")
    @ApiOperation(value="根据ID删除指定用户条目",notes="根据ID删除指定用户条目")
    public Map delete(
            @ApiParam(required = true,name="id",value = "需要删除的用户的ID") @RequestParam("id") Integer id
    ){
        Map<Object,Object> response=new HashMap<>();
        try{
            userService.delete(id);
            response.put("status","success");
            response.put("massage",null);
            response.put("data",null);
            return response;
        }catch (Exception e){
            response.put("status","error");
            response.put("massage",e.getMessage());
            return response;
        }
    }

    //更新用户信息
    @PostMapping(value = "/user/update")
    @ApiOperation(value="根据ID更新指定用户条目",notes="根据ID更新指定用户条目")
    public Map update(
            @ApiParam(required = true,name="user",value = "需要更新的用户信息(ID必填)") @RequestBody User user
    ){
        Map<Object,Object> response=new HashMap<>();
        try{
            Integer id=user.getId();
            User updateUser=userService.select(id);
            Tools.update(updateUser,user);
            userService.update(updateUser);
            response.put("status","success");
            response.put("massage",null);
            response.put("data",updateUser);
            return response;
        }catch (Exception e){
            response.put("status","error");
            response.put("massage",e.getMessage());
            return response;
        }
    }

    //查询所有用户信息
    @GetMapping(value = "/user/all")
    @ApiOperation(value="询所有用户信息",notes="询所有用户信息")
    public Map select(){
        Map<Object,Object> response=new HashMap<>();
        try{
            List<User> userList=userService.select();
            response.put("status","success");
            response.put("massage",null);
            response.put("data",userList);
            return response;
        }catch (Exception e){
            response.put("status","error");
            response.put("massage",e.getMessage());
            return response;
        }
    }

    @GetMapping(value = "/user")
    @ApiOperation(value="查询指定ID用户信息",notes="查询指定ID用户信息")
    public Map selectById(
            @ApiParam(required = true,name="id",value = "需要查询的用户的ID") @RequestParam("id") Integer id
    ){
        Map<Object,Object> response=new HashMap<>();
        try{
            User user=userService.select(id);
            response.put("status","success");
            response.put("massage",null);
            response.put("data",user);
            return response;
        }catch (Exception e){
            response.put("status","error");
            response.put("massage",e.getMessage());
            return response;
        }
    }

    //小程序用户登录信息
    @PostMapping(value = "/user/code")
    @ApiOperation(value="小程序用户登录信息",notes="小程序用户登录信息")
    public Map code(
            @ApiParam(required = true,name="code",value = "登录时获取的 code") @RequestParam("code") String code
    ){
        Map<Object,Object> response=new HashMap<>();
        try{
            String userId=userService.code(code);
            response.put("status","success");
            response.put("massage",null);
            response.put("userId",userId);
            return response;
        }catch (Exception e){
            response.put("status","error");
            response.put("massage",e.getMessage());
            return response;
        }
    }





}
