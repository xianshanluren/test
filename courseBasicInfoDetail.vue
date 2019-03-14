<template>
  <div style="height:100%;width:100%;" v-show="!success">
    <div class="add_btn_con">
      <div class="add_btn_inner">
        <div class="add_btn" @click="close_page">关闭</div>
      </div>
    </div>
    <div class="addData_container">
      <div class="addinfor_input_top">
        <div class="input_top_title" v-cloak>{{title}}</div>
        <div class="separat_line">课程基本信息</div>
        <hr />

        <div class="input_top_basic_con">

          <div class="lan_4 show_list">
            <div class="input_head lan_4">课程名称：</div>
            <div class="input_content lan_8">{{data.courseName}}</div>
          </div>

          <div class="lan_4 show_list">
            <div class="input_head lan_4">课程编号：</div>
            <div class="input_content lan_8" v-cloak>{{data.courseNumber}}</div>
          </div>
          <div class="lan_4 show_list">
            <div class="input_head lan_4">课程性质：</div>
            <div class="input_content lan_8" v-cloak>{{data.courseNature}}</div>
          </div>
          <div class="lan_4 show_list">
            <div class="input_head lan_4">开课学院：</div>
            <div class="input_content lan_8">{{data.openingAcademy}}</div>
          </div>
          <div class="lan_4 show_list">
            <div class="input_head lan_4">总学时：</div>
            <div class="input_content lan_8" v-html="data.totalHours"></div>
          </div>
          <div class="lan_4 show_list">
            <div class="input_head lan_4">理论学时：</div>
            <div class="input_content lan_8">{{data.theoreticalHours}}</div>
          </div>
          <div class="lan_4 show_list">
            <div class="input_head lan_4">实践学时：</div>
            <div class="input_content lan_8">{{data.practicalHours}}</div>
          </div>
          <div class="lan_4 show_list">
            <div class="input_head lan_4">考核方式：</div>
            <div class="input_content lan_8">{{data.assessmentMethod}}</div>
          </div>
          <!-- <div class="lan_12 show_list">
                        <div class="input_head lan_2">图片：</div>
                        <span v-for="index in campusEnclosure" style="margin-left: 10px;"><a :href="index.downloadUrl" download="index.downloadName" >{{index.downloadName}}</a></span>
                    </div> -->

          <div class="clear:both;"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import upLoad from './../../../../../common/upLoad'
  export default {
    name: "courseBasicInfoDetail",
    components: {
      upLoad
    },
    data() {
      return {
        title: '课程详情',
        status: '',
        active_index: 1,
        id: null,
        year: '',
        search: false,
        success: false,
        data: {},
        procData: {},
        avtivitiStatusList: [],
        applyStatus: null,
        nextNode: '',
        chargesFundNum: '',
        deptNumber: '',
        predictEmployMoney: '',
        path: '',
        campusEnclosure: [],
        campusImage: [],
        download: {
        }
      }
    },
    mounted: function() {
      let id = this.$route.query.id;
      let engine = this.$post(this.axios.$host_oa+"/courseBasicInfo/form/" + id);
      engine.then(data => {
        if(data.flag) {
          this.data = data.data;
          this.campusClicks = data.data.campusClicks++ ;
          //		alert(this.campusClicks)
          this.data["id"] = id;
          let engine = this.$post(this.axios.$host_oa+"/courseBasicInfo/update", this.data);
        } else {
          this.index = layer.open({
            icon: 1, //图标
            area: ['200px', '160px'],
            content: '系统繁忙,请稍后再试!'
          });
          return;
        }
      });

    },

    methods: {
      close_page: function() {
        window.close();
      },
      tab_change: function(index) {
        this.active_index = index;
      }
    }
  }
</script>

<style>

</style>
