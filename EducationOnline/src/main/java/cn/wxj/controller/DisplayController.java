package cn.wxj.controller;

import com.alibaba.fastjson.JSON;

import cn.wxj.entity.Course;
import cn.wxj.entity.Student;
import cn.wxj.entity.StudyInfo;
import cn.wxj.service.IAdminService;
import cn.wxj.service.IClzService;
import cn.wxj.service.IStudyService;
import cn.wxj.util.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
public class DisplayController {

    @Autowired
    private IClzService clzService;

    @Autowired
    private IStudyService studyService;

    @Autowired
    private IAdminService adminService;



     //功能描述：前台首页
    @RequestMapping("/index")
    public String displayAll(HttpServletRequest req) {

        List<Course> clzs = clzService.getAllClz();
        req.getSession().setAttribute("clzs", clzs);
        return "index";
    }


    //课程详情

    @RequestMapping("/showDetail")
    public String showDetail(@RequestParam String id, HttpServletRequest req) {
        Course course = clzService.getClzById(Integer.parseInt(id));
        req.getSession().setAttribute("course", course);
        return "WEB-INF/pages/course/detail";
    }

    //查看已选该门课程的所有学生列表
    @RequestMapping("/showStudent")
    public String showStudents(@RequestParam(defaultValue="1")Integer page, 
			@RequestParam(defaultValue="5")Integer rows,HttpServletRequest req,Model model) {
        try {
            Integer clzId = Integer.parseInt(req.getParameter("id"));
            Page<StudyInfo> students = studyService.getAllStuByClzId(clzId,page,rows);

            req.getSession().setAttribute("students", students.getRows());
            model.addAttribute("page", students);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "404";
        }
       
        return "WEB-INF/pages/student/stulist";

    }


     //功能描述：分页获取所有课程
    @RequestMapping("/showAllClasses")
    public String showAllClzs(@RequestParam(defaultValue="1")Integer page, 
			@RequestParam(defaultValue="5")Integer rows,HttpServletRequest req,Model model) {
    	Page<Course> courses = clzService.getAllPageClz(page,rows);
        req.getSession().setAttribute("courses", courses.getRows());
        //req.getSession().setAttribute("page", courses);
        model.addAttribute("page", courses);
        return "WEB-INF/pages/course/clzList";
    }


    //分页获取我的选课列表
    @RequestMapping("/showMyClasses")
    public String showMyClzs(@RequestParam(defaultValue="1")Integer page, 
			@RequestParam(defaultValue="5")Integer rows,HttpServletRequest req,Model model) {
        String id = (String) req.getSession().getAttribute("userId");
        Page<StudyInfo> studyInfos=null;
        if(id!=null){
            studyInfos = studyService.getAllClzByStuId(id,page,rows);
            req.getSession().setAttribute("clzs", studyInfos.getRows());
            model.addAttribute("page", studyInfos);
        }else{
        	req.getSession().setAttribute("clzs", null);
        }
        
        return "WEB-INF/pages/course/myClzs";
    }


     //学生管理，分页获取学生列表
    @RequestMapping("/studentManage")
    public String studentManage(@RequestParam(defaultValue="1")Integer page, 
			@RequestParam(defaultValue="5")Integer rows,HttpServletRequest req,Model model){
        if(req.getSession().getAttribute("id")==null){
            return "adminLogin";
        }
        Page<Student> students=adminService.getAllStudents(page,rows);
        req.getSession().setAttribute("students", students.getRows());
        model.addAttribute("page", students);
        return "WEB-INF/pages/student/allStudents";
    }


    //后台课程管理
    @RequestMapping("/courseManage")
    public String courseManage(@RequestParam(defaultValue="1")Integer page, 
			@RequestParam(defaultValue="5")Integer rows,HttpServletRequest req,Model model){
        if(req.getSession().getAttribute("id")==null){
            return "adminLogin";
        }
        Page<Course> courses=adminService.getPageAllCourses(page,rows);
        req.getSession().setAttribute("courses", courses.getRows());
        model.addAttribute("page", courses);
        return "WEB-INF/pages/course/allCourses";
    }

    @RequestMapping("/adminIndex")
    public String showChart(HttpServletRequest req){
        if(req.getSession().getAttribute("id")==null){
            return "adminLogin";
        }
        List<Course> courses=adminService.getAllCourses();
        List<String> listX=new ArrayList<String>();
        List<Integer> listSelected=new ArrayList<Integer>();
        List<Integer> listLeft=new ArrayList<Integer>();
        for(Course course:courses){
            listX.add(course.getName());
            listSelected.add(course.getSelected());
            listLeft.add(course.getAmount()-course.getSelected());
        }

        req.getSession().setAttribute("listX", JSON.toJSONString(listX));
        req.getSession().setAttribute("listSelected",JSON.toJSONString(listSelected));
        req.getSession().setAttribute("listLeft",JSON.toJSONString(listLeft));
        return "WEB-INF/pages/admin/admin";
    }

    @RequestMapping("/404")
    public String pageNotFount(){
        return "404";
    }

}
