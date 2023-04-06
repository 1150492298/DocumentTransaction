<template>
  <div class="DepartmentEditView">
    <AppIntroHeader HeaderClass="部门编辑"></AppIntroHeader>
    <div class="DepartmentEditViewbody">
      <el-form :model="departmentForm" :rules="rules" ref="departmentForm" label-width="auto" class="demo-ruleForm mystify_form" size="mini"
               :hide-required-asterisk='hide_required' label-position="top" >
        <el-form-item label="部门Id" prop="departmentId">
          <el-input v-model="departmentForm.departmentId" readonly="readonly" class="inputReadOnly"></el-input>
        </el-form-item>
        <el-form-item label="部门名称" prop="departmentName">
          <el-input v-model="departmentForm.departmentName"></el-input>
        </el-form-item>
        <el-form-item label="部门简介" prop="departmentIntro">
          <el-input type="textarea" v-model="departmentForm.departmentIntro" rows="8"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="save('departmentForm')">更新部门</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import AppIntroHeader from "@/components/AppIntroHeader";
import request from "@/utils/request";
export default {
  name: "DepartmentEditView",
  components: {AppIntroHeader},
  data() {
    return {
      departmentForm: {
        departmentId:0,
        departmentIntro:'无',
        departmentName:'未获取'
      },
      hide_required: true,
      rules: {
        departmentName: [
          {required: true, message: '请输入部门名称', trigger: 'blur'},
          {min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur'}
        ],
        departmentIntro: [
          {required: true, message: '请填写部门简介', trigger: 'blur'},
          {min: 0, max: 200, message: '简介字数不得超过二百', trigger: 'change'}
        ]
      }
    };
  },
  methods:{
    save(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          request.put('/department/updateDepartmentById', this.departmentForm).then(res=>{
            if(res.code=='200'){
              this.$notify.success('更新部门成功')
              this.$router.back()
            }else{
              this.$notify.error(res.msg)
            }
          })
        }else {
          this.$notify.error('部门信息不符合规范')
        }
      });
    },
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
.DepartmentEditView{
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