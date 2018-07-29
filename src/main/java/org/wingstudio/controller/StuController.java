package org.wingstudio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.wingstudio.Common.Const;
import org.wingstudio.entity.Category;
import org.wingstudio.entity.Student;
import org.wingstudio.entity.Video;
import org.wingstudio.service.CategoryService;
import org.wingstudio.service.CommonService;
import org.wingstudio.service.StudentService;
import org.wingstudio.service.VideoService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * StuController
 * create by chenshihang on 2018/7/28
 */
@Controller
public class StuController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CommonService commonService;

    @RequestMapping("/to_index22")
    public ModelAndView toIndex22(){
        ModelAndView modelAndView = new ModelAndView();
        List<Category> categories = categoryService.getCategories();

        List<Integer> categoryIds =  new ArrayList<>();
        for(Category category: categories){
            categoryIds.add(category.getId());
        }
        List<List<Video>> videos = videoService.getVideosByCategory(categoryIds);

        modelAndView.addObject("videos",videos);
        modelAndView.addObject("categories",categories);

        for(Integer categoryId: categoryIds){
            System.out.println(categoryId+"--------------categoryId---------------");
        }
        modelAndView.setViewName("/student/index_test");

        return modelAndView;
    }

    @RequestMapping("/do_login")
    public ModelAndView doLogin(HttpServletRequest request,String stuNum,String password){

        ModelAndView modelAndView = new ModelAndView();
        try {
            Integer.parseInt(stuNum);
        }catch (Exception e){
            modelAndView.setViewName("student/error");
            modelAndView.addObject("msg","学号或密码错误");
            return modelAndView;
        }

        Student student = studentService.doLogin(Integer.parseInt(stuNum),password);
        if(student==null){
            modelAndView.setViewName("student/error");
            modelAndView.addObject("msg","学号或密码错误");
            return modelAndView;
        }else {
            request.getSession().setAttribute(Const.CURRENT_STU,student);
            modelAndView.setViewName("redirect:/to_index");
            return modelAndView;
        }
    }


    @RequestMapping("/to_index")
    public ModelAndView toIndex(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("/student/index");
        Student student = studentService.isOnline(request);
        if(student==null && !commonService.guestIsOnline(request)){
            commonService.addGuest(request);
        }
        List<Category> categories = categoryService.getCategories();
        List<Video> videos = videoService.getRecentVideos();
        modelAndView.addObject("categories",categories);
        modelAndView.addObject("videos",videos);
        return modelAndView;
    }


}