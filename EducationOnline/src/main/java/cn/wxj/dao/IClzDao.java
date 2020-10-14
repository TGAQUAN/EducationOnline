package cn.wxj.dao;

import cn.wxj.entity.Course;
import cn.wxj.util.Page;

import java.util.List;


public interface IClzDao {

    public List<Course> selectAll();

    public Course selectById(Integer id);

    public void update(Course course);

    //分页获取所有课程
	public Page<Course> getAllPageClz(Integer page, Integer rows);


}
