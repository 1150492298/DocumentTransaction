<template>
  <div class="UserResetPwd">
    <AppIntroHeader HeaderClass="重置密码"></AppIntroHeader>
    <el-form :model="userForm" :rules="rules" ref="userForm" label-width="auto" class="demo-ruleForm mystify_form"
             size="mini"
             :hide-required-asterisk='hide_required' label-position="left">
      <el-form-item label="当前密码" prop="nowPwd">
        <el-input v-model="userForm.nowPwd" show-password></el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="userPwd">
        <el-input v-model="userForm.userPwd" show-password></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="checkPwd">
        <el-input v-model="userForm.checkPwd" show-password></el-input>
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
  name: "UserResetPwd",
  data() {
    const validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      }
      const r=/^\w{3,20}$/;
      if (!r.test(value)) {
        callback(new Error('密码由字母数字或下划线组成，长度为3-20'));
      }else {
        if (this.userForm.checkPwd !== '') {
          this.$refs.userForm.validateField('checkPwd');
        }
        callback();
      }
    };
    const validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.userForm.userPwd) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    }
    return {
      userCookie: null,
      hide_required: true,
      userForm:{
        userPwd: '',
        checkPwd: '',
        nowPwd:'',
        token:null
      },
      rules: {
        nowPwd: [
          {required: true, message: '请输入当前密码', trigger: 'blur'},
        ],
        userPwd: [
          {validator: validatePass, trigger: ['blur','change']},
        ],
        checkPwd: [
          {validator: validatePass2, trigger: ['blur','change']},
        ]
      },
    };
  },
  methods: {
    resetPwd(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          request.put('/user/userResetPwd',{
            nowPwd: this.userForm.nowPwd,
            checkPwd: this.userForm.checkPwd,
            token: this.userForm.token
          }).then(res => {
            if (res.code == '200') {
              this.$notify.success('您的密码已更新成功')
              this.$router.back()
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
      this.userForm={
        userPwd: '',
        checkPwd: '',
        nowPwd:'',
        token:null
      }
      this.loadCookie()
    },
    loadCookie(){
      this.userCookie=Cookies.get('user') ? JSON.parse(Cookies.get('user')) : {}
      this.userForm.token=this.userCookie.token
    },
  },
  mounted() {
    this.loadCookie()
  }
}
</script>

<style lang="less" scoped>
.UserResetPwd {
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