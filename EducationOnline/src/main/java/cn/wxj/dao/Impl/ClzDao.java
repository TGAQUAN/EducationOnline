package cn.wxj.dao.Impl;

import cn.wxj.dao.IClzDao;
import cn.wxj.entity.Course;
import cn.wxj.util.Page;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Repository
public class ClzDao extends SqlSessionDaoSupport implements IClzDao {

    private static final String SQL_NAMESPACE = "Clz";

    @Override
    public List<Course> selectAll() {
        return this.getSqlSession().selectList(SQL_NAMESPACE + ".selectAll");
    }

    @Override
    public Course selectById(Integer id) {

        return this.getSqlSession().selectOne(SQL_NAMESPACE + ".selectById", id);
    }

    @Override
    public void update(Course course) {
        this.getSqlSession().update(SQL_NAMESPACE + ".update", course);
    }


     //分页获取所有课程
	@Override
	public Page<Course> getAllPageClz(Integer page, Integer rows) {
		
		//分页处理
		Map<String,Object> map=new HashMap<String, Object>();
	    map.put("pageNo", (page-1)*rows);
        map.put("rows", rows);
		List<Course> list = this.getSqlSession().selectList(SQL_NAMESPACE + ".selectPageAll", map);
		int total = this.getSqlSession().selectList(SQL_NAMESPACE + ".selectAll").size();
		Page<Course> result = new Page<>();
		result.setPage(page);
		result.setSize(rows);
		result.setRows(list);
		result.setTotal(total);
		return result;
	}
}
