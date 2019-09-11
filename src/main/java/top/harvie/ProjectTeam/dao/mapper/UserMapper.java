package top.harvie.ProjectTeam.dao.mapper;

import org.apache.ibatis.annotations.*;
import top.harvie.ProjectTeam.dao.pojo.User;

import java.io.IOException;
import java.util.List;

public interface UserMapper {


    //增
    @Insert("INSERT INTO user (img, gender, name, wechatid, profile, position, phone, email, wechatline, location, status) VALUES (#{img},#{gender},#{name},#{wechatid},#{profile},#{position},#{phone},#{email},#{wechatline},#{location},#{status})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void add(User user)throws IOException;

    @Insert("INSERT INTO user (openid) VALUES (#{openid})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void addByOpenId(User user)throws IOException;
    //删
    @Delete("DELETE FROM user WHERE id=#{id}")
    void delete(Integer id)throws IOException;
    //改
    @Update("UPDATE user SET img=#{img}, gender=#{gender}, name=#{name}, wechatid=#{wechatid}, profile=#{profile}, position=#{position}, phone=#{phone}, email=#{email}, wechatline=#{wechatline}, location=#{location}, status=#{status} WHERE id=#{id}")
    void update(User user)throws IOException;
    //查
    @Select( "SELECT * FROM user")
    List<User> selectAll();

    @Select("SELECT * FROM user WHERE id=#{id}")
    User select(Integer id);

    @Select( "SELECT id FROM user WHERE openid=#{openid}")
    Integer selectByOpenId(User user);
}
