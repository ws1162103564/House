package cn.kgc.service.impl;

import cn.kgc.domain.district;
import cn.kgc.service.DistrictService;
import cn.kgc.utils.pageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private cn.kgc.dao.districtMapper districtMapper;
    @Autowired
    private cn.kgc.dao.streetMapper streetMapper;

    @Override
    public List<district> selectAllDistrict() {
        return districtMapper.selectAllDistrict();
    }

    @Override
    public PageInfo<district> fenyeByPage(pageUtils pageUtils) {
        PageHelper.startPage(pageUtils.getPage(), pageUtils.getRows());
        List<district> districts = districtMapper.selectAllDistrict();
        PageInfo<district> pageInfo=new PageInfo<>(districts);
        return pageInfo;
    }

    @Override
    public int insertByName(district district) {
        return districtMapper.insert(district);
    }

    @Override
    public district selectByPrimaryKey(Integer id) {
        return districtMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(district record) {
        return districtMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteByPrimaryKey(Integer id) {
        districtMapper.deleteByPrimaryKey(id);
        streetMapper.delbydid(id);
        return 1;
    }

    @Override
    public int delMoreDistrict(Integer[] ids) {
        return districtMapper.delDistrictById(ids);
    }

}
