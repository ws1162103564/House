package cn.kgc.service;

import cn.kgc.domain.type;
import cn.kgc.utils.pageUtils;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface typeService {

    List<type> selectAlltype();

    //分页
    PageInfo<type> fenyeByPage(pageUtils pageUtils);

    //插入
    int insertByName(type type);

    //根据id查询name
    type selectByPrimaryKey(Integer id);

    //修改
    int updateByPrimaryKeySelective(type record);

    //删除
    int deleteByPrimaryKey(Integer id);

    //批量删除
    int delMoretype(Integer[] ids);
}
