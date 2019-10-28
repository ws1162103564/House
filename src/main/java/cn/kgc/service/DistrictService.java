package cn.kgc.service;

import cn.kgc.domain.district;
import cn.kgc.utils.pageUtils;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface DistrictService {

    List<district> selectAllDistrict();

    //分页
    PageInfo<district> fenyeByPage(pageUtils pageUtils);

    //插入
    int insertByName(district district);

    //根据id查询name
    district selectByPrimaryKey(Integer id);

    //修改
    int updateByPrimaryKeySelective(district record);

    //删除
    int deleteByPrimaryKey(Integer id);

    //批量删除
    int delMoreDistrict(Integer[] ids);
}
