<template>
  <div class="RoleSearch">
    <AppIntroHeader HeaderClass="权限申请"></AppIntroHeader>
    <div class="RoleSearchBody">
      <el-form :model="roleSearchForm" :rules="rules" ref="roleSearchForm" label-width="auto" class="demo-ruleForm mystify_form" size="mini"
               :hide-required-asterisk='hide_required' label-position="top" >
        <el-form-item label="申请权限" prop="roleId">
          <el-select v-model="roleSearchForm.roleId" placeholder="请选择" class="roleSearchFormRoleId">
            <el-option
                v-for="item in $store.getters.getRoleList"
                :key="item.roleId"
                :label="item.roleName"
                :value="item.roleId"
                >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="申请理由" prop="searchReason">
          <el-input type="textarea" v-model="roleSearchForm.searchReason" rows="8"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="roleSearch('roleSearchForm')">请求权限</el-button>
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
  name: "RoleSearch",
  components: {AppIntroHeader},
  data() {
    return {
      roleSearchForm: {
        userId:this.$store.getters.getUserForm.userId,
        roleId: null,
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
        roleId: [
          {required: true, message: '请选择申请权限', trigger: 'blur'},
        ]
      }
    };
  },
  methods:{
    roleSearch(formName){
      this.roleSearchForm.token=this.userCookie.token
      this.$refs[formName].validate((valid) => {
        if (valid) {
          request.post('/roleSearch/addRoleSearch', this.roleSearchForm).then(res=>{
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
.RoleSearch{
  width: 100%;
  height: 100%;
  float: none;
  /deep/ .roleSearchFormRoleId{
    width: 100%;
  }
  /deep/input{
    background-color: #ddded7;
  }
  /deep/textarea{
    background-color: #ddded7;
    height: 50%;
  }
  .RoleSearchBody{
    margin-top: 10%;
  }
}
</style>