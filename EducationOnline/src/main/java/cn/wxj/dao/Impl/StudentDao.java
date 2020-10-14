package cn.wxj.dao.Impl;

import cn.wxj.dao.IStudentDao;
import cn.wxj.entity.Student;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class StudentDao extends SqlSessionDaoSupport implements IStudentDao {

    private static final String SQL_NAMESPACE = "Student";

    @Override
    public void insert(Student student) {
        this.getSqlSession().insert(SQL_NAMESPACE + ".insert", student);
    }

    @Override
    public void update(Student student) {
        this.getSqlSession().update(SQL_NAMESPACE + ".update", student);
    }

    @Override
    public void delete(@Param("id")String id) {
        this.getSqlSession().delete(SQL_NAMESPACE + ".delete", id);
    }

    @Override
    public Student selectById(@Param("id")String id) {
        Map<String,String> map=new HashMap<String, String>();
        map.put("id",id);
        return this.getSqlSession().selectOne(SQL_NAMESPACE + ".selectById", map);
    }
}
