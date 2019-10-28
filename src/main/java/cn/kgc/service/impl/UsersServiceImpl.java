package cn.kgc.service.impl;

import cn.kgc.domain.users;
import cn.kgc.domain.usersExample;
import cn.kgc.service.UsersService;
import cn.kgc.utils.tiaojianfenye;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

@Autowired
cn.kgc.dao.usersMapper usersMapper;


    @Override
    public PageInfo<users> getUserByCondition(tiaojianfenye condition) {
        //1.开启分页
        PageHelper.startPage(condition.getPage(),condition.getRows());
        //2.查询所有用户
        usersExample usersExample=new usersExample();
        //使用Criteria封装查询条件
        usersExample.Criteria criteria=usersExample.createCriteria();
        //添加条件
        if(condition.getName()!=null&&condition.getName()!="")
            criteria.andNameLike("%"+condition.getName()+"%");
        if(condition.getTelephone()!=null&&condition.getTelephone()!="")
            criteria.andTelephoneLike("%"+condition.getTelephone()+"%");
        List<users> list=this.usersMapper.selectByExample(usersExample); //查询
        return new PageInfo<>(list);
        }

    @Override
    public users denglu(String name, String password) {
        usersExample u=new usersExample();
        usersExample.Criteria criteria = u.createCriteria();
        criteria.andNameEqualTo(name);
        criteria.andPasswordEqualTo(password);
        List<users> users = usersMapper.selectByExample(u);
        if (users.size()!=0){
            return users.get(0);
        }else{
            return null;
        }
    }

    @Override
    public int insertusers(users u) {
        return usersMapper.insertSelective(u);
    }
}
