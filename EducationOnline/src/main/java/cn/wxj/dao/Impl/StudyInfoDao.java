package cn.wxj.dao.Impl;

import cn.wxj.dao.IStudyInfoDao;
import cn.wxj.entity.StudyInfo;
import cn.wxj.util.Page;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class StudyInfoDao extends SqlSessionDaoSupport implements IStudyInfoDao {

    private static final String SQL_NAMESPACE = "StudyInfo";


    //查看选择该课程的所有学生列表
    @Override
    public Page<StudyInfo> selectByClzId(Integer clzId,Integer page,Integer rows) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("clzId", clzId);
        int total =  this.getSqlSession().selectList(SQL_NAMESPACE + ".selectByClzId", map).size();
        //分页处理
        map.put("pageNo", (page-1)*rows);
        map.put("rows", rows);
        List<StudyInfo> list = this.getSqlSession().selectList(SQL_NAMESPACE + ".selectPageByClzId", map);
 
        Page<StudyInfo> result = new Page<>();
		result.setPage(page);
		result.setSize(rows);
		result.setRows(list);
		result.setTotal(total);
		return result;
    }


    //前台我的课程
    @Override
    public Page<StudyInfo> selectByStuId(String stuId,Integer page,Integer rows) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("stuId", stuId);
        
        //分页处理
        map.put("pageNo", (page-1)*rows);
        map.put("rows", rows);
        List<StudyInfo> list = this.getSqlSession().selectList(SQL_NAMESPACE + ".selectPageByStuId", map);
        
        
        int total = this.getSqlSession().selectList(SQL_NAMESPACE + ".selectByStuId", map).size();
        Page<StudyInfo> result = new Page<>();
		result.setPage(page);
		result.setSize(rows);
		result.setRows(list);
		result.setTotal(total);
		return result;
    }

    @Override
    public StudyInfo check(String stuId, Integer clzId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("stuId", stuId);
        map.put("clzId", clzId);

        return this.getSqlSession().selectOne(SQL_NAMESPACE + ".selectByStuIdAndClzId", map);
    }

    @Override
    public void insert(StudyInfo studyInfo) {
        this.getSqlSession().insert(SQL_NAMESPACE + ".insert", studyInfo);
    }

    @Override
    public void delete(String stuId, Integer clzId) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("stuId", stuId);
        map.put("clzId", clzId);

        this.getSqlSession().delete(SQL_NAMESPACE + ".delete", map);
    }

}
