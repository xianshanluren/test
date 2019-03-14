<template>
  <div style="height:100%;width:100%;" id="businessTripList">
    <div class="newCreatePage_head">
      <div class="newCreatePage_head_left_icon">
        <i style="font-size:40px;color:#FFF;" class="iconfont icon-liucheng"></i>
      </div>
      <div class="newCreatePage_head_left_con" style="width: calc(100% - 60px);">
        <div style="height:38px;font-weight:bold;font-size:22px;text-align: left">课程基本信息</div>
        <div style="height:24px;width:100%;">
          <div class="listShow_more" @click.stop="optlist(this)">
            <i style="font-size:20px;" class="iconfont icon-more"></i>
            <div class="optlist">
              <div class="optmenu" @click="jump_page2">新增</div>
            </div>
          </div>
          <div class="listShow_search_icon" @click="search_fn">
            <span>高级搜索</span>
            <i class="iconfont icon-sousuo"></i>
          </div>
        </div>
      </div>
    </div>
    <div class="listShow_body" style>
      <div class="listShow_search_con" v-show="search">
        <div class="listShow_input_con">
          <div class="listShow_input_head">
            <i  class="iconfont icon-gengduo"></i>
            <span>常用条件</span>
          </div>
          <div class="listShow_input_body">
            <div class="search_input_list">
              <div class="search_input_title">课程名称</div>
              <div class="search_input_opt">
                <input type="text" v-model="courseName">
              </div>
            </div>
            <div class="search_input_list">
              <div class="search_input_title">课程编号</div>
              <div class="search_input_opt">
                <input type="text" v-model="courseNumber">
              </div>
            </div>
            <div class="search_input_list">
              <div class="search_input_title">课程性质</div>
              <div class="search_input_opt">
                <select name v-model="courseNature" style="display:block;">
                  <option value>---请选择---</option>
                  <option value="必修">必修</option>
                  <option value="选修">选修</option>
                </select>
              </div>
            </div>
            <div class="search_input_list">
              <div class="search_input_title">开课学期</div>
              <div class="search_input_opt">
                <select name v-model="openingSemester" style="display:block;">
                  <option value>---请选择---</option>
                  <option value="第一学期">第一学期</option>
                  <option value="第二学期">第二学期</option>
                </select>
              </div>
            </div>
            <div class="search_input_list">
              <div class="search_input_title">开课学院</div>
              <div class="search_input_opt">
                <input type="text" v-model="openingAcademy">
              </div>
            </div>
            <div class="search_input_list">
              <div class="search_input_title">考核方式</div>
              <div class="search_input_opt">
                <select name v-model="assessmentMethod" style="display:block;">
                  <option value>---请选择---</option>
                  <option value="集中笔试">集中笔试</option>
                  <option value="论文(设计)">论文(设计)</option>
                  <option value="形成性考核">形成性考核</option>
                  <option value="机考">机考</option>
                </select>
              </div>
            </div>
          </div>
          <div class="listShow_input_footer">
            <span class="listShow_input_footer_btn" @click="search_data">搜索</span>
            <span class="listShow_input_footer_btn" @click="reset_search">重置</span>
            <span class="listShow_input_footer_btn" style="border: none;" @click="search_fn">取消</span>
          </div>
        </div>
      </div>
      <div class="listShow_table_con">
        <table class="table table-hover" style="margin-bottom: 0;" v-show="active_index ==1">
          <thead>
          <th>
            <!-- <input class="lancheckbox" type="checkbox">
            <span>全选</span>-->
          </th>
          <th>课程编号</th>
          <th>课程名称</th>
          <th>课程性质</th>
          <th>开课学期</th>
          <th>开课学院</th>
          <th>学分</th>
          <th>总学时</th>
          <th>考核方式</th>
          <th>操作</th>
          </thead>
          <tbody>
          <tr v-for="(list, index) in datas" :key="index" id="courseBasicInfoList" @click="jump_page(list.id)">
            <td>
              <!-- <input class="lancheckbox" type="checkbox" :value="index"> -->
            </td>

            <td v-cloak>
              <span class="linkspan" >{{list.courseNumber}}</span>
            </td>
            <td v-cloak>
              <span class="linkspan" >{{list.courseName}}</span>
            </td>
            <td v-cloak>
              <span class="linkspan" >{{list.courseNature}}</span>
            </td>
            <td v-cloak>
              <span class="linkspan" >{{list.openingSemester}}</span>
            </td>
            <td v-cloak>
              <span class="linkspan" >{{list.openingAcademy}}</span>
            </td>
            <td v-cloak>
              <span class="linkspan" >{{list.credit}}</span>
            </td>
            <td v-cloak>
              <span class="linkspan" >{{list.totalHours}}</span>
            </td>
            <td v-cloak>
                <span class="linkspan">
                  <div class="omit" :title="list.assessmentMethod">{{list.assessmentMethod}}</div>
                </span>
            </td>
            <td style="color: #00A9E7;">
              <!--<a @click="deleteFin(list.id)">删除</a>-->
              <!--<a @click="update(list.id)" style="margin-left: 10px">编辑</a>-->
              <span
                class="btn-sty1 ed ed3"
                style="color: white;"
                @click.stop="delete_data(list.id)"
                title="删除"
              ></span>
              <span
                class="btn-sty1 ed ed1"
                style="color: white;"
                @click.stop="update(list.id)"
                title="编辑"
              ></span>
            </td>

            <!--<td>
            <a class="btn-sty1 ed ed3" style="color: white;" @click="delete_data(list.id)" title="删除"></a>
            <a class="btn-sty1 ed ed1" style="color: white;" @click="update(list.id)" title="编辑"></a>
            </td>-->
          </tr>
          </tbody>
        </table>
      </div>
      <div class="listShow_change_page">
        <list-page></list-page>
      </div>
    </div>
    <alert-success
      :showSuccess="showSuccess"
      :successMsg="successMsg"
      @closeSuccess="closeSuccess"
      @confirmSuccess="confirmSuccess"
    ></alert-success>
    <alert-failure :showFailure="showFailure" :failureCon="failureCon" @closeFailure="closeFailure"></alert-failure>
    <alert-add
      :showAddBox="showAddBox"
      :addMsg="addMsg"
      @closeAddBox="closeAddBox"
      @confirmAddBox="confirmAddBox"
    ></alert-add>
  </div>
</template>

<script>
  import listPage from "./../../../../../common/listPage";
  import dateDay from "./../../../../../common/dataPicker/dateDay";
  import alertSuccess from "../../../../../common/alert/alertSuccess";
  import alertAdd from "../../../../../common/alert/alertAdd";
  import alertFailure from "../../../../../common/alert/alertFailure";
  //import radioTree from './../../../../common/tree/radioTree'

  export default {
    components: {
      listPage,
      dateDay,
      alertSuccess,
      alertAdd,
      alertFailure
      //radioTree,
    },
    name: "editCourseMes",
    data() {
      return {
        showSuccess: false,
        showAddBox: false,
        showFailure: false,
        //提交失败弹出框的提示文字
        failureCon: "",
        successMsg: "",
        addMsg: "",

        courseName: "",
        courseNumber: "",
        courseNature: "",
        openingSemester: "",
        openingAcademy: "",
        assessmentMethod: "",



        active_index: 1,
        search: false,
        editbtn: false,
        params: {
          url: this.axios.$host_oa + "/courseBasicInfo/list",
          payload: {},
          method: "post",
          data: "datas"
        },
        datas: {},
        item: ""
        //treeData:[]
      };
    },
    created() {
      /*this.logUserNumber = this.$util.sessionGetData('mes').data.userNumber;
        this.params.payload = this.initParams(0, 10); //也在在这里增加些附加属性
        let condition = {
          "colName": 'userNumber',
          "ruleType": 'EQ',
          "value": this.logUserNumber
        };*/
      this.params.payload = this.initParams(0, 10, "createTime", "asc"); //也在在这里增加些附加属性
    },
    mounted() {
      /*this.$api.departmentData().then((data) => {
          data.name = 'deptName';
          this.treeData = data;
        });*/
    },
    methods: {
      /*//部门组件
        radioGetData: function () {
          this.$util.openPop('#positionDept')
        },
        receptionGetData: function () {
          this.$util.openPop('#positionDept')
        },
        //部门组件赋值
        receptionSetData: function (data) {
          this.responsibleDept = data.deptName;
          layer.close(layer.index);
        },*/

      jump_page: function(id) {
        let routeData = this.$router.resolve({
          name: "courseBasicInfoDetail",
          query: {
            id: id
          }
        });
        window.open(routeData.href, "_blank");
      },

      //更新信息方法
      update: function(id) {
        let routeData = this.$router.resolve({
          name: "courseBasicInfoUpdate",
          query: { id: id }
        });
        window.open(routeData.href, "_blank");
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
      // 确认添加
      confirmAddBox(data) {
        this.showAddBox = data;
        //调用添加方法
        this.delete(this.item);
      },
      // 取消以及关闭添加弹框
      closeAddBox(data) {
        this.showAddBox = data;
      },
      // 关闭失败弹框
      closeFailure(data) {
        this.showFailure = data;
      },

      reset_search: function() {
          (this.courseName = ""),
          (this.courseNumber = ""),
          (this.courseNature = ""),
          (this.openingSemester = ""),
          (this.openingAcademy = ""),
          (this.assessmentMethod = "");
        /*this.$refs.treeRadio.eliminate();
          this.$refs.treeRadio1.eliminate();*/
      },
      optlist: function(event) {
        document.querySelector(".listShow_more").classList.toggle("active");
      },
      search_fn: function() {
        this.search = !this.search;
      },
      //搜索
      search_data: function() {
        let payload = this.initParams(0, 10);
        let url = this.axios.$host_oa + "/courseBasicInfo/list";
        if (
          this.courseName != null &&
          this.courseName != "" &&
          this.courseName != undefined
        ) {
          let busCondition = {
            colName: "courseName",
            ruleType: "LIKE",
            value: this.courseName
          };
          payload.condition.push(busCondition);
        }
        if (
          this.courseNumber != null &&
          this.courseNumber != "" &&
          this.courseNumber != undefined
        ) {
          let deptCondition = {
            colName: "courseNumber",
            ruleType: "EQ",
            value: this.courseNumber
          };
          payload.condition.push(deptCondition);
        }
        if (
          this.courseNature != null &&
          this.courseNature != "" &&
          this.courseNature != undefined
        ) {
          let staCondition = {
            colName: "courseNature",
            ruleType: "EQ",
            value: this.courseNature
          };
          payload.condition.push(staCondition);
        }
        if (
          this.openingSemester != null &&
          this.openingSemester != "" &&
          this.openingSemester != undefined
        ) {
          let endCondition = {
            colName: "openingSemester",
            ruleType: "LIKE",
            value: this.openingSemester
          };
          payload.condition.push(endCondition);
        }
        if (
          this.openingAcademy != null &&
          this.openingAcademy != "" &&
          this.openingAcademy != undefined
        ) {
          let desCondition = {
            colName: "openingAcademy",
            ruleType: "LIKE",
            value: this.openingAcademy
          };
          payload.condition.push(desCondition);
        }
        if (
          this.assessmentMethod != null &&
          this.assessmentMethod != "" &&
          this.assessmentMethod != undefined
        ) {
          let endCondition = {
            colName: "assessmentMethod",
            ruleType: "LIKE",
            value: this.assessmentMethod
          };
          payload.condition.push(endCondition);
        }
        let engine = this.$post(url, payload);
        engine.then(data => {
          if (data.flag) {
            this.datas = data.datas;
            this.reset_search();
            this.search = false;
          } else {
            layer.msg("搜索失败！");
          }
        });
        this.reset_search();
        this.search = !this.search;
      },
      edit_fn: function() {
        this.editbtn = !this.editbtn;
      },

      //新增
      jump_page2: function() {
        let routeData = this.$router.resolve({
          name: "createCourseMes"
        });
        window.open(routeData.href, "_blank");
      },

      //详情
      /*jump_page: function (id) {
          let routeData = this.$router.resolve({
            name: "businessTripDetail",
            query: {
              id: id
            }
          });
          window.open(routeData.href, '_blank');
        },*/

      //删除功能
      delete_data: function(id) {
        console.info("11111111111",id)
        this.addMsg = "是否删除？";
        this.showAddBox = true;
        this.item = id;
      },
      //根据id级联删除
      delete: function(id) {
        let this_ = this;
        let tip = layer.load(1, {
          shade: [0.75, "#000"] //0.1透明度的白色背景
        });
        let engineType = this.$post(
          this.axios.$host_oa + "/courseBasicInfo/delete/" + id
        );
        engineType.then(data => {
          if (data.flag) {
            setTimeout(() => {
              this_.successMsg = "删除成功！";
              this_.showSuccess = true;
              this_.item = "";
              layer.close(tip);
            }, 200);
            this_.reload();
          } else {
            setTimeout(() => {
              this_.failureCon = "删除失败！";
              this_.showFailure = true;
              layer.close(tip);
            }, 200);
            this_.reload();
          }
        });
      }
    }
  };
</script>

<style>
  #courseBasicInfoList .ed1,
  #courseBasicInfoList .ed3 {
    display: inline-block;
    width: 18px;
    height: 18px;
    padding: 0px;
    border: none;
    background-size: 18px auto;
    background-position: center;
    margin: 0 auto;
  }
  #courseBasicInfoList .ed3 {
    margin-right: 15px;
    background: transparent url("../../../../../assets/img/bf-delete-node.png")
    no-repeat left center;
  }
  #courseBasicInfoList .ed1 {
    background: transparent url("../../../../../assets/img/bf-edit.png") no-repeat
    left center;
  }
</style>

