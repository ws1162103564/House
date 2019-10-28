package cn.kgc.controller;


import cn.kgc.domain.users;
import cn.kgc.service.impl.UsersServiceImpl;
import cn.kgc.utils.tiaojianfenye;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/page/")
public class usersController {
    @Autowired
    private UsersServiceImpl usersService;



    @RequestMapping("usersdenglu")
    public String  usersdenglu(String name, String password, HttpSession session){
        users denglu = usersService.denglu(name, password);
        if (denglu!=null){
            session.setAttribute("userinfo",denglu);
            return "redirect:findzhuyemian.do";
        }else{
            return "login";
        }
    }

    @RequestMapping("userszhuce")
    public String  userszhuce(users users){
        int insertusers = usersService.insertusers(users);
        if (insertusers>0){
            return "login";
        }else{
            return "regs";
        }         
    }
}
