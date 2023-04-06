<template>
  <div class="DepartmentAddView">
    <AppIntroHeader HeaderClass="部门添加"></AppIntroHeader>
    <div class="DepartmentAddViewbody">
      <el-form :model="departmentForm" :rules="rules" ref="departmentForm" label-width="auto" class="demo-ruleForm mystify_form" size="mini"
               :hide-required-asterisk='hide_required' label-position="top" >
        <el-form-item label="部门名称" prop="departmentName">
          <el-input v-model="departmentForm.departmentName"></el-input>
        </el-form-item>
        <el-form-item label="部门简介" prop="departmentIntro">
          <el-input type="textarea" v-model="departmentForm.departmentIntro" rows="8"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="save('departmentForm')">添加部门</el-button>
          <el-button @click="resetDepartment()">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import AppIntroHeader from "@/components/AppIntroHeader";
import request from "@/utils/request";
export default {
  name: "DepartmentAddView",
  components: {AppIntroHeader},
  data() {
    return {
      departmentForm: {
        departmentIntro: '',
        departmentName: '',
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
          request.post('/department/save', this.departmentForm).then(res=>{
            if(res.code=='200'){
              this.$notify.success('新增部门成功')
            }else{
              this.$notify.error(res.msg)
            }
          })
        }else {
          this.$notify.error('部门信息不符合规范')
        }
      });
    },
    resetDepartment(){
      this.departmentForm={
        departmentIntro: '',
        departmentName: '',
      }
    }
  }
}
</script>

<style lang="less" scoped>
  .DepartmentAddView{
    width: 100%;
    height: 100%;
    float: none;
    /deep/input{
      background-color: #ddded7;
    }
    /deep/textarea{
      background-color: #ddded7;
      height: 50%;
    }
    .DepartmentAddViewbody{
      margin-top: 10%;
    }
  }
</style>