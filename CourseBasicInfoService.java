package com.bx.service;

import com.bx.entity.oa.basicInformationSetting.CourseBasicInfo;
import com.java.framework.service.BaseService;

/**
 * Description: 课程基本信息业务层接口类
 * Author: HuiHuanHuan
 * Version: 1.0
 * Create Date Time : 2019/2/25 0025.
 *
 * @see
 */
public interface CourseBasicInfoService extends BaseService<CourseBasicInfo,String>{
//    // 根据课程名称查询课程信息
//    CourseBasicInformation findCourseByCourseName(String courseName) throws Exception;
//
    //根据课程编号查询数据条数
//    Integer findCountByCourseNumber(String courseNumber) throws Exception;

    String autoGenerationCode() throws Exception;
}
