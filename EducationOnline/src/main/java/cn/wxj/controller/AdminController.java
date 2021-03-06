package cn.wxj.controller;

import cn.wxj.entity.Course;
import cn.wxj.entity.Student;
import cn.wxj.entity.StudyInfo;
import cn.wxj.service.IAdminService;
import cn.wxj.service.IStudentService;
import cn.wxj.service.IStudyService;
import cn.wxj.util.Page;
import cn.wxj.util.Tools;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IStudyService studyService;
    
    public static SimpleDateFormat df = new SimpleDateFormat("yyyyMM");

    @RequestMapping(value = "/changeStudent", method = RequestMethod.GET)
    public String changeStu(@RequestParam String id, HttpServletRequest req) {
        Student student = adminService.getStudentById(id);
        req.getSession().setAttribute("student", student);
        return "WEB-INF/pages/student/changeStu";
    }

    @RequestMapping(value = "/changeStudent", method = RequestMethod.POST)
    public String changeStudent(HttpServletRequest req) {
        try {
            req.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Student student = new Student();
        student.setId(req.getParameter("id"));
        student.setName(req.getParameter("name"));
        student.setPwd(req.getParameter("pwd"));
        student.setMajor(req.getParameter("major"));
        student.setYear(req.getParameter("year"));
        student.setSex(req.getParameter("sex").charAt(0));

        String msg = null;
        if (adminService.updateStudent(student)) {
            msg = "????????????";
        } else {
            msg = "????????????";
        }

        req.getSession().setAttribute("msg", msg);

        return "redirect:/studentManage";
    }

    @RequestMapping(value = "addStudent", method = RequestMethod.POST)
    public String addStudent(HttpServletRequest req) {
        try {
            req.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Student student = new Student();
        student.setId(req.getParameter("id"));
        student.setName(req.getParameter("name"));
        student.setPwd(req.getParameter("pwd"));
        student.setMajor(req.getParameter("major"));
        student.setYear(req.getParameter("year"));
        student.setSex(req.getParameter("sex").charAt(0));

        String msg = null;
        if (adminService.addStudent(student)) {
            msg = "????????????";
        } else {
            msg = "????????????";
        }

        req.getSession().setAttribute("msg", msg);

        return "redirect:/studentManage";
    }

    @RequestMapping("delStudent")
    public String delStudent(@RequestParam String id, HttpServletRequest req) {
        adminService.delStudent(id);
        req.getSession().setAttribute("msg", "????????????");
        return "redirect:/studentManage";
//        if (id != null) {
//            adminService.delStudent(id);
//            req.getSession().setAttribute("msg", "??????????????????");
//        } else {
//            req.getSession().setAttribute("msg", "??????????????????");
//        }
//
//        return "redirect:/studentManage";

    }


    //????????????
    @RequestMapping(value = "addCourse", method = RequestMethod.POST)
    public String addCourse(HttpServletRequest req,
    		@RequestParam("file") MultipartFile file) {
        String msg = null;
        String year_moth = df.format(new Date());
        try {
            req.setCharacterEncoding("utf-8");

            Course course = new Course();
            if(!file.isEmpty()){
		    	ServletContext sc = req.getSession().getServletContext();
		        String dir = sc.getRealPath("/upload/imgurl/"+year_moth+"");    //???????????????????????????
		        String filename = file.getOriginalFilename();    //???????????????????????????
		        String tempfilename = Tools.getRndFilename()+Tools.getFileExtName(filename);          
		      			
				try {
					FileUtils.writeByteArrayToFile(new File(dir,tempfilename), file.getBytes());
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				course.setImgurl("/upload/imgurl/"+year_moth+"/"+tempfilename); //????????????????????????
		        
		    }
            course.setName(req.getParameter("name"));
            course.setSelected(0);
            course.setAmount(Integer.parseInt(req.getParameter("amount")));
            course.setBelong(req.getParameter("belong"));
            course.setCredit(Integer.parseInt(req.getParameter("credit")));
            course.setPlace(req.getParameter("place"));
            course.setDetail(req.getParameter("detail"));
            course.setTime(req.getParameter("time"));
            if (adminService.addCourse(course)) {
                msg = "????????????";
            } else {
                msg = "????????????";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            msg = "????????????";
        } finally {
            req.getSession().setAttribute("msg", msg);
            return "redirect:/courseManage";
        }
    }

    @RequestMapping("adminDelCourse")
    public String delCourse(@RequestParam String id, HttpServletRequest req) {
        adminService.delCourse(Integer.parseInt(id));
        req.getSession().setAttribute("msg", "????????????");
        return "redirect:/courseManage";
    }

    @RequestMapping(value = "/changeCourse", method = RequestMethod.GET)
    public String changeCourse(@RequestParam String id, HttpServletRequest req) {
        Course course = adminService.getCourseById(Integer.parseInt(id));
        req.getSession().setAttribute("course", course);
        return "WEB-INF/pages/course/changeClz";
    }

    @RequestMapping(value = "/changeCourse", method = RequestMethod.POST)
    public String changeCourse(HttpServletRequest req,
    		@RequestParam("file") MultipartFile file) {
        String msg = null;
        String year_moth = df.format(new Date());
        try {
            req.setCharacterEncoding("utf-8");


            Integer id = (Integer) req.getSession().getAttribute("id");

            Course course = adminService.getCourseById(id);
            
            if(!file.isEmpty()){
		    	ServletContext sc = req.getSession().getServletContext();
		        String dir = sc.getRealPath("/upload/imgurl/"+year_moth+"");    //???????????????????????????
		        String filename = file.getOriginalFilename();    //???????????????????????????
		        String tempfilename = Tools.getRndFilename()+Tools.getFileExtName(filename);          
		      			
				try {
					FileUtils.writeByteArrayToFile(new File(dir,tempfilename), file.getBytes());
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				course.setImgurl("/upload/imgurl/"+year_moth+"/"+tempfilename); //????????????????????????
		        
		    }

            course.setName(req.getParameter("name"));
            course.setAmount(Integer.parseInt(req.getParameter("amount")));
            course.setBelong(req.getParameter("belong"));
            course.setCredit(Integer.parseInt(req.getParameter("credit")));
            course.setPlace(req.getParameter("place"));
            course.setDetail(req.getParameter("detail"));
            course.setTime(req.getParameter("time"));

            if (adminService.updateCourse(course)) {
                msg = "????????????";
            } else {
                msg = "????????????";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            msg = "????????????";
            e.printStackTrace();
        } finally {
            req.getSession().setAttribute("msg", msg);
            return "redirect:/courseManage";
        }
    }

    //???????????????????????????
    @RequestMapping("/chooseManage")
    public String chooseManage(@RequestParam(defaultValue="1")Integer page, 
			@RequestParam(defaultValue="5")Integer rows,HttpServletRequest req,Model model) {
    	Page<StudyInfo> records = adminService.getAllStudyInfo(page,rows);
        req.getSession().setAttribute("records", records.getRows());
        model.addAttribute("page", records);
        return "WEB-INF/pages/student/allChoose";
    }

    @RequestMapping("/delStudyInfo")
    public String delStudyInfo(HttpServletRequest req, @RequestParam String id) {
        String msg = null;
        try {
            Integer stdId = Integer.parseInt(id);
            StudyInfo info = adminService.getStudyById(stdId);
            Course c = adminService.getCourseById(info.getC_id());
            c.setSelected(c.getSelected() - 1);
            adminService.updateCourse(c);
            adminService.delStudyInfo(stdId);
            msg = "????????????";
        } catch (Exception e) {
            msg = "????????????";
            e.printStackTrace();
        } finally {
            req.getSession().setAttribute("msg", msg);
            return "redirect:/chooseManage";
        }
    }


    @RequestMapping("/addChoose")
    public String addChoose(HttpServletRequest req, @RequestParam String stuId, @RequestParam String clzId) {
        String msg = null;
        try {
            Integer cId = Integer.parseInt(clzId);
            Student s = adminService.getStudentById(stuId);
            Course c = adminService.getCourseById(cId);
            if (s != null && c != null) {
                if(c.getAmount()>c.getSelected()) {
                    int rst = studentService.selectCource(stuId, cId);
                    if (rst==0) {
                        msg = "????????????";
                    } else if(rst==1){
                        msg = "??????????????????!";
                    } else if(rst==2){
                        msg="??????????????????!";
                    }else{
                        msg="????????????!";
                    }
                }
            }else{
                msg="????????????";
            }
        } catch (Exception e) {
            msg = "????????????";
            e.printStackTrace();
        } finally {
            req.getSession().setAttribute("msg", msg);
            return "redirect:/chooseManage";
        }
    }

}
