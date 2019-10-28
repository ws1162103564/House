package cn.kgc.controller;

import cn.kgc.domain.district;
import cn.kgc.service.DistrictService;
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
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @RequestMapping("getDistrict")
    @ResponseBody
    public List<district> getDistrict(){
        List<district> districtList = districtService.selectAllDistrict();
        System.out.println(districtList);
        return districtList;
    }

    @RequestMapping("getDistrictFenye")
    @ResponseBody
    public Map<String,Object> getDistrict(pageUtils pageUtils){
        PageInfo<district> pageInfo = districtService.fenyeByPage(pageUtils);
        Map<String,Object> map=new HashMap<>();
        map.put("rows",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }

    @RequestMapping("insertByName")
    @ResponseBody
    public Map<String,Object> insertByName(district district){
        int i = districtService.insertByName(district);
        Map<String,Object> map=new HashMap<>();
        map.put("result",i);
        return map;
    }

    @RequestMapping("getDistrictid")
    @ResponseBody
    public district xiugai(Integer id){
        return   districtService.selectByPrimaryKey(id);

    }
    @RequestMapping("upDistrit")
    @ResponseBody
    public Map upDistrit(district district){
        int i = districtService.updateByPrimaryKeySelective(district);
        Map m=new HashMap();
        m.put("result",i);
        return m;
    }

    @RequestMapping("delDistrit")
    @ResponseBody
    public Map delDistrit(Integer id){
        int i = districtService.deleteByPrimaryKey(id);
        Map m=new HashMap();
        m.put("result",i);
        return m;
    }
    @RequestMapping("delMoreDistrit")
    @ResponseBody
    public Map<String,Object> delMoreDistrit(String ids){
        String [] list=ids.split(",");
        Integer [] ary=new Integer[list.length];
        for (int i = 0; i <list.length ; i++) {
            ary[i]=Integer.parseInt(list[i]);
        }
        int flag=districtService.delMoreDistrict(ary);
        Map<String,Object> map=new HashMap<>();
        map.put("result",flag);
        return map;
    }

}
