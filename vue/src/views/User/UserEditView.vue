<template>
  <div class="UserEditView">
    <AppIntroHeader HeaderClass="用户管理"></AppIntroHeader>
    <div class="UserAddViewbody">
      <el-form :model="userForm" :rules="rules" ref="userForm" label-width="auto"
               class="demo-ruleForm mystify_form" size="mini"
               :hide-required-asterisk='hide_required'>
        <el-form-item label="用户账号">
          <el-input class="inputReadOnly" type="textarea" v-model="userForm.userAccount" rows="1" readonly="readonly"></el-input>
        </el-form-item>
        <el-form-item label="创建时间">
          <el-input class="inputReadOnly" v-model="userForm.createTime" readonly="readonly"></el-input>
        </el-form-item>
        <el-form-item label="用户编号">
          <el-input class="inputReadOnly" v-model="userForm.userId" readonly="readonly"></el-input>
        </el-form-item>
        <el-form-item label="用户姓名" prop="userName">
          <el-input v-model="userForm.userName"></el-input>
        </el-form-item>
        <el-form-item label="用户电话" prop="userPhone">
          <el-input v-model="userForm.userPhone"></el-input>
        </el-form-item>
        <el-form-item label="用户性别" prop="userSex">
          <el-select v-model="userForm.userSex" placeholder="请选择">
            <el-option
                v-for="item in sex"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="用户部门" prop="userDepartment">
          <el-select v-model="userForm.departmentId" placeholder="请选择">
            <el-option
                v-for="item in $store.getters.getDepartmentList"
                :key="item.departmentId"
                :label="item.departmentName"
                :value="item.departmentId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="用户角色" prop="userRole">
          <el-select v-model="userForm.roleId" placeholder="请选择">
            <el-option
                v-for="item in $store.getters.getRoleList"
                :key="item.roleId"
                :label="item.roleName"
                :value="item.roleId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="用户简介" prop="userIntro">
          <el-input type="textarea" v-model="userForm.userIntro" rows="4"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="updateUser">更新用户</el-button>
          <el-button @click="resetForm">重置密码</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import AppIntroHeader from "@/components/AppIntroHeader";
import request from "@/utils/request";

export default {
  name: "UserEditView",
  components: {AppIntroHeader},
  data() {
    const checkPhone = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('请填写用户手机号'));
      }
      const r = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/;
      if (!r.test(value)) {
        callback(new Error('请输入正确手机号'));
      } else {
        callback()
      }
    }
    return {
      userForm: {
        userId:'',
        roleId:null,
        userName:'',
        userAccount:'',
        userIntro:'',
        userSex:null,
        userPhone:'',
        departmentId:null,
        createTime:'',
      },
      sex: [
        {
          value: 1,
          label: '男'
        }, {
          value: 0,
          label: '女'
        }
      ],
      hide_required: true,
      userTwicePwd:'',
      rules: {
        userName: [
          {required: true, message: '请输入用户名称', trigger: 'blur'},
          {min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur'}
        ],
        userPhone: [
          {validator: checkPhone, trigger: 'blur'},
        ],
        userSex: [
          {required: true, message: '请选择用户性别', trigger: 'blur'},
        ],
        userDepartment: [
          {required: true, message: '请选择用户部门', trigger: 'blur'},
        ],
        userIntro: [
          {required: true, message: '请填写用户简介', trigger: 'blur'},
          {min: 0, max: 200, message: '简介字数不得超过二百', trigger: 'change'}
        ],
        userRole: [
          {required: true, message: '请选择用户角色', trigger: 'blur'},
        ]
      }
    };
  },
  created() {
    this.getUserById()
    this.$notify.success('进入用户信息界面')
  },
  methods: {
    updateUser() {
      this.$refs[userForm].validate((valid)=> {
        if (valid) {
          request.put('/user/adminUpdateUserById', this.userForm).then(res => {
            if (res.code == '200') {
              this.$notify.success('更新用户信息成功')
              this.$router.push("/usermanage")
            } else {
              this.$notify.error(res.msg)
            }
          })
        }else {
          this.$notify.error('用户信息不符合规范')
        }
      })
    },
    resetForm() {

      this.$router.push('/adminResetPwd?userId='+this.userForm.userId)
    },
    getUserById(){
      const userId=parseInt(this.$route.query.userId)
      request.get("/user/adminGetUserById/" + userId).then(res => {
        if (res.code == '200') {
          this.userForm=res.data
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
  }
}
</script>

<style lang="less" scoped>
.UserEditView {
  width: 100%;
  height: 100%;
  float: none;
  overflow: scroll;

  /deep/ input {
    background-color: #ddded7;
  }

  /deep/ textarea {
    background-color: #ddded7;
  }

  /deep/.inputReadOnly{
    input{
      background-color: #cdd2c0;
    }
    textarea {
      background-color: #cdd2c0;
    }
  }

  .UserAddViewbody {
    margin-top: 2%;
  }

}
</style>