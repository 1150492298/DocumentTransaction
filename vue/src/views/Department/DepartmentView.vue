<template>
  <div class="DepartmentView">
    <AppIntroHeader HeaderClass="部门查看"></AppIntroHeader>
    <div class="DepartmentEditViewbody">
      <el-form :model="departmentForm"  label-width="auto" class="demo-ruleForm mystify_form" size="mini"
               :hide-required-asterisk='hide_required' label-position="top" >
        <el-form-item label="部门Id" >
          <el-input v-model="departmentForm.departmentId" readonly="readonly" class="inputReadOnly"></el-input>
        </el-form-item>
        <el-form-item label="部门名称" >
          <el-input v-model="departmentForm.departmentName" readonly="readonly" class="inputReadOnly"></el-input>
        </el-form-item>
        <el-form-item label="部门简介" >
          <el-input type="textarea" v-model="departmentForm.departmentIntro" rows="8" readonly="readonly" class="inputReadOnly"></el-input>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import AppIntroHeader from "@/components/AppIntroHeader";
import request from "@/utils/request";
export default {
  name: "DepartmentView",
  components: {AppIntroHeader},
  data() {
    return {
      departmentForm: {
        departmentId:0,
        departmentIntro:'无',
        departmentName:'未获取'
      },
      hide_required: true,
    };
  },
  methods:{
    getDepartmentById(){
      const departmentId=parseInt(this.$route.query.departmentId)
      request.get("/department/getDepartmentById/" + departmentId).then(res => {
        if (res.code == '200') {
          this.departmentForm=res.data
          this.$notify.success('获取部门信息成功')
        } else {
          this.$notify.error(res.msg)
        }
      })
    }
  },
  mounted() {
    this.getDepartmentById()
  }
}
</script>

<style lang="less" scoped>
.DepartmentView{
  width: 100%;
  height: 100%;
  float: none;

  /deep/.inputReadOnly{
    input{
      background-color: #cdd2c0;
    }
    textarea {
      background-color: #cdd2c0;
    }
  }

  /deep/input{
    background-color: #ddded7;
  }
  /deep/textarea{
    background-color: #ddded7;
    height: 50%;
  }
  .DepartmentEditViewbody{
    margin-top: 10%;
  }
}
</style>