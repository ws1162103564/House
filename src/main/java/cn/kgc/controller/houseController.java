package cn.kgc.controller;

import cn.kgc.domain.*;
import cn.kgc.service.DistrictService;
import cn.kgc.service.impl.HouseServiceImpl;
import cn.kgc.service.impl.streetServiceImpl;
import cn.kgc.domain.HouseCondition;
import cn.kgc.utils.pageUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/page/")
public class houseController {
    @Autowired
    private HouseServiceImpl houseService;
    @Autowired
    private cn.kgc.service.typeService typeService;
    @Autowired
    private streetServiceImpl streetService;
    @Autowired
    private DistrictService districtService;


    @RequestMapping("addHouse")
    public String addHouse(house house
            , HttpSession session,
                           @RequestParam(value = "pfile",required = false) MultipartFile pfile){
        try {
            //1.上传文件
            String sourceFile=pfile.getOriginalFilename();  //文件名
            String extName=sourceFile.substring(sourceFile.lastIndexOf("."));//扩展名
            String bh=System.currentTimeMillis()+"";
            String filename=bh+extName;
            String path="E:\\images\\"+filename;
            File saveFile=new File(path);
            pfile.transferTo(saveFile);   //上传

            //2.调用业务将数据保存到数据库
            //设置编号
            house.setId(bh);
            //设置图片
            house.setPath(filename);
            //设置是否删除0没删除/1删除
            house.setIsdel(0);
            //设置是否审核0没审核/1审核通过
            house.setIspass(0);
            //设置用户编号
            users user=(users) session.getAttribute("userinfo");
            house.setUserId(user.getId());
            houseService.addHouse(house); //保存
            return "fabu";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }
    @RequestMapping("getAlltypexiangmu")
        @ResponseBody
    public List<type> gettype(){
        List<type> typeList = typeService.selectAlltype();
        System.out.println(typeList);
        return typeList;
    }


    //通过区域编号查询街道
    @RequestMapping("getStreetByDid")
    @ResponseBody
    public List<street> getStreetByDid(Integer did){
        return streetService.getStreetByDistrictId(did);
    }


    @RequestMapping("getDistrictall")
    @ResponseBody
    public List<district> getDistrict(){
        List<district> districtList = districtService.selectAllDistrict();
        System.out.println(districtList);
        return districtList;
    }

    @RequestMapping("findzhuyemian")
    public String findzhuyemian(HttpSession session, Model model){
        users usersinfo =(users) session.getAttribute("userinfo");
        List<Exhouse> findzhuyemian = houseService.findzhuyemian(usersinfo.getId());
        model.addAttribute("list",findzhuyemian);
        return "guanli";
    }
    @RequestMapping("xiugaizhu")
    public String xiugaizhu(String id, Model model){
        Exhouse findzhuyemian = houseService.xiugaizhu(id);
        System.out.println(id+"----------------");
        System.out.println(findzhuyemian+"*********");
        model.addAttribute("house",findzhuyemian);
        return "upfabu";
    }
    @RequestMapping("xiugai")
    public String UpdateHouse(Exhouse house,String oldName, @RequestParam(value = "pfile",required = false)MultipartFile pfile) {
        try {
            //根据用户是否选择图片来判定需不需要上传图片
            if (!pfile.isEmpty()) {
                // System.out.println("不为空则修改图片");
                //1.上传文件
                String sourceFile = pfile.getOriginalFilename();  //文件名
                String extName = sourceFile.substring(sourceFile.lastIndexOf("."));//扩展名
                String bh = System.currentTimeMillis() + "";
                String filename = bh + extName;
                String path = "e:\\images\\" + filename;
                File saveFile = new File(path);
                pfile.transferTo(saveFile);   //上传
                //设置图片
                house.setPath(filename);
            }

            //2.调用业务将数据保存到数据库
            houseService.xiugai(house); //保存

            //删除旧图
            if (!pfile.isEmpty()) {
                //删除旧的图片
                File file = new File("e:\\images\\" + oldName);
                file.delete();
            }
            return "redirect:findzhuyemian.do";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    @RequestMapping("delhouse")
    @ResponseBody
    public Map delhouse(String id){
        int delhouse = houseService.delhouse(id, 1);
        Map m=new HashMap();
        m.put("result",delhouse);
        return m;
    }

    /*查找没有审批的*/
    @RequestMapping("findallExhousebyispass")
    @ResponseBody
    public Map<String,Object> getHouseNoPass(pageUtils page){
            //调用业务    0表示未审核
            PageInfo<Exhouse> house=houseService.findallExhousebyispass(0,page);
            //返回数据
            Map<String,Object> map=new HashMap<>();
            map.put("rows",house.getList());
            map.put("total",house.getTotal());
            return map;
    }



    @RequestMapping("goHousePass")
    @ResponseBody
    public String goHousePass(String id){
        //调用业务审核 1表示通过审核
        int temp=this.houseService.updateHousePassState(id,1);
        return "{\"result\":"+temp+"}";
    }

    /*查找没有审批的*/
    @RequestMapping("findallExhousebyispass1")
    @ResponseBody
    public Map<String,Object> getHouseNoPass1(pageUtils page){
        //调用业务    0表示未审核
        PageInfo<Exhouse> house=this.houseService.findallExhousebyispass(1,page);
        //返回数据
        Map<String,Object> map=new HashMap<>();
        map.put("rows",house.getList());
        map.put("total",house.getTotal());
        return map;
    }
    @RequestMapping("goHousePass1")
    @ResponseBody
    public String goHousePass1(String id){
        //调用业务审核 1表示通过审核
        int temp=this.houseService.updateHousePassState(id,0);
        return "{\"result\":"+temp+"}";
    }


    //查询用户浏览的出租房
    @RequestMapping("searchHouse")
    public String searchHouse(HouseCondition condition, Model model){
        //调用业务
        condition.setRows(5);  //页大小默认5条
        PageInfo<Exhouse> houseByBroswer = houseService.getHouseByBroswer(condition);
        //填充信息到Model
        model.addAttribute("houses",houseByBroswer);
        model.addAttribute("HouseCondition",condition);
        return "/list";
    }

}
