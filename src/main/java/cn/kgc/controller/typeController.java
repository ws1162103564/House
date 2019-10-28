package cn.kgc.controller;

import cn.kgc.domain.type;
import cn.kgc.service.typeService;
import cn.kgc.utils.pageUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class typeController {
    @Autowired
    private typeService typeService;

    @RequestMapping("gettype")
    @ResponseBody
    public List<type> gettype(){
        List<type> typeList = typeService.selectAlltype();
        System.out.println(typeList);
        return typeList;
    }

    @RequestMapping("gettypeFenye")
    @ResponseBody
    public Map<String,Object> gettype(pageUtils pageUtils){
        PageInfo<type> pageInfo = typeService.fenyeByPage(pageUtils);
        Map<String,Object> map=new HashMap<>();
        map.put("rows",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }

    @RequestMapping("inserttypeByName")
    @ResponseBody
    public Map<String,Object> insertByName(type type){
        int i = typeService.insertByName(type);
        Map<String,Object> map=new HashMap<>();
        map.put("result",i);
        return map;
    }

    @RequestMapping("gettypeid")
    @ResponseBody
    public type gettypeid(Integer id){
        return   typeService.selectByPrimaryKey(id);
    }
    @RequestMapping("uptype")
    @ResponseBody
    public Map upDistrit(type type){
        int i = typeService.updateByPrimaryKeySelective(type);
        Map m=new HashMap();
        m.put("result",i);
        return m;
    }

    @RequestMapping("deltype")
    @ResponseBody
    public Map delDistrit(Integer id){
        int i = typeService.deleteByPrimaryKey(id);
        Map m=new HashMap();
        m.put("result",i);
        return m;
    }
    @RequestMapping("delMoretype")
    @ResponseBody
    public Map<String,Object> delMoreDistrit(String ids){
        String [] list=ids.split(",");
        Integer [] ary=new Integer[list.length];
        for (int i = 0; i <list.length ; i++) {
            ary[i]=Integer.parseInt(list[i]);
        }
        int flag=typeService.delMoretype(ary);
        Map<String,Object> map=new HashMap<>();
        map.put("result",flag);
        return map;
    }

}
