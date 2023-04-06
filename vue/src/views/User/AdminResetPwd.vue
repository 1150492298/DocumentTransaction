<template>
  <div class="AdminResetPwd">
    <AppIntroHeader HeaderClass="重置用户密码"></AppIntroHeader>
    <el-form :model="userForm" :rules="rules" ref="userForm" label-width="auto" class="demo-ruleForm mystify_form"
             size="mini"
             :hide-required-asterisk='hide_required' label-position="left">
      <el-form-item label="用户账号">
        <el-input v-model="userForm.userId" class="inputReadOnly" readonly="readonly"></el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="newPwd">
        <el-input v-model="userForm.newPwd" ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="resetPwd('userForm')">更新密码</el-button>
        <el-button @click="resetForm()">重置表单</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import Cookies from "js-cookie";
import request from "@/utils/request";

export default {
  name: "AdminResetPwd",
  data() {
    const validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      }
      const r=/^\w{3,20}$/;
      if (!r.test(value)) {
        callback(new Error('密码由字母数字或下划线组成，长度为3-20'));
      }else {
        callback()
      }
    };
    return {
      userCookie: null,
      hide_required: true,
      userForm:{
        userId:null,
        newPwd:'',
        token:null
      },
      rules: {
        newPwd: [
          {validator: validatePass, trigger: ['blur','change']},
        ],
      },
    };
  },
  methods: {
    resetPwd(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          request.put('/user/adminResetUserPwd',this.userForm).then(res => {
            if (res.code == '200') {
              this.$notify.success('该用户密码已更新成功')
              this.$router.push('/usermanage')
            } else {
              this.$notify.error(res.msg)
            }
          })
        }else {
          this.$notify.error('密码不符合规范')
        }
      });
    },
    resetForm() {
      this.userForm.newPwd=''
    },
    loadCookie(){
      this.userCookie=Cookies.get('user') ? JSON.parse(Cookies.get('user')) : {}
      this.userForm.token=this.userCookie.token
    },
  },
  mounted() {
    this.userForm.userId=parseInt(this.$route.query.userId)
    this.loadCookie()
  }
}
</script>

<style lang="less" scoped>
.AdminResetPwd {
  height: 100%;
  width: 100%;

  /deep/ input {
    background-color: #ddded7;
  }

  /deep/ textarea {
    background-color: #ddded7;
  }

  /deep/ .inputReadOnly {


    input {
      background-color: #cdd2c0;
    }

    textarea {
      background-color: #cdd2c0;
    }
  }
}
</style>