package cn.kgc.service.impl;

import cn.kgc.dao.streetMapper;
import cn.kgc.domain.street;
import cn.kgc.domain.streetExample;
import cn.kgc.service.streetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class streetServiceImpl implements streetService {
    @Autowired
    private streetMapper streetMapper;


    @Override
    public List<street> getStreetByDistrictId(Integer disstrictId) {
        streetExample streetExample=new streetExample();
        //添加查询条件
        streetExample.Criteria criteria=streetExample.createCriteria();
        criteria.andDistrictIdEqualTo(disstrictId);
        List<street> list= streetMapper.selectByExample(streetExample);
        return list;
    }
}
