package top.harvie.ProjectTeam.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.spring.web.json.Json;
import top.harvie.ProjectTeam.dao.mapper.UserMapper;
import top.harvie.ProjectTeam.dao.pojo.User;

import java.util.List;
import java.util.Map;

@Service
@CacheConfig(cacheNames = "user")
public class UserService {

    private static Logger log = LogManager.getLogger(UserService.class);

    @Autowired
    UserMapper userMapper;

    @Autowired
    RestTemplate restTemplate;

    //增
    @CacheEvict(key="'UserCache'")
    public Integer add(User user) throws Exception
    {
        userMapper.add(user);
        return user.getId();
    }
    //小程序用户信息获取
    public String code(String code) throws Exception
    {
        String url="https://api.weixin.qq.com/sns/jscode2session?appid=wxb66076f3be08489c&secret=d309b5cdc5f3f4330d53c58f58b5c085&js_code="+code+"&grant_type=authorization_code";
        log.info("发送的url请求为：",url);
        String response=restTemplate.getForObject(url, String.class);
        JSONObject json= JSON.parseObject(response);
        String openId=json.getString("openid");

        User user = new User();
        user.setOpenid(openId);

        Integer id=userMapper.selectByOpenId(user);
        log.info("查询到的id"+id);
        if(id==null){
            //数据库无用户openid 则插入返回新id
            userMapper.addByOpenId(user);
            return user.getId().toString();
        }else{
            return id.toString();
        }

    }
    //删
    @CacheEvict(key="'UserCache'")
    public void delete(Integer id) throws Exception
    {
        userMapper.delete(id);
    }
    //改
    @CacheEvict(key="'UserCache'")
    public void update(User user) throws Exception
    {
        userMapper.update(user);
    }
    //查
    public User select(Integer id)
    {
        User user=userMapper.select(id);
        return user;
    }

    @Cacheable(key="'UserCache'")
    public List<User> select()
    {
        List<User> navigationList=userMapper.selectAll();
        return navigationList;
    }

}

