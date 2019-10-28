package cn.kgc.dao;

import cn.kgc.domain.street;
import cn.kgc.domain.streetExample;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;

public interface streetMapper {
    int countByExample(streetExample example);

    int deleteByExample(streetExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(street record);

    int insertSelective(street record);

    List<street> selectByExample(streetExample example);

    street selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") street record, @Param("example") streetExample example);

    int updateByExample(@Param("record") street record, @Param("example") streetExample example);

    int updateByPrimaryKeySelective(street record);

    int updateByPrimaryKey(street record);


    @Delete("delete from street where district_id=#{id}")
    int delbydid(Integer id);
}