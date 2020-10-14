package cn.wxj.dao;

import cn.wxj.entity.Admin;
import cn.wxj.entity.Course;
import cn.wxj.entity.Student;
import cn.wxj.entity.StudyInfo;
import cn.wxj.util.Page;

import java.util.List;


public interface IAdminDao {

    public Admin selectById(String id);

    public Page<Student> selectAllStudents(Integer page,Integer rows);

    public List<Course> selectAllCourses();

    public void updateStudent(Student student);

    public Student selectStuById(String id);

    public void addStudent(Student student);

    public void delStudent(String id);

    public void addCourse(Course course);

    public void delCourse(Integer id);

    //分页获取选课信息
    public Page<StudyInfo> selectAllStudyInfo(Integer page, Integer rows);

    public void addStudyInfo(StudyInfo studyInfo);

    public void delStudyInfo(Integer id);

    public StudyInfo selectById(Integer id);

    public void delStudyInfoByCId(Integer id);

    public void delStudyInfoBySId(String id);

    //分页获取所有课程
	public Page<Course> getPageAllCourses(Integer page, Integer rows);
}
