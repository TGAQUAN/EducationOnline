package cn.wxj.dao.Impl;

import cn.wxj.dao.IAdminDao;
import cn.wxj.entity.Admin;
import cn.wxj.entity.Course;
import cn.wxj.entity.Student;
import cn.wxj.entity.StudyInfo;
import cn.wxj.util.Page;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class AdminDao extends SqlSessionDaoSupport implements IAdminDao {

    private static final String SQL_NAMESPACE = "Admin";

    @Override
    public Admin selectById(String id) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        return this.getSqlSession().selectOne(SQL_NAMESPACE + ".selectById", map);
    }

    @Override
    public Page<Student> selectAllStudents(Integer page,Integer rows) {
    	Map<String, Object> map = new HashMap<String, Object>();
        int total = this.getSqlSession().selectList(SQL_NAMESPACE + ".selectAllStudents").size();
        //分页处理
        map.put("pageNo", (page-1)*rows);
        map.put("rows", rows);
        List<Student> list = this.getSqlSession().selectList(SQL_NAMESPACE + ".selectPageAllStudents", map);
        

        Page<Student> result = new Page<>();
		result.setPage(page);
		result.setSize(rows);
		result.setRows(list);
		result.setTotal(total);
		return result;
    }

    @Override
    public List<Course> selectAllCourses() {
        return this.getSqlSession().selectList(SQL_NAMESPACE + ".selectAllCourses");
    }
    

    //分页获取课程列表
    @Override
	public Page<Course> getPageAllCourses(Integer page, Integer rows) {
    	Map<String, Object> map = new HashMap<String, Object>();
        int total = this.getSqlSession().selectList(SQL_NAMESPACE + ".selectAllCourses").size();
        //分页处理
        map.put("pageNo", (page-1)*rows);
        map.put("rows", rows);
        List<Course> list = this.getSqlSession().selectList(SQL_NAMESPACE + ".selectPageAllCourses", map);
        

        Page<Course> result = new Page<>();
		result.setPage(page);
		result.setSize(rows);
		result.setRows(list);
		result.setTotal(total);
		return result;
	}

    @Override
    public void updateStudent(Student student) {
        Map<String, Student> map = new HashMap<String, Student>();
        map.put("s", student);
        this.getSqlSession().update(SQL_NAMESPACE + ".updateStudent", map);
    }

    @Override
    public Student selectStuById(String id) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        return this.getSqlSession().selectOne(SQL_NAMESPACE + ".selectStuById", map);
    }

    @Override
    public void addStudent(Student student) {
        this.getSqlSession().insert(SQL_NAMESPACE + ".insertStudent", student);
    }

    @Override
    public void delStudent(String id) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        this.getSqlSession().delete(SQL_NAMESPACE + ".delStudent", map);

    }

    @Override
    public void addCourse(Course course) {
        this.getSqlSession().insert(SQL_NAMESPACE + ".insertClz", course);
    }

    @Override
    public void delCourse(Integer id) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("id", id);
        this.getSqlSession().delete(SQL_NAMESPACE + ".delCourse", map);
    }

    @Override
    public Page<StudyInfo> selectAllStudyInfo(Integer page, Integer rows) {
    	 Map<String, Object> map = new HashMap<String, Object>();
         int total =  this.getSqlSession().selectList(SQL_NAMESPACE + ".selectAllStudyInfo").size();
         //分页处理
         map.put("pageNo", (page-1)*rows);
         map.put("rows", rows);
         List<StudyInfo> list = this.getSqlSession().selectList(SQL_NAMESPACE + ".selectPageAllStudyInfo", map);
  
         Page<StudyInfo> result = new Page<>();
 		result.setPage(page);
 		result.setSize(rows);
 		result.setRows(list);
 		result.setTotal(total);
 		return result;
    }

    @Override
    public void addStudyInfo(StudyInfo studyInfo) {
        this.getSqlSession().insert(SQL_NAMESPACE + ".insertStudyInfo", studyInfo);
    }

    @Override
    public void delStudyInfo(Integer id) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("id", id);
        this.getSqlSession().delete(SQL_NAMESPACE + ".delStudyInfo", map);
    }

    @Override
    public StudyInfo selectById(Integer id) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("id", id);
        return this.getSqlSession().selectOne(SQL_NAMESPACE + ".selectByStudyId", map);
    }

    @Override
    public void delStudyInfoByCId(Integer id) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("id", id);
        this.getSqlSession().delete(SQL_NAMESPACE + ".delStudyInfoByCId", map);
    }

    @Override
    public void delStudyInfoBySId(String id) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        this.getSqlSession().delete(SQL_NAMESPACE + "delStudyInfoBySId", map);
    }

	
}
