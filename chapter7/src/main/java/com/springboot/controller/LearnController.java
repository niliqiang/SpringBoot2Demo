package com.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.springboot.entity.LearnResource;
import com.springboot.service.LearnService;
import com.springboot.tools.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/learn")
public class LearnController {
    @Autowired
    private LearnService learnService;

    @RequestMapping("t")
    public String learn(){
        return "learn-resource";
    }

    @RequestMapping(value = "queryLearnList",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public void queryLearnList(HttpServletRequest request , HttpServletResponse response){
        int page = Integer.parseInt(request.getParameter("page")); // 取得当前页数,注意这是jqgrid自身的参数
        int rows = Integer.parseInt(request.getParameter("rows")); // 取得每页显示行数，,注意这是jqgrid自身的参数
        PageInfo pageObj =learnService.queryLearnResourceList(page, rows);
        List<Map<String, Object>> learnList=pageObj.getList();
        JSONObject jo=new JSONObject();
        jo.put("rows", learnList); //包含实际数据的数组
        jo.put("total", pageObj.getPages()); //总页数
        jo.put("records", pageObj.getTotal()); //查询出的记录数
        ServletUtil.createSuccessResponse(200, jo, response);
    }

    /**
     * 新添教程
     * @param request
     * @param response
     */
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public void addLearn(HttpServletRequest request , HttpServletResponse response){
        JSONObject result=new JSONObject();
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        String url = request.getParameter("url");
        if(author.isEmpty()){
            result.put("message","作者不能为空!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        if(title.isEmpty()){
            result.put("message","教程名称不能为空!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        if(url.isEmpty()){
            result.put("message","地址不能为空!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        LearnResource learnResource = new LearnResource();
        learnResource.setAuthor(author);
        learnResource.setTitle(title);
        learnResource.setUrl(url);
        int index=learnService.add(learnResource);
        System.out.println("结果="+index);
        if(index>0){
            result.put("message","教程信息添加成功!");
            result.put("flag",true);
        }else{
            result.put("message","教程信息添加失败!");
            result.put("flag",false);
        }
        ServletUtil.createSuccessResponse(200, result, response);
    }

    /**
     * 删除教程
     * @param request
     * @param response
     */
    @RequestMapping(value="delete",method = RequestMethod.POST)
    @ResponseBody
    public void deleteUser(HttpServletRequest request ,HttpServletResponse response){
        String id = request.getParameter("ids");
        System.out.println("ids==="+id);
        JSONObject result = new JSONObject();
        //删除操作
        int index = learnService.delete(id);
        if(index>0){
            result.put("message","教程信息删除成功!");
            result.put("flag",true);
        }else{
            result.put("message","教程信息删除失败!");
            result.put("flag",false);
        }
        ServletUtil.createSuccessResponse(200, result, response);
    }
}
