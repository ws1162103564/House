package cn.kgc.dao;

import cn.kgc.domain.users;
import cn.kgc.domain.usersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface usersMapper {
    int countByExample(usersExample example);

    int deleteByExample(usersExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(users record);

    int insertSelective(users record);

    List<users> selectByExample(usersExample example);

    users selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") users record, @Param("example") usersExample example);

    int updateByExample(@Param("record") users record, @Param("example") usersExample example);

    int updateByPrimaryKeySelective(users record);

    int updateByPrimaryKey(users record);
}