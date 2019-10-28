package cn.kgc.service.impl;

import cn.kgc.domain.type;
import cn.kgc.service.typeService;
import cn.kgc.utils.pageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class typeServiceImpl implements typeService {

    @Autowired
    private cn.kgc.dao.typeMapper typeMapper;
    @Autowired
    private cn.kgc.dao.streetMapper streetMapper;

    @Override
    public List<type> selectAlltype() {
        return typeMapper.selectAlltype();
    }

    @Override
    public PageInfo<type> fenyeByPage(pageUtils pageUtils) {
        PageHelper.startPage(pageUtils.getPage(), pageUtils.getRows());
        List<type> types = typeMapper.selectAlltype();
        PageInfo<type> pageInfo=new PageInfo<>(types);
        return pageInfo;
    }

    @Override
    public int insertByName(type type) {
        return typeMapper.insert(type);
    }

    @Override
    public type selectByPrimaryKey(Integer id) {
        return typeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(type record) {
        return typeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteByPrimaryKey(Integer id) {
        typeMapper.deleteByPrimaryKey(id);
        streetMapper.delbydid(id);
        return 1;
    }

    @Override
    public int delMoretype(Integer[] ids) {
        return typeMapper.deltypeById(ids);
    }

}
