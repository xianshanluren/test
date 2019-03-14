package com.bx.service.impl.courseBasicInfo;
import com.bx.entity.oa.basicInformationSetting.CourseBasicInfo;
import com.bx.repository.courseBasicInfo.CourseBasicInfoRepository;
import com.bx.service.CourseBasicInfoService;
import com.java.framework.redis.redisson.SequenceNumberGeneratorUtil;
import com.java.framework.service.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description: 课程基本信息业务层实现类
 * Author: HuiHuanHuan
 * Version: 1.0
 * Create Date Time : 2019/2/25 0025.
 *
 * @see
 */
@Service
@Transactional(readOnly = true)
public class CourseBasicInfoServiceImpl extends BaseServiceImpl<CourseBasicInfo, String> implements CourseBasicInfoService {

//    @Autowired
//    private CourseBasicInfoRepository courseBasicInfoRepository;

    public CourseBasicInfoServiceImpl() {
    }



//    @Override
//    @Transactional
//    public <S extends CourseBasicInfo> S save(S entity) throws Exception {
////        if (entity != null) {
////            String courseNumber = entity.getCourseNumber();
////            if(StringUtils.isEmpty(courseNumber)){
////                String kcbh = SequenceNumberGeneratorUtil.getSequenceNumber("KC",false);
////                entity.setCourseNumber(kcbh);
////            }
////        }
////        return super.save(entity);
//    }

    @Override
    public String autoGenerationCode () throws Exception{
        String courseNumber = SequenceNumberGeneratorUtil.getSequenceNumber("KC",false);
        return courseNumber;
    }

    //    @Override
//    @Transactional
//    public CourseBasicInformation findCourseByCourseName(String courseName) throws Exception{
//        CourseBasicInformation basicInformationSetting = this.courseBasicInfoRepository.findCourseByCourseName(courseName);
//        return basicInformationSetting;
//    }
//
//
    /**
     * 根据课程编号查询数据量
     * @param courseNumber
     * @return 查到的数据条数
     * @throws Exception
     */
//    @Override
//    @Transactional
//    public Integer findCountByCourseNumber (String courseNumber)throws Exception{
//        return courseBasicInfoRepository.findCountByCourseNumber(courseNumber);
//    }



}
