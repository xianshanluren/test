package com.bx.controller.basicInformationSettings;

import com.bx.entity.oa.basicInformationSetting.CourseBasicInfo;
import com.bx.service.CourseBasicInfoService;
import com.java.framework.controller.BaseController;
import com.java.framework.model.DataModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.function.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Description: 课程基本信息Controller
 * Author: HuiHuanHuan
 * Version: 1.0
 * Create Date Time : 2019/2/25 9:55.
 *
 * @see
 */
@RestController
@RequestMapping("courseBasicInfo")
@Api(value = "课程基本信息Controller", description = "课程基本信息", tags = {"课程基本信息接口"})
public class CourseBasicInfoController extends BaseController<CourseBasicInfo, String> {

    public CourseBasicInfoController() {
    }

    @Autowired
    private CourseBasicInfoService courseBasicInfoService;


    /**
     * 数据合法性校验
     * @param entity
     * @param predicate
     * @return
     * @throws Exception
     */
    @Override
    protected DataModel<CourseBasicInfo> validate(CourseBasicInfo entity, Predicate<?> predicate) throws Exception {
        DataModel<CourseBasicInfo> dm = new DataModel.Builder<CourseBasicInfo>().build();
//        int length = entity.getCourseName().length();
//        if(length > 20){
//            dm.setFlag(false).setShortMessage("课程名称不超过20字符");
//            return dm;
//        }
//
//
        // 学分必须保留一位小数
        String credit = entity.getCredit();
        String regex = "^[0-9]+(\\.[0-9])?$";  //符合一位小数的正则
        if (!credit.matches(regex)) {
            dm.setFlag(false).setShortMessage("学分必须保留一位小数");
            return dm;
        }
        // 课程编号唯一性校验
//        Integer count = courseBasicInfoService.findCountByCourseNumber(entity.getCourseNumber());
//        if (count>0) {
//            dm.setFlag(false).setShortMessage("课程编号已存在");
//            return dm;
//        }
        return null;
    }

    /**
     * 课程编号自动生成接口
     * @return
     * @throws Exception
     */
    @PostMapping("autoGenerationCode")
    @ApiOperation("自动生成课程编号")
    public String autoGenerationCode() throws  Exception{
        String courseNumber = courseBasicInfoService.autoGenerationCode();
        return courseNumber;
    }


}
