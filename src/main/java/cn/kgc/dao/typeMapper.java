package cn.kgc.dao;

import cn.kgc.domain.type;
import cn.kgc.domain.typeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface typeMapper {
    int countByExample(typeExample example);

    int deleteByExample(typeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(type record);

    int insertSelective(type record);

    List<type> selectByExample(typeExample example);

    type selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") type record, @Param("example") typeExample example);

    int updateByExample(@Param("record") type record, @Param("example") typeExample example);

    int updateByPrimaryKeySelective(type record);

    int updateByPrimaryKey(type record);

    @Select("select * from type")
    List<type> selectAlltype();

    //批量删除
    int deltypeById(Integer[] ids);
}