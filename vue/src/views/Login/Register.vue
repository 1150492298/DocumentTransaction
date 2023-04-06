<template>
  <div class="Register">
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
    <div class="RegisterBodyHeader">
      <div class="RegisterBodyHeaderImage">
        <img src="@/assets/logo.png"/>
      </div>
      <div class="RegisterBodyHeaderText">
        注册
      </div>
    </div>
    <div class="RegisterBody">
      <el-form :model="userForm" :rules="rules" ref="userForm" label-width="auto" class="demo-ruleForm mystify_form"
               size="mini"
               :hide-required-asterisk='hide_required' label-position="left">
        <el-form-item label="用户姓名" prop="userName">
          <el-input v-model="userForm.userName"></el-input>
        </el-form-item>
        <el-form-item label="用户电话" prop="userPhone">
          <el-input v-model="userForm.userPhone"></el-input>
        </el-form-item>
        <el-form-item label="用户密码" prop="userPwd">
          <el-input v-model="userForm.userPwd" show-password></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="checkPwd">
          <el-input v-model="userForm.checkPwd" show-password></el-input>
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
        <el-form-item>
          <el-button type="primary" @click="register('userForm')">注册账号</el-button>
          <el-button @click="toLogin">返回登陆</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>;
import request from "@/utils/request";

export default {
  name: "Register",
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
      userForm: {
        userName: '',
        userSex: '',
        userPhone: '',
        userPwd: '',
        checkPwd: ''
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
        userPwd: [
          {validator: validatePass, trigger: 'blur'},
        ],
        checkPwd: [
          {validator: validatePass2, trigger: 'blur'},
        ]
      },
      picIf: false,
      picFailCount: 1,
      hide_required: true,
      picArray: [
        require("@/assets/picSlideArray/1.png"),
        require("@/assets/picSlideArray/2.png"),
        require("@/assets/picSlideArray/3.png"),
        require("@/assets/picSlideArray/4.png"),
        require("@/assets/picSlideArray/5.png"),
      ],
    };
  },
  methods: {
    // async getUserById() {
    //   await request.get("/user/userGetUserByToken/" + this.userCookie.token).then(res => {
    //     if (res.code == '200') {
    //       this.userForm = res.data
    //       this.roleName = this.$store.getters.getRoleList.find((item) => {
    //         return item.roleId === this.userForm.roleId
    //       }).roleName
    //       this.departmentName = this.$store.getters.getDepartmentList.find((item) => {
    //         return item.departmentId === this.userForm.departmentId
    //       }).departmentName
    //     } else {
    //       this.$notify.error(res.msg)
    //     }
    //   })
    // },
    register(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.picIf=true
        } else {
          this.$notify.error('信息不合规范')
        }
      })
    }
    ,
    toLogin() {
      this.$router.push("/login")
    },
    onSuccess() {
      this.picIf = false
      request.post('/user/userRegister', this.userForm).then(res => {
        if (res.code == '200') {
          this.userAccount = res.data
          if (this.userAccount != null) {
            this.$notify.success({
              title: '注册成功',
              message: '您的账号为'+this.userAccount+',请牢记',
              duration: 0
            });
            this.$router.push("/login")
          } else {
            this.$notify.error("注册失败")
          }
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
    onFail() {
      if (this.picFailCount === 5) {
        this.$notify.error('滑块验证失败，请重新尝试')
        this.picIf = false
        this.picFailCount = 1
      } else {
        this.picFailCount++
      }
    },
    onRefresh() {
      console.log('refresh')
    },
  },
  mounted() {

  }

}
</script>

<style lang="less" scoped>
.Register {
  width: 100%;
  height: 100%;
  float: none;

  .cover {
    width: fit-content;
    background-color: white;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    z-index: 1000;
  }

  .RegisterBodyHeader {
    //padding-top: 10%;
    height: 20%;

    .RegisterBodyHeaderImage {
      height: 40%;
      img {
        height: 40%
      }
    }

    .RegisterBodyHeaderText {
      padding-top: 5%;
      padding-bottom: 5%;
      height: 30%;
    }
  }

  .RegisterBody {
    height: 50%;
  }

  /deep/ input {
    background-color: #ddded7;
  }

  /deep/ textarea {
    background-color: #ddded7;
  }

}
</style>