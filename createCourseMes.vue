<template>
  <Form id="createCourseMes" style="text-align: center;width:100%;text-align: left;" :model="saveData" :rules="ruleInline"  ref="saveData">
    <div style="height:100%;width:100%;" v-show="mainView">
      <div class="add_btn_con">
        <div class="add_btn_inner">
          <div class="add_btn" @click="post_fn('saveData')">提交</div>
          <div class="add_btn" @click="close_page">关闭</div>
        </div>
      </div>
      <div class="addData_container">
        <div class="addinfor_input_top">
          <div class="input_top_title">{{title}}</div>
          <div class="separat_line">课程基本信息</div>
          <hr/>
          <div class="input_top_basic_con">

            <FormItem class="lan_4 show_list" prop="courseName">
              <div class="input_head lan_4">课程名称：</div>
              <div class="input_content lan_8">
                <Input type="text" v-model="saveData.courseName"></Input>
                <span class="required">*</span>
              </div>
            </FormItem>

            <FormItem class="lan_4 show_list" prop="courseNumber">
              <div class="input_head lan_4">课程编号：</div>
              <div class="input_content lan_8">
                <div class="input_content lan_10">{{saveData.courseNumber}}</div>
                <span class="required">*</span>
              </div>
            </FormItem>

            <FormItem class="lan_4 show_list" prop="courseNature">
              <div class="input_head lan_4">课程性质：</div>
              <div class="input_content lan_8">
                <select v-model="saveData.courseNature" transfer>
                  <option value="">---请选择---</option>
                  <option value="必修">必修</option>
                  <option value="选修">选修</option>
                </select>
              </div>
            </FormItem>

            <FormItem class="lan_4 show_list" prop="openingSemester">
              <div class="input_head lan_4">开课学期：</div>
              <div class="input_content lan_8">
                <select v-model="saveData.openingSemester" transfer>
                  <option value="">---请选择---</option>
                  <option value="第一学期">第一学期</option>
                  <option value="第二学期">第二学期</option>
                </select>
              </div>
            </FormItem>

            <FormItem class="lan_4 show_list" prop="openingAcademy">
              <div class="input_head lan_4">开课学院：</div>
              <div class="input_content lan_8">
                <input type="text" @click="receptionGetData" v-model="deptName"/>
                <radio-tree v-show="false" id="positionDept" :treeData="treeData"
                            @radioSetData="receptionSetData"></radio-tree>
                <span class="required">*</span>
              </div>
            </FormItem>

            <FormItem class="lan_4 show_list" prop="credit">
              <div class="input_head lan_4">学分：</div>
              <div class="input_content lan_8">
                <Input type="text" v-model="saveData.credit"></Input>
                <span class="required">*</span>
              </div>
            </FormItem>

            <FormItem class="lan_4 show_list" prop="totalHours">
              <div class="input_head lan_4">总学时：</div>
              <div class="input_content lan_8">
                <Input type="text" v-model="saveData.totalHours"></Input>
                <span class="required">*</span>
              </div>
            </FormItem>

            <FormItem class="lan_4 show_list" prop="theoreticalHours">
              <div class="input_head lan_4">理论学时：</div>
              <div class="input_content lan_8">
                <Input type="text" v-model="saveData.theoreticalHours"></Input>
                <span class="required">*</span>
              </div>
            </FormItem>

            <FormItem class="lan_4 show_list" prop="practicalHours">
              <div class="input_head lan_4">实践学时：</div>
              <div class="input_content lan_8">
                <Input type="text" v-model="saveData.practicalHours"></Input>
                <span class="required">*</span>
              </div>
            </FormItem>

            <FormItem class="lan_4 show_list" prop="assessmentMethod">
              <div class="input_head lan_4">考核方式：</div>
              <div class="input_content lan_8">
                <select v-model="saveData.assessmentMethod" transfer>
                  <option value="">---请选择---</option>
                  <option value="集中笔试">集中笔试</option>
                  <option value="论文(设计)">论文(设计)</option>
                  <option value="形成性考核">形成性考核</option>
                  <option value="机考">机考</option>
                </select>
              </div>
            </FormItem>

            <div class="clear:both;"></div>
          </div>

        </div>

      </div>
    </div>
    <alert-success
      :showSuccess="showSuccess"
      @closeSuccess="closeSuccess"
      @confirmSuccess="confirmSuccess"
    ></alert-success>
    <alert-add :showAddBox="showAddBox" @closeAddBox="closeAddBox" @confirmAddBox="confirmAddBox"></alert-add>
    <alert-failure :showFailure="showFailure" :failureCon="failureCon" @closeFailure="closeFailure"></alert-failure>
  </Form>
</template>

<script>
  import alertAdd from './../../../../../common/alert/alertAdd'
  import alertSuccess from './../../../../../common/alert/alertSuccess'
  import alertFailure from './../../../../../common/alert/alertFailure'
  import radioTree from './../../../../../common/tree/radioTree'
  import finder from './../../../../../common/finder/finder'

  export default {
    name: "createCourseMes",
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
        title: '课程新增',
        autoGenerationCode:"",
        active_index: 1,
        search: false,
        success: false,
        saveData: {},
        data: {},
        params: {
          url: this.axios.$host_oa + "/courseBasicInfo/autoGenerationCode", //必须参数
          payload: {},//必须参数.可以在created()初始化
          method: "post",
          data: "datas"
        },
        datas: [],
        deptNumber:'',
        mainView: true,
        breakDownView: false,
        deptName: '',
        treeData: '',
        postSetList:[],
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
            { required: true, message: '学分为必选字段,且保留一位小数', trigger: 'blur'},
            { type:"string", pattern:/^[0-9]+([.]{1}[0-9])?$/, message: '学分必须保留一位小数', trigger: 'blur'}
          ],
          totalHours: [
            { required: true, message: '总学时为必选字段', trigger: 'blur'}
          ],
          theoreticalHours: [
            { required: true, message: '理论学时为必选字段', trigger: 'blur'}
          ],
          practicalHours: [
            { required: true, message: '实践学时为必选字段', trigger: 'blur'}
          ],
          assessmentMethod: [
            { required: true, message: '请选择考核方式', trigger: 'change'}
          ]
        },
        userNameList: [],
        defaultData:{},
        propsArray: ['realName', 'deptName'],
        columnTitle:[["deptName","部门"],['realName','用户名']]
      }
    },
    created() {
      let engine1 = this.$post(this.axios.$host_oa + "/courseBasicInfo/autoGenerationCode");
      engine1.then(data => {
          this.saveData.courseNumber = data;
      });
    },
    beforeCreate: function() {
      let req1 = this.initParams(0, 1000);
      let engine1 = this.$post(this.axios.$host_oa + "/newDeptInfo/list", req1);
      engine1.then(data => {
        if (data.flag) {
          this.merchantNameList = data.datas;
          this.finderHide = true;
        } else {
          this.failureCon = "系统繁忙,请稍后再试";
          this.showFailure = true;
        }
      });
    },

    mounted() {
      this.$api.departmentData().then((data) => {
        data.name = 'deptName';
        this.treeData = data;
      });
    },
    methods: {
      finderss:function (){
        let data = this.initParams(0, 1000);
        let deptCondition = {
          colName: 'deptNumber',
          ruleType: 'EQ',
          value: this.deptNumber
        };
        data.condition.push(deptCondition);
        let engine = this.$post(this.axios.$host_auth+"/user/list", data);
        engine.then(data => {
          if (data.flag) {
            this.userNameList = data.datas;
            this.finderHide=true;
            console.info('222',data.datas.length);
          } else {
            console.info('222',this.userNameList);
            this.index = layer.open({
              icon: 1, //图标
              area: ['200px', '160px'],
              content: '!'
            });
          }
        });
      },
      //获取选择的岗位设置的数据
      getPostData: function (data) {
        this.postSetList = data;
      },

      closePop: function () {
        layer.close(this.index);
      },
      //部门组件
      radioGetData: function () {
        this.$util.openPop('#positionDept')
      },
      receptionGetData: function () {
        this.$util.openPop('#positionDept')
      },
      //部门组件赋值
      receptionSetData: function (data) {
        this.finderHide = false;
        this.deptName = data.deptName;
        this.saveData.openingAcademy = data.deptName;
        this.saveData.deptNumber = data.deptNumber;
        this.deptNumber = data.deptNumber;
        console.info('deptNumber',this.deptNumber);
        this.finderss();
        layer.close(layer.index);
      },
      getData: function (data) {
        this.saveData.courseNumber = data.value;
      },

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
      closeSuccess: function (data) {
        this.showSuccess = data;
        window.close();
      },
      //点击确认关闭方法
      confirmSuccess : function (data) {
        this.showSuccess = data;
        window.close();
      },
      post_fn: function (name) {
        this.$refs[name].validate((valid) => {
          if (valid) {
            this.showAddBox = true;
          } else {
            this.failureCon = "请检查输入信息!";
            this.showFailure = true;
          }
        })
      },
      closeFailure(data) {
        this.showFailure = data;
      },
      search_fn: function () {
        this.search = !this.search;
      },
      save_budget: function () {
        let this_ = this;
        let tip = layer.load(1, {
          shade: [0.75, '#000'] //0.1透明度的白色背景
        });
        let engine = this.$post(this.axios.$host_oa+"/courseBasicInfo/save", this.saveData);
        engine.then(data => {
          if (data.flag) {
            setTimeout(() => {
              this_.showSuccess = true;
              this_.item = '';
              layer.close(tip);
            }, 200);
          } else {
            setTimeout(() => {
              this_.failureCon = "保存失败！";
              this_.showFailure = true;
              layer.close(tip);
            }, 1500);
          }
        });
      },
      close_page: function () {
        window.close();
      },
      tab_change: function (index) {
        this.active_index = index;
      },
    }
  }
</script>

<style type="text/css" lang="scss">
  #courseBasicInfoAdd .ivu-form-item-error-tip {
    /*top: 25px;*/
    left: 107px;
  }

  .loadBtn[data-v-2cc66588] {
    background: #409eff !important;
    color: white !important;
  }
</style>


<style>

</style>
