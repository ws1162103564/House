package cn.kgc.service;


import cn.kgc.domain.users;
import cn.kgc.utils.tiaojianfenye;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UsersService {

    PageInfo<users> getUserByCondition(tiaojianfenye condition);


    users denglu(String name, String password);

    int insertusers(users u);
}
