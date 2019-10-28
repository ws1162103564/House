package cn.kgc.dao;

import cn.kgc.domain.district;
import cn.kgc.domain.districtExample;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface districtMapper {
    int countByExample(districtExample example);

    int deleteByExample(districtExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(district record);

    int insertSelective(district record);

    List<district> selectByExample(districtExample example);

    district selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") district record, @Param("example") districtExample example);

    int updateByExample(@Param("record") district record, @Param("example") districtExample example);

    int updateByPrimaryKeySelective(district record);

    int updateByPrimaryKey(district record);


    @Select("select * from district")
    List<district> selectAllDistrict();

    int delDistrictById(Integer[] ids);
}