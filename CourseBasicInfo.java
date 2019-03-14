package com.bx.entity.oa.basicInformationSetting;
import com.bx.common.BaseEntity;
import javax.persistence.*;

/**
 * Description: 课程基本信息实体类
 * Author: HuiHuanHuan
 * Version: 1.0
 * Create Date Time : 2019/2/25 0025.
 *
 * @see
 */

@Entity
@Table(name = "tb_oa_course_basic_info")
@NamedQuery(name = "CourseBasicInfo.findAll", query = "SELECT b FROM CourseBasicInfo b")
public class CourseBasicInfo extends BaseEntity<String> {

    private static final long serialVersionUID = 1L;

    public CourseBasicInfo() {
    }


  /*  @Column(name = "id")
    private String Id; // ID
*/
    @Column(name = "course_number")
    private String courseNumber; //课程编号

    @Column(name = "course_name")
    private String courseName; //课程名称

    @Column(name = "course_nature")
    private String courseNature; //课程性质

    @Column(name = "opening_semester")
    private String openingSemester; //开课学期

    @Column(name = "opening_academy")
    private String openingAcademy; //开课学院

    @Column(name = "credit")
    private String credit; //学分

    @Column(name = "total_hours")
    private String totalHours; //总学时

    @Column(name = "theoretical_hours")
    private String theoreticalHours; //理论学时

    @Column(name = "practical_hours")
    private String practicalHours;  //实践学时

    @Column(name = "assessment_method")
    private String assessmentMethod; //考核方式

    @Column(name = "dept_number")
    private String deptNumber; // 学院编号

    public String getDeptNumber() {
        return deptNumber;
    }

    public void setDeptNumber(String deptNumber) {
        this.deptNumber = deptNumber;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseNature() {
        return courseNature;
    }

    public void setCourseNature(String courseNature) {
        this.courseNature = courseNature;
    }

    public String getOpeningSemester() {
        return openingSemester;
    }

    public void setOpeningSemester(String openingSemester) {
        this.openingSemester = openingSemester;
    }

    public String getOpeningAcademy() {
        return openingAcademy;
    }

    public void setOpeningAcademy(String openingAcademy) {
        this.openingAcademy = openingAcademy;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(String totalHours) {
        this.totalHours = totalHours;
    }

    public String getTheoreticalHours() {
        return theoreticalHours;
    }

    public void setTheoreticalHours(String theoreticalHours) {
        this.theoreticalHours = theoreticalHours;
    }

    public String getPracticalHours() {
        return practicalHours;
    }

    public void setPracticalHours(String practicalHours) {
        this.practicalHours = practicalHours;
    }

    public String getAssessmentMethod() {
        return assessmentMethod;
    }

    public void setAssessmentMethod(String assessmentMethod) {
        this.assessmentMethod = assessmentMethod;
    }

    @Override
    public String toString() {
        return "CourseBasicInfo{" +
                "courseNumber='" + courseNumber + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseNature='" + courseNature + '\'' +
                ", openingSemester='" + openingSemester + '\'' +
                ", openingAcademy='" + openingAcademy + '\'' +
                ", credit='" + credit + '\'' +
                ", totalHours='" + totalHours + '\'' +
                ", theoreticalHours='" + theoreticalHours + '\'' +
                ", practicalHours='" + practicalHours + '\'' +
                ", assessmentMethod='" + assessmentMethod + '\'' +
                ", deptNumber='" + deptNumber + '\'' +
                '}';
    }
}
