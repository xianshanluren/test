package com.bx.repository.courseBasicInfo;

import com.bx.entity.oa.basicInformationSetting.CourseBasicInfo;
import com.java.framework.jpa.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Description: 课程基本信息DAO层
 * Author: HuiHuanHuan
 * Version: 1.0
 * Create Date Time : 2019/2/25 0025.
 *
 * @see
 */
public interface CourseBasicInfoRepository extends BaseRepository<CourseBasicInfo, String> {

//    /**
//     * 通过课程名称查找课程信息
//     * @param courseName 课程名称
//     * @return 课程信息
//     */
//    @Query(value = "select * from td_oa_course_basic_info c where c.course_name = :courseName", nativeQuery = true)
//    CourseBasicInformation findCourseByCourseName(@Param("courseName") String courseName);
//
//
    /**
     * 根据课程编号查询
     * @param courseNumber
     * @return 查到的数据条数
     */
//    @Query(value = "select count(*) from tb_oa_course_basic_info c where c.course_number = :courseNumber", nativeQuery = true)
//    Integer findCountByCourseNumber(@Param("courseNumber") String courseNumber);


}