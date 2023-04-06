<template>
  <div class="Login">
    <el-card class="cover" v-if="picIf">
      <slide-verify :l="42"
                    :r="10"
                    :w="250"
                    :h="155"
                    slider-text="向右滑动"
                    @success="onSuccess"
                    @fail="onFail"
                    @refresh="onRefresh"
                    :imgs="picArray"
      ></slide-verify>
    </el-card>
    <div class="LoginBodyHeader">
      <div class="LoginBodyHeaderImage">
        <img src="@/assets/logo.png"/>
      </div>
      <div class="LoginBodyHeaderText">
        登录
      </div>
    </div>
    <div class="LoginBody">
      <el-form :model="userForm" :rules="rules" ref="userForm" label-width="auto"
               class="demo-ruleForm mystify_form" size="mini"
               :hide-required-asterisk='hide_required'>
        <el-form-item label="账号" prop="userAccount">
          <el-input v-model="userForm.userAccount" maxlength="50"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="userPwd">
          <el-input v-model="userForm.userPwd" show-password maxlength="50"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="login('userForm')">用户登录</el-button>
          <el-button @click="toRegister">注册账号</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>;
import request from "@/utils/request";
import Cookies from 'js-cookie'

export default {
  name: "Login",
  data() {
    return {
      userForm: {
        userAccount: '',
        userPwd: '',
      },
      userAbout:{},
      userCookie:{},
      picIf:false,
      picFailCount:1,
      picArray: [
        require("@/assets/picSlideArray/1.png"),
        require("@/assets/picSlideArray/2.png"),
        require("@/assets/picSlideArray/3.png"),
        require("@/assets/picSlideArray/4.png"),
        require("@/assets/picSlideArray/5.png"),
      ],
      hide_required: true,
      rules: {
        userAccount: [
          {required: true, message: '请输入用户名称', trigger: 'blur'},
        ],
        userPwd: [
          {required: true, message: '请填写用户密码', trigger: 'blur'},
        ]
      }
    };
  },
  methods: {
    login(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.picIf=true
        } else {
          this.$notify.error('信息不合规范')
        }
      })
    },
    toRegister() {
      this.$router.push('/register')
    },
    onSuccess() {
      this.picIf=false
      request.post('/user/userLogin', this.userForm).then(res => {
        if (res.code == '200') {
          this.userAbout=res.data
          if(this.userAbout!=null){
            this.userAbout.userPwd=this.userForm.userPwd
            Cookies.set('user',JSON.stringify(this.userAbout))
            this.$notify.success('用户登录成功')
            this.$router.push("/")
          }else{
            this.$notify.error("获取用户信息失败")
          }
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
    onFail() {
      if(this.picFailCount===5){
        this.$notify.error('滑块验证失败，请重新尝试')
        this.picIf=false
        this.picFailCount=1
      }else {
        this.picFailCount++
      }
    },
    onRefresh() {
      console.log('refresh')
    },
  },
  mounted() {
    this.userCookie=Cookies.get('user')?JSON.parse(Cookies.get('user')): {}
    this.userForm.userPwd=this.userCookie['userPwd']
    this.userForm.userAccount=this.userCookie['userAccount']
    console.log(this.userForm)
  }
}
</script>

<style lang="less" scoped>
.Login {
  width: 100%;
  height: 100%;
  float: none;

  .cover {
    width: fit-content;
    background-color: white;
    position: absolute;
    top:50%;
    left:50%;
    transform: translate(-50%, -50%);
    z-index: 1000;
  }

  .LoginBodyHeader {
    padding-top: 10%;
    height: 40%;

    .LoginBodyHeaderImage {
      height: 60%;

      img {
        height: 50%
      }
    }

    .LoginBodyHeaderText {
      padding-top: 10%;
      height: 30%;
    }
  }

  .LoginBody {
    height: 50%;
    margin-top: 10%;
  }

  /deep/ input {
    background-color: #ddded7;
  }

  /deep/ textarea {
    background-color: #ddded7;
  }

}
</style>