package top.harvie.ProjectTeam.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import top.harvie.ProjectTeam.conf.Tools;
import top.harvie.ProjectTeam.dao.pojo.Suggestion;
import top.harvie.ProjectTeam.service.SuggestionService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/api")
@Api(value="Suggestion",description = "用户建议接口")
public class SuggestionController {

    @Autowired
    SuggestionService suggestionService;
    
    @PostMapping(value = "/suggestion")
    @ApiOperation(value="用于增加用户建议条目",notes="用于增加用户建议条目")
    public Map add(
            @ApiParam(required = true,name="suggestion",value = "用户建议信息(不需要填入ID,createtime,status)") @RequestBody Suggestion suggestion
    ){
        Map<String,Object> response=new HashMap<>();
        try{
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            suggestion.setCreatetime(df.format(new Date()));
            suggestion.setStatus("1");
            suggestionService.add(suggestion);
            response.put("status","success");
            response.put("massage",null);
            response.put("data",suggestion);
            return response;
        }catch (Exception e){
            response.put("status","error");
            response.put("massage",e.getMessage());
            return response;
        }
    }

    //根据ID删除一条用户建议信息
    @PostMapping(value = "/suggestion/delete")
    @ApiOperation(value="根据ID删除指定用户建议条目",notes="根据ID删除指定用户建议条目")
    public Map delete(
            @ApiParam(required = true,name="id",value = "需要删除的用户建议的ID") @RequestParam("id") Integer id
    ){
        Map<Object,Object> response=new HashMap<>();
        try{
            suggestionService.delete(id);
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

    //更新用户建议信息
    @PostMapping(value = "/suggestion/update")
    @ApiOperation(value="根据ID更新指定用户建议条目",notes="根据ID更新指定用户建议条目")
    public Map update(
            @ApiParam(required = true,name="suggestion",value = "需要更新的用户信息(ID必填)") @RequestBody Suggestion suggestion
    ){
        Map<Object,Object> response=new HashMap<>();
        try{
            Integer id=suggestion.getId();
            Suggestion updateUser=suggestionService.select(id);
            Tools.update(updateUser,suggestion);
            suggestionService.update(updateUser);
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
    @GetMapping(value = "/suggestion/all")
    @ApiOperation(value="询所有用户建议信息",notes="询所有用户建议信息")
    public Map select(){
        Map<Object,Object> response=new HashMap<>();
        try{
            List<Suggestion> userList=suggestionService.select();
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

    @GetMapping(value = "/suggestion")
    @ApiOperation(value="查询指定ID用户建议信息",notes="查询指定ID用户建议信息")
    public Map selectById(
            @ApiParam(required = true,name="id",value = "需要查询的用户的ID") @RequestParam("id") Integer id
    ){
        Map<Object,Object> response=new HashMap<>();
        try{
            Suggestion suggestion=suggestionService.select(id);
            response.put("status","success");
            response.put("massage",null);
            response.put("data",suggestion);
            return response;
        }catch (Exception e){
            response.put("status","error");
            response.put("massage",e.getMessage());
            return response;
        }
    }
    
    
}
