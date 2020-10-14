package cn.wxj.service;

import cn.wxj.entity.StudyInfo;
import cn.wxj.util.Page;


public interface IStudyService {

    public Page<StudyInfo> getAllClzByStuId(String stuId,Integer page,Integer rows);

    //查看选择该课程的所有学生列表
    public Page<StudyInfo> getAllStuByClzId(Integer clzId,Integer page,Integer rows);

    public void delCourse(String stuId,Integer clzId);

}
