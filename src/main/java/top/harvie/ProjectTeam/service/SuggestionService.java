package top.harvie.ProjectTeam.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import top.harvie.ProjectTeam.dao.mapper.SuggestionMapper;
import top.harvie.ProjectTeam.dao.pojo.Suggestion;

import java.util.List;

@Service
public class SuggestionService {

    private static Logger log = LogManager.getLogger(SuggestionService.class);

    @Autowired
    SuggestionMapper suggestionMapper;

    //增
    public Integer add(Suggestion suggestion) throws Exception
    {
        suggestionMapper.add(suggestion);
        return suggestion.getId();
    }
    //删
    public void delete(Integer id) throws Exception
    {
        suggestionMapper.delete(id);
    }
    //改
    public void update(Suggestion suggestion) throws Exception
    {
        suggestionMapper.update(suggestion);
    }
    //查
    public Suggestion select(Integer id)
    {
        Suggestion suggestion=suggestionMapper.select(id);
        return suggestion;
    }
    public List<Suggestion> select()
    {
        List<Suggestion> suggestionList=suggestionMapper.selectAll();
        return suggestionList;
    }
}
