package cn.wxj.service;

import cn.wxj.entity.Course;
import cn.wxj.util.Page;

import java.util.List;


public interface IClzService {

    public List<Course> getAllClz();

    public Course getClzById(Integer id);

    public void update(Course course);

    //分页获取所有课程
	public Page<Course> getAllPageClz(Integer page, Integer rows);

}
