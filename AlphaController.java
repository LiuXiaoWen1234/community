package com.nowcoder.community.controller;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.zip.CheckedOutputStream;

@Controller
@RequestMapping("/alpha")
public class AlphaController {
    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "Hello Spring Boot.";
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        //获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        //请求消息头
        Enumeration<String> enumeration = request.getHeaderNames();
        while(enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ":" + value);
        }
        //请求体包含的业务数据，即包含的参数
        System.out.println(request.getParameter("code"));

        //response对浏览器做出相应
        response.setContentType("text/html;charset=utf-8");
        try (
                PrintWriter writer = response.getWriter();
                ){
            //通过writer向浏览器打印
            writer.write("<h1>牛客网</h1>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //Get请求-方式1
    // students?current=1&limit=10
    @RequestMapping(path = "students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit){
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }

    //查询一个学生-请求方式2-放在路径里
    // /student/123 直接将参数编排到路径中成为路径一部分
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){
        System.out.println(id);
        return "a student";
    }

    //POST请求，浏览器向服务器提交数据
    @RequestMapping(path="/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    //响应HTML数据
    //浏览器要查询一个老师，服务器查询到，响应给浏览器
    @RequestMapping(path="/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView modelAndView = new ModelAndView(); //实例化对象
        modelAndView.addObject("name", "张三");
        modelAndView.addObject("age", "45");
        modelAndView.setViewName("/demo/view");
        return modelAndView;
    }

    //向浏览器响应HTML数据方法二
    @RequestMapping(path="/school", method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name", "北京大学");
        model.addAttribute("age",  105);

        return "/demo/view";
    }

    //异步请求需要响应Json数据，当前网页不动，悄悄进行某种操作，需要json
    // Java对象 -> ISON字符串 -> JS对象, 衔接作用
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody //不加会认为返回html
    public Map<String, Object> getEmp(){
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 19);
        emp.put("salary", 8000.00);
        return emp;
    }

    //Json 返回一组数据
    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody //不加会认为返回html
    public List<Map<String, Object>> getEmps(){
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 19);
        emp.put("salary", 8000.00);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "张四");
        emp.put("age", 29);
        emp.put("salary", 9000.00);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "张五");
        emp.put("age", 39);
        emp.put("salary", 9400.00);
        list.add(emp);


        return list;
    }


}
