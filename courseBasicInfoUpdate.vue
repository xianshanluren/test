<template>
  <Form id="courseBasicInfoUpdate" :model="data" :rules="ruleInline" ref="data">
    <div style="height:100%;width:100%;" v-show="mainView">
      <div class="add_btn_con">
        <div class="add_btn_inner">
          <!-- <div class="add_btn" @click="jump_page">增加岗位设置</div>-->
          <div class="add_btn" @click="save_budget('data')">提交</div>
          <div class="add_btn" @click="close_page">关闭</div>
        </div>
      </div>
      <div class="addData_container">
        <div class="addinfor_input_top">
          <div class="input_top_title">{{title}}</div>
          <div class="separat_line">课程基本信息</div>
          <hr>
          <div class="input_top_basic_con">
            <FormItem class="lan_4 show_list" prop="courseName">
              <div class="input_head lan_4">课程名称：</div>
              <div class="input_content lan_8">
                <Input type="text" v-model="data.courseName"></Input>
                <span class="required">*</span>
              </div>
            </FormItem>

            <!--<FormItem class="lan_4 show_list" prop="courseNumber">-->
              <!--<div class="input_head lan_4">课程编号：</div>-->
              <!--<div class="input_content lan_8">-->
                <!--<div class="input_content lan_10">{{data.courseNumber}}</div>-->
                <!--<span class="required">*</span>-->
              <!--</div>-->
            <!--</FormItem>-->

            <FormItem class="lan_4 show_list" prop="courseNature">
              <div class="input_head lan_4">课程性质：</div>
              <div class="input_content lan_8">
                <Select v-model="data.courseNature">
                  <Option value>---请选择---</Option>
                  <Option value="必修">必修</Option>
                  <Option value="选修">选修</Option>
                </Select>
              </div>
            </FormItem>

            <FormItem class="lan_4 show_list" prop="openingSemester">
              <div class="input_head lan_4">开课学期：</div>
              <div class="input_content lan_8">
                <Select v-model="data.openingSemester">
                  <Option value>---请选择---</Option>
                  <Option value="第一学期">第一学期</Option>
                  <Option value="第二学期">第二学期</Option>
                </Select>
              </div>
            </FormItem>

            <FormItem class="lan_4 show_list" prop="openingAcademy">
              <div class="input_head lan_4">开课学院：</div>
              <div class="input_content lan_8">
                <input type="text" @click="receptionGetData" v-model="deptName" readonly="readonly">
                <radio-tree
                  v-show="false"
                  id="positionDept"
                  :treeData="treeData"
                  @radioSetData="receptionSetData"
                ></radio-tree>
                <span class="required">*</span>
              </div>
            </FormItem>

            <FormItem class="lan_4 show_list" prop="credit">
              <div class="input_head lan_4">学分：</div>
              <div class="input_content lan_8">
                <Input type="text" v-model="data.credit"></Input>
                <span class="required">*</span>
              </div>
            </FormItem>

            <FormItem class="lan_4 show_list" prop="totalHours">
              <div class="input_head lan_4">总学时：</div>
              <div class="input_content lan_8">
                <Input type="text" v-model="data.totalHours"></Input>
                <span class="required">*</span>
              </div>
            </FormItem>

            <FormItem class="lan_4 show_list" prop="assessmentMethod">
              <div class="input_head lan_4">考核方式：</div>
              <div class="input_content lan_8">
                <Select v-model="data.assessmentMethod">
                  <Option value>---请选择---</Option>
                  <Option value="集中笔试">集中笔试</Option>
                  <Option value="论文(设计)">论文(设计)</Option>
                  <Option value="形成性考核">形成性考核</Option>
                  <Option value="机考">机考</Option>
                </Select>
              </div>
            </FormItem>

            <div class="clear:both;"></div>
          </div>
        </div>
      </div>
    </div>

    <alert-add
      v-show="showAddBox"
      :showAddBox="showAddBox"
      @closeAddBox="closeAddBox"
      @confirmAddBox="confirmAddBox"
    ></alert-add>
    <alert-success
      v-show="showSuccess"
      :showSuccess="showSuccess"
      @closeSuccess="closeSuccess"
      @confirmSuccess="confirmSuccess"
    ></alert-success>
    <alert-failure
      v-show="showFailure"
      :showFailure="showFailure"
      :failureCon="failureCon"
      @closeFailure="closeFailure"
    ></alert-failure>
  </Form>
</template>

<script>
  import alertAdd from "./../../../../../common/alert/alertAdd";
  import alertSuccess from "./../../../../../common/alert/alertSuccess";
  import alertFailure from "./../../../../../common/alert/alertFailure";
  import radioTree from "./../../../../../common/tree/radioTree";
  import finder from "./../../../../../common/finder/finder";

  export default {
    name: "courseBasicInfoUpdate",
    components: {
      alertAdd,
      alertSuccess,
      alertFailure,
      radioTree,
      finder
    },
    data() {
      return {
        finderHide: false,
        showAddBox: false,
        showSuccess: false,
        showFailure: false,
        failureCon: "",
        title: "课程变更",
        active_index: 1,
        search: false,
        success: false,
        saveData: {},
        mainView: true,
        breakDownView: false,
        deptName: "",
        treeData: "",
        deptNumber: "",
        postSetList: [] ,
        data: {},
        ruleInline: {
          courseName: [
            { required: true, message: '课程名称为必填字段', trigger: 'blur'}
          ],
          courseNature: [
            { required: true, message: '请选择课程性质', trigger: 'change'}
          ],
          openingSemester: [
            { required: true, message: '请选择开课学期', trigger: 'change'}
          ],
          openingAcademy: [
            { required: true, message: '请选择开课学院', trigger: 'change'}
          ],
          credit: [
            { required: true, message: '学分为必选字段', trigger: 'blur'},
            { type:"string", pattern:/^[0-9]+([.]{1}[0-9])?$/, message: '学分必须保留一位小数', trigger: 'blur'}
          ],
          totalHours: [
            { required: true, message: '总学时为必选字段', trigger: 'blur'}
          ],
          assessmentMethod: [
            { required: true, message: '请选择考核方式', trigger: 'change'}
          ]
        },
        userNameList: [],
        defaultData: {},
        propsArray: ["realName", "deptName"],
        columnTitle: [["deptName", "部门"], ["realName", "用户名"]]
      };
    },
    beforeCreate: function() {
      let id = this.$route.query.id;
      let engine = this.$post(
        this.axios.$host_oa + "/courseBasicInfo/form/" + id
      );
      engine.then(data => {
        this.data = data.data;

        // this.defaultData.defaultName = this.data.courseNumber;
        this.finderHide = true;
        this.deptName = this.data.openingAcademy;
        this.deptNumber = this.data.deptNumber;
        this.finderss();
        //console.info("data",this.data);
      });
    },
    mounted() {
      this.$api.departmentData().then(data => {
        data.name = "deptName";
        this.treeData = data;
      });
    },
    created() {
    },
    methods: {
      finderss: function() {
        let data = this.initParams(0, 1000);
        let deptCondition = {
          colName: "deptNumber",
          ruleType: "EQ",
          value: this.deptNumber
        };
        data.condition.push(deptCondition);
        let engine = this.$post(this.axios.$host_auth + "/user/list", data);
        engine.then(data => {
          if (data.flag) {
            this.userNameList = data.datas;
            this.finderHide = true;
            console.info("222", data.datas.length);
          } else {
            setTimeout(() => {
              this_.failureCon = "修改失败！";
              this_.showFailure = true;
              layer.close(tip);
            }, 1500);
          }
        });
      },

      //获取选择的岗位设置的数据
      getPostData: function(data) {
        this.postSetList = data;
      },

      closePop: function() {
        layer.close(this.index);
      },
      //部门组件
      radioGetData: function() {
        this.$util.openPop("#positionDept");
      },
      receptionGetData: function() {
        this.$util.openPop("#positionDept");
      },
      //部门组件赋值
      receptionSetData: function(data) {
        this.deptName = data.deptName;
        this.data.openingAcademy = data.deptName;
        this.data.deptNumber = data.deptNumber;
        this.deptNumber = data.deptNumber;
        this.finderss();
        layer.close(layer.index);
      },
      // getData: function(data) {
      //   this.data.courseNumber = data.value;
      // },
      // 确认添加
      confirmAddBox(data) {
        this.showAddBox = data;
        //调用添加方法
        this.save_budget();
      },
      // 取消以及关闭添加弹框
      closeAddBox(data) {
        this.showAddBox = data;
      },
      //"右上角关闭"方法
      closeSuccess: function(data) {
        this.showSuccess = data;
        window.close();
      },
      //点击确认关闭方法
      confirmSuccess: function(data) {
        this.showSuccess = data;
        window.close();
      },
      post_fn: function(name) {
        this.$refs[name].validate(valid => {
          if (valid) {
            this.showAddBox = true;
          } else {
            this.failureCon = "请检查输入信息!";
            this.showFailure = true;
          }
        });
      },
      closeFailure(data) {
        this.showFailure = data;
      },
      search_fn: function() {
        this.search = !this.search;
      },
      save_budget: function(name) {
        let this_ = this;
        this.$refs[name].validate(valid => {
          if (valid) {
            let tip = layer.load(1, {
              shade: [0.75, "#000"] //0.1透明度的白色背景
            });
            let engine = this.$post(
              this.axios.$host_oa + "/courseBasicInfo/update",
              this.data
            );
            engine.then(data => {
              if (data.flag) {
                setTimeout(() => {
                  this_.showSuccess = true;
                  this_.item = "";
                  layer.close(tip);
                }, 200);
              } else {
                setTimeout(() => {
                  this_.failureCon = "修改失败！";
                  this_.showFailure = true;
                  layer.close(tip);
                }, 1500);
              }
            });
          } else {
            this.failureCon = "请检查输入信息!";
            this.showFailure = true;
          }
        });
      },
      close_page: function() {
        window.close();
      },
      tab_change: function(index) {
        this.active_index = index;
      }
    }
  };
</script>

<style type="text/css" lang="scss">
  #courseBasicInfo .ivu-form-item-error-tip {
    top: 25px;
    left: 107px;
  }

  .loadBtn[data-v-2cc66588] {
    background: #409eff !important;
    color: white !important;
  }
</style>
<style scoped>
</style>
