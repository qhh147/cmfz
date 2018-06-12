package com.baizhi.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.baizhi.entity.Ringimg;
import com.baizhi.entity.User;
import com.baizhi.entity.Zhuanji;
import com.baizhi.service.RingimgService;
import com.baizhi.service.UserService;
import com.baizhi.service.ZhangjieService;
import com.baizhi.service.ZhuanjiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2018/6/6.
 */
@Controller
public class ShowController {
    @Autowired
    private RingimgService ringimgService;
    @Autowired
    private ZhuanjiService zhuanjiService;
    @Autowired
    private ZhangjieService zhangjieService;
    @Autowired
    private UserService userService;


    /*1首页展示,wen*/
    @RequestMapping(value = "/first_page",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public JSONObject first_page(String type,String subtype,Integer uid,HttpSession session){
        Integer uid1 = (Integer) session.getAttribute("uid");
        JSONObject jsonObject=new JSONObject();
        if(subtype==null){
            if(uid!=null){
                if(type.equals("wen")){
                    //wen:展示专辑（所有）
                    List<Zhuanji> zhuanjis1 = zhuanjiService.queryNews();//所有专辑
                    jsonObject.put("album",zhuanjis1);
                }else{
                    //展示首页,轮播图、最新专辑（6）、最新文章(2)
                    List<Ringimg> ringimgs = ringimgService.queryAllRimg();//轮播图
                    List<Zhuanji> zhuanjis = zhuanjiService.queryNew();//最新专辑
                    jsonObject.put("header",ringimgs);
                    jsonObject.put("album",zhuanjis);
                }
            }else {
                //用户未登录，强制登录
                jsonObject.put("error",-200);
                jsonObject.put("errmsg","账号未登录，请先登录");
            }
        }else{
            //si:甘露妙宝
            if(subtype.equals("xmfy")){
                //显密法要
            }else{
                //上师言教
            }
        }

        return jsonObject;
    }

    /*3wen*/
    @RequestMapping(value = "/wen",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public JSONObject wen(Integer id,Integer uid,HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        if(uid!=null){
            List<Zhuanji> list = zhuanjiService.queryById(id);
            jsonObject.put("wen",list);
        }else{
            jsonObject.put("error",-200);
            jsonObject.put("errmsg","账号未登录，请先登录");
        }
        return jsonObject;
    }

    /*4客户登录*/
    @RequestMapping(value = "/login",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public JSONObject login(String phone,String password,HttpSession session){
        JSONObject jsonObject=new JSONObject();
        if(phone!=null){
            User user = userService.queryByPhone(phone);
            if(user!=null){
                if(user.getPassword().equals(password)){
                    int id = user.getId();
                    session.setAttribute("uid",id);
                    jsonObject.put("password",user.getPassword());
                    jsonObject.put("farmington",user.getFarmington());
                    jsonObject.put("uid",user.getId());
                    jsonObject.put("nickname",user.getNickname());
                    jsonObject.put("gender",user.getSex());
                    jsonObject.put("photo",user.getHeadPic());
                    jsonObject.put("location",user.getLocation());
                    jsonObject.put("province",user.getProvince());
                    jsonObject.put("city",user.getCity());
                    jsonObject.put("description",user.getDescription());
                    jsonObject.put("phone",user.getPhone());
                }else{
                    jsonObject.put("errmsg","密码不正确");
                    jsonObject.put("error",-200);
                }
            }else{
                jsonObject.put("errmsg","账号不存在");
                jsonObject.put("error",-200);
            }
        }else{
            jsonObject.put("errmsg","账号为空,请重新填写账号");
            jsonObject.put("error",-200);
        }
        return jsonObject;
    }

    /*5用户注册*/
    @RequestMapping(value = "/regist",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public JSONObject register(String phone,String password){
        JSONObject jsonObject = new JSONObject();
        if(phone==null || password==null){
            jsonObject.put("error",-200);
            jsonObject.put("errmsg","请正确填写电话和密码");
        }else {
            if (userService.queryByPhone(phone) != null) {
                jsonObject.put("error", -200);
                jsonObject.put("errmsg", "该手机号码已经存在");
            } else {
                User user = new User();
                user.setBdate(new Date());
                user.setStatus("y");
                user.setPhone(phone);
                user.setPassword(password);
                try {
                    userService.insertUser(user);
                    User user1 = userService.queryByPhone(phone);
                    jsonObject.put("password", user1.getPassword());
                    jsonObject.put("uid", user1.getId());
                    jsonObject.put("phone", user1.getPhone());
                } catch (Exception e) {
                    jsonObject.put("error", -200);
                    jsonObject.put("errmsg", "数据有误，注册失败");
                }
            }
        }
        return jsonObject;
    }

    /*6修改个人信息*/
    @RequestMapping(value = "/modify",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public JSONObject modify(HttpSession session,Integer uid,String gender,String photo,String location,String description,String nickname,String province,String city,String password){
        JSONObject jsonObject = new JSONObject();
        Integer id = (Integer) session.getAttribute("uid");
        if(uid!=null){
            User user = new User();
            user.setId(uid);
            user.setSex(gender);
            user.setHeadPic(photo);
            user.setDescription(description);
            user.setLocation(location);
            user.setCity(city);
            user.setNickname(nickname);
            user.setPassword(password);
            user.setProvince(province);
            userService.updateUseMSG(user);
            User user1 = userService.queryById(uid);
            jsonObject.put("user",user1);

        }else{
            jsonObject.put("error",-200);
            jsonObject.put("errmsg","您未登录，请先登录");
        }
        return jsonObject;

    }

    /*7获取短信验证码*/
    @RequestMapping(value = "/obtain",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public JSONObject obtain(String phone,HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        try {
            CreateValidateCode cvc=new CreateValidateCode();
            String code = cvc.getCode();
            SendMSG.send(phone,code);
            session.setAttribute("yzm",code);
            session.setAttribute("phone",phone);
            jsonObject.put("msg","发送成功");
        } catch (ClientException e) {
            e.printStackTrace();
            jsonObject.put("error",-200);
            jsonObject.put("errmsg","发送失败，电话号码不存在");
        }
        return jsonObject;
    }

    /*8短信验证码校验*/
    @RequestMapping(value = "/check",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public JSONObject check(String phone,String code,HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        String code1 = (String) session.getAttribute("yzm");
        String phone1 = (String) session.getAttribute("phone");
        if(code.equals(code1) && phone.equals(phone1)){
            jsonObject.put("result","验证成功");
        }else{
            jsonObject.put("result","验证失败");
        }
        return jsonObject;
    }

    /*9获取会员列表*/
    @RequestMapping(value = "/member",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public JSONObject member(Integer id,HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        Integer id1 = (Integer) session.getAttribute("uid");
        if(id!=null){
            List<User> users = userService.query();
            for (int i = 0; i < users.size(); i++) {
                if(users.get(i).getId()==id){
                    users.remove(i);
                }
            }
            List<User> users1=new ArrayList<User>();
            Random random = new Random();
            List<Integer> r=new ArrayList<Integer>();
            for(int i=0;i<5;i++){
                int x = random.nextInt(users.size());
                if(!r.contains(x)){
                    r.add(x);
                    users1.add(users.get(x));
                }else{
                    i--;
                }
            }
            jsonObject.put("users",users1);
        }else{
            jsonObject.put("result","请先登录");
        }
        return jsonObject;
    }

}
