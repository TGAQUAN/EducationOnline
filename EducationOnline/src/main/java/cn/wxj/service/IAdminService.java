package cn.wxj.service;

import cn.wxj.entity.Course;
import cn.wxj.entity.Student;
import cn.wxj.entity.StudyInfo;
import cn.wxj.util.Page;

import java.util.List;



public interface IAdminService {
    public boolean login(String username, String pwd);

    //分页获取学生列表
    public Page<Student> getAllStudents(Integer page,Integer rows);

    
    public List<Course> getAllCourses();

    public boolean updateStudent(Student student);

    public Student getStudentById(String id);

    public boolean addStudent(Student student);

    public void delStudent(String id);

    public boolean addCourse(Course course);

    public boolean updateCourse(Course course);

    public void delCourse(Integer id);

    public Course getCourseById(Integer id);

    public Page<StudyInfo> getAllStudyInfo(Integer page, Integer rows);

    public void addStudyInfo(StudyInfo studyInfo);

    public void delStudyInfo(Integer id);

    public StudyInfo getStudyById(Integer id);
    
    //后台分页获取获取课程列表
	public Page<Course> getPageAllCourses(Integer page, Integer rows);

}
