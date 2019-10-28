package cn.kgc.controller;

import cn.kgc.domain.users;
import cn.kgc.service.impl.UsersServiceImpl;
import cn.kgc.utils.tiaojianfenye;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
@Controller
public class UsersControllerhoutai {
    @Autowired
    private UsersServiceImpl usersService;


    @RequestMapping("getUserByPage")
    @ResponseBody
    public Map findusersbytiaojian(tiaojianfenye tiaojianfenye){
        PageInfo<users> userByCondition = usersService.getUserByCondition(tiaojianfenye);
        Map m=new HashMap();
        m.put("rows",userByCondition.getList());
        m.put("total",userByCondition.getTotal());
        return m;
    }
}
