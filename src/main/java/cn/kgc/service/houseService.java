package cn.kgc.service;

import cn.kgc.domain.Exhouse;
import cn.kgc.domain.house;
import cn.kgc.domain.HouseCondition;
import cn.kgc.utils.pageUtils;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface houseService {

    int addHouse(house house);

    List<Exhouse> findzhuyemian(Integer userid);

    Exhouse xiugaizhu(String id);

    int xiugai(Exhouse exhouse);

    /*逻辑删除*/
    int delhouse(String id,Integer isdel);

    /*查找审核*/
    PageInfo<Exhouse> findallExhousebyispass(Integer ispass, pageUtils pageUtil);


    /*修改出租房的审核状态*/
     int updateHousePassState(String id,Integer state);


     //实现浏览出租房
     PageInfo<Exhouse> getHouseByBroswer(HouseCondition condition);

}
