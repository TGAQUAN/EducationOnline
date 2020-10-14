package cn.wxj.service.Impl;

import cn.wxj.dao.IClzDao;
import cn.wxj.entity.Course;
import cn.wxj.service.IClzService;
import cn.wxj.util.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClzService implements IClzService {

    @Autowired
    private IClzDao clzDao;

    @Override
    public List<Course> getAllClz() {
        return clzDao.selectAll();
    }

    @Override
    public Course getClzById(Integer id) {
        return clzDao.selectById(id);
    }

    @Override
    public void update(Course course) {
        clzDao.update(course);
    }

    /**
     * 功能描述：分页获取所有课程
     */
	@Override
	public Page<Course> getAllPageClz(Integer page, Integer rows) {
		
		return clzDao.getAllPageClz(page,rows);
	}
}
