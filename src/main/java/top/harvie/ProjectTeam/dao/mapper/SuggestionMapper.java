package top.harvie.ProjectTeam.dao.mapper;

import org.apache.ibatis.annotations.*;
import top.harvie.ProjectTeam.dao.pojo.Suggestion;

import java.io.IOException;
import java.util.List;

public interface SuggestionMapper {
    //增
    @Insert("INSERT INTO suggestion (userid, title, content, img, createtime, status) VALUES (#{userid},#{title},#{content},#{img},#{createtime},#{status})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void add(Suggestion suggestion)throws IOException;

    //删
    @Delete("DELETE FROM suggestion WHERE id=#{id}")
    void delete(Integer id)throws IOException;
    //改
    @Update("UPDATE suggestion SET userid=#{userid}, title=#{title}, content=#{content}, img=#{img}, createtime=#{createtime}, status=#{status} WHERE id=#{id}")
    void update(Suggestion suggestion)throws IOException;
    //查
    @Select( "SELECT * FROM suggestion")
    List<Suggestion> selectAll();

    @Select("SELECT * FROM suggestion WHERE id=#{id}")
    Suggestion select(Integer id);

}
