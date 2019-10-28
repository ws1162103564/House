package cn.kgc.dao;

import cn.kgc.domain.Exhouse;
import cn.kgc.domain.house;
import cn.kgc.domain.houseExample;
import java.util.List;
import java.util.Map;

import cn.kgc.domain.HouseCondition;
import org.apache.ibatis.annotations.Param;

public interface houseMapper {
    int countByExample(houseExample example);

    int deleteByExample(houseExample example);

    int deleteByPrimaryKey(String id);

    int insert(house record);

    int insertSelective(house record);

    List<house> selectByExample(houseExample example);

    house selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") house record, @Param("example") houseExample example);

    int updateByExample(@Param("record") house record, @Param("example") houseExample example);

    int updateByPrimaryKeySelective(house record);

    int updateByPrimaryKey(house record);

    List<Exhouse> findzhuyemian(Integer userid);

    Exhouse xiugaizhu(String id);

    int updateisdelbyid(Map map);

    List<Exhouse> findallExhousebyispass(Integer ispass);

    //实现浏览出租房
    List<Exhouse> getHouseByBroswer(HouseCondition condition);

}