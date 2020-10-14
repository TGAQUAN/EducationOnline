package cn.wxj.dao;

import cn.wxj.entity.StudyInfo;
import cn.wxj.util.Page;


public interface IStudyInfoDao {

    //查看选择该课程的所有学生列表
    public Page<StudyInfo> selectByClzId(Integer clzId,Integer page,Integer rows);

    //分页获取我的选课列表
    public Page<StudyInfo> selectByStuId(String stuId,Integer page,Integer rows);

    public StudyInfo check(String stuId,Integer clzId);

    public void insert(StudyInfo studyInfo);

    public void delete(String stuId,Integer clzId);

}
