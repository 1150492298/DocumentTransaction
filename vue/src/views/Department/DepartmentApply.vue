<template>
  <div class="DepartmentApply">
    <AppIntroHeader HeaderClass="部门申请"></AppIntroHeader>
    <div class="DepartmentApplyBody">
      <el-form :model="departmentApplyForm" :rules="rules" ref="departmentApplyForm" label-width="auto" class="demo-ruleForm mystify_form" size="mini"
               :hide-required-asterisk='hide_required' label-position="top" >
        <el-form-item label="申请部门" prop="departmentId">
          <el-select v-model="departmentApplyForm.departmentId" placeholder="请选择" class="departmentApplyFormRoleId">
              <el-option
                  v-for="item in $store.getters.getDepartmentList"
                  :key="item.departmentId"
                  :label="item.departmentName"
                  :value="item.departmentId"
              >
              </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="申请理由" prop="searchReason">
          <el-input type="textarea" v-model="departmentApplyForm.searchReason" rows="8"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="departmentApply('departmentApplyForm')">提交申请</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import AppIntroHeader from "@/components/AppIntroHeader";
import request from "@/utils/request";
import Cookies from "js-cookie";

export default {
  name: "DepartmentApply",
  components: {AppIntroHeader},
  data() {
    return {
      departmentApplyForm: {
        departmentId: null,
        searchReason: '',
        token:null
      },
      userCookie:Cookies.get('user') ? JSON.parse(Cookies.get('user')) : {},
      hide_required: true,
      rules: {
        searchReason: [
          {required: true, message: '请输入申请理由', trigger: 'blur'},
          {min: 3, max: 50, message: '长度不得小于3大于50', trigger: 'blur'}
        ],
        departmentId: [
          {required: true, message: '请选择申请部门', trigger: 'blur'},
        ]
      }
    };
  },
  methods:{
    departmentApply(formName){
      this.departmentApplyForm.token=this.userCookie.token
      this.$refs[formName].validate((valid) => {
        if (valid) {
          request.post('/departmentSearch/addDepartmentSearch', this.departmentApplyForm).then(res=>{
            if(res.code=='200'){
              this.$notify.success('发送请求成功，请等待管理员回应')
            }else{
              this.$notify.error(res.msg)
            }
          })
        }else {
          this.$notify.error('用户信息不符合规范')
        }
      });
    },
  },
  mounted(){

  }
}
</script>

<style lang="less" scoped>
.DepartmentApply{
  width: 100%;
  height: 100%;
  float: none;
  /deep/ .departmentApplyFormRoleId{
    width: 100%;
  }
  /deep/input{
    background-color: #ddded7;
  }
  /deep/textarea{
    background-color: #ddded7;
    height: 50%;
  }
  .DepartmentApplyBody{
    margin-top: 10%;
  }
}
</style>