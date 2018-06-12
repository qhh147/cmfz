package com.baizhi.controller;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.entity.AddressSort;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.util.*;

/**
 * Created by Administrator on 2018/5/28.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    /*分页浏览用户信息*/
    @RequestMapping(value = "/selectUser",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Map selectUser(Integer page, String rows){
        Integer total=userService.queryCount();
        if(page==null){
            page=1;
        }
        Integer row = Integer.valueOf(rows);
        if(row==null) row=10;
        Integer begin=(page-1)*row;
        Integer end=page*row;
        List<User> users = userService.selectUser(begin,end);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("total",total);
        map.put("rows",users);
        return map;
    }
    /*修改用户状态*/
    @RequestMapping(value="/updateUser",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String updateUser(int id,String status){
        userService.update(id, status);
        return "修改成功";
    }
    /*导出用户信息*/
    @RequestMapping(value = "/writeOutUser",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String query(String titles, String fileds, HttpServletResponse response) throws Exception{
        /*导出所有用户自定义信息*/
        Workbook workbook = new HSSFWorkbook();
        DataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy-MM-dd");
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setDataFormat(format);
        Sheet sheet = workbook.createSheet("用户信息表");

        String[] split = titles.split(",");
        String[] userFields = fileds.split(",");

        Row row = sheet.createRow(0);

        for (int i = 0; i < split.length; i++) {
            String title=split[i];
            Cell cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(title);
        }

        List<User> list = userService.query();
        for (int i = 0; i < list.size(); i++) {
            Row row1 = sheet.createRow(i + 1);
            for(int j=0;j<userFields.length;j++){
                Cell cell = row1.createCell(j);
                cell.setCellStyle(cellStyle);
                Class<? extends User> userClass = list.get(i).getClass();
                String methodName="get"+userFields[j].substring(0,1).toUpperCase()+userFields[j].substring(1);
                Object invoke = userClass.getDeclaredMethod(methodName, null).invoke(list.get(i), null);
                if(invoke instanceof Date){
                    sheet.setColumnWidth(j,18*256);
                    cell.setCellValue((Date) invoke);
                }else{
                    cell.setCellValue(invoke.toString());
                }
            }
        }
        String name = "用户数据.xls";
        String fileName = "";
        fileName = new String(name.getBytes("UTF-8"), "ISO8859-1");

        response.setHeader("content-disposition", "attachment;fileName=" + fileName);
        response.setContentType("application/vnd.ms-excel");
        workbook.write(response.getOutputStream());
        workbook.close();
        return "成功导出用户信息";
    }

    /*导入用户信息*/
    @RequestMapping(value = "/inputUser",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public void inputUser(MultipartFile filed, HttpServletRequest request,String type) throws Exception{
        //new FileInputStream("D:/userfile/user/"+filed.getOriginalFilename())
        List<User> users = new ArrayList<User>();
        Workbook workbook = new HSSFWorkbook(new FileInputStream("D:/userfile/"+filed.getOriginalFilename()));
        Sheet sheet = workbook.getSheetAt(0);
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            User user = new User();
            Row row = sheet.getRow(i);
            Cell cell = row.getCell(0);
            Cell cell1 = row.getCell(1);
            if(cell==null && cell1==null){
                break;
            }
            Integer v = (int) cell.getNumericCellValue();
            user.setId(v);
            String v1 = cell1.getStringCellValue();
            user.setUsername(v1);
            Cell cell2 = row.getCell(2);
            String v2 = cell2.getStringCellValue();
            user.setPassword(v2);
            Cell cell3 = row.getCell(3);
            String v3 = cell3.getStringCellValue();
            user.setFarmington(v3);

            Cell cell4 = row.getCell(4);
            String d4 = cell4.getStringCellValue();
            user.setNickname(d4);

            Cell cell5 = row.getCell(5);
            String d5 = cell5.getStringCellValue();
            user.setSex(d5);

            Cell cell6 = row.getCell(6);
            String d6 = cell6.getStringCellValue();
            user.setHeadPic(d6);

            Cell cell7 = row.getCell(7);
            String d7 = cell7.getStringCellValue();
            user.setProvince(d7);

            Cell cell8 = row.getCell(8);
            String d8 = cell8.getStringCellValue();
            user.setCity(d8);

            Cell cell9 = row.getCell(9);
            String d9 = cell9.getStringCellValue();
            user.setLocation(d9);

            Cell cell10 = row.getCell(10);
            String d10 = cell10.getStringCellValue();
            user.setDescription(d10);

            Cell cell11 = row.getCell(11);
            String d11 = cell11.getStringCellValue();
            user.setPhone(d11);
            Cell cell12 = row.getCell(12);
            String d12 = cell12.getStringCellValue();
            user.setShangshi(d12);
            Cell cell13 = row.getCell(13);
            String d13 = cell13.getStringCellValue();
            user.setStatus(d13);
            Cell cell14 = row.getCell(14);
            Date d14 = cell14.getDateCellValue();
            user.setBdate(d14);

            users.add(user);
        }

        for (User user : users) {
            System.out.println(user);
        }
        for (int i = 0; i < users.size(); i++) {
             userService.insertUser(users.get(i));
        }
    }

    /*展示用户地域分布信息*/
    @PostMapping(value = "/showSort",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<AddressSort> showSort() {
        List<AddressSort> sort = userService.queryAll();
        return sort;
    }
    /*展示用户地域分布信息*/
   /* @RequestMapping(value = "/loginShowUserSort",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public void showUserSort(HttpServletResponse response) {
        List<AddressSort> sort = userService.queryAll();
        List<String> prov = new ArrayList<String>();
        List<Integer> counts = new ArrayList<Integer>();
        for (int i = 0; i < sort.size(); i++) {
            prov.add(sort.get(i).getProvince());
            counts.add(sort.get(i).getCounts());
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("prov", prov);
        jsonObject.put("counts", counts);
        String s = jsonObject.toJSONString();
         *//*1.服务器地址   appkey ：commonKey*//*
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-63cd5c36f44b4886aa5faea05a56260b");
     *//*1. 管道标识  2.消息内容*//*
        goEasy.publish("my_channel", s);
    }*/

    @PostMapping(value = "/showUserLocation",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Map showUserLocation(){
        List<AddressSort> sort = userService.queryAll();
        Map<String,Integer> map=new HashMap<String, Integer>();
        for (int i = 0; i <sort.size(); i++) {
            map.put(sort.get(i).getProvince(),sort.get(i).getCounts());
        }
        return map;
    }

    /*客户登录*/
    @RequestMapping(value = "/loginUser1",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public JSONObject login(String phone,String password,HttpSession session){
        JSONObject jsonObject=new JSONObject();
        if(phone!=null){
            User user = userService.queryByPhone(phone);
            if(user!=null){
                if(user.getPassword().equals(password)){
                    String username = user.getUsername();
                    session.setAttribute("username",username);
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
    /*用户修改密码*/
    @RequestMapping(value = "/updatePassword",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String updatePassword(String password, HttpSession session){
        String username = (String) session.getAttribute("username");
        userService.updatePass(username,password);
        return "密码修改成功";
    }

    /*退出*/
    @RequestMapping(value="/existsUser")
    public String existsUser(HttpSession session){
        session.removeAttribute("username");
        return "redirect:/main/main.jsp";
    }

}
