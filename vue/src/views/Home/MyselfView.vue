<template>
  <div class="MyselfView">
    <el-form :model="userForm" :rules="rules" ref="userForm" label-width="auto" class="demo-ruleForm mystify_form"
             size="mini"
             :hide-required-asterisk='hide_required' label-position="left">
      <el-form-item label="用户账号">
        <el-input class="inputReadOnly" type="textarea" v-model="userForm.userAccount" rows="1"
                  readonly="readonly"></el-input>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-input class="inputReadOnly" v-model="userForm.createTime" readonly="readonly"></el-input>
      </el-form-item>
      <el-form-item label="用户角色" >
        <el-input class="inputReadOnly" v-model="userForm.roleName" readonly="readonly" @focus="$router.push('/roleView?roleId='+userForm.roleId)" suffix-icon="el-icon-s-management"></el-input>
      </el-form-item>
      <el-form-item label="用户部门" >
        <el-input class="inputReadOnly" v-model="userForm.departmentName" readonly="readonly" @focus="$router.push('/departmentView?departmentId='+userForm.departmentId)" suffix-icon="el-icon-s-management"></el-input>
      </el-form-item>
      <el-form-item label="用户电话" prop="userPhone" >
        <el-input class="inputReadOnly" v-model="userForm.userPhone" readonly="readonly" ></el-input>
      </el-form-item>
      <el-form-item label="用户姓名" prop="userName">
        <el-input v-model="userForm.userName"></el-input>
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
      <el-form-item label="用户简介" prop="userIntro">
        <el-input type="textarea" v-model="userForm.userIntro" rows="4"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="updateUser('userForm')">更新信息</el-button>
        <el-button @click="resetPwd">重置密码</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import Cookies from "js-cookie";
import request from "@/utils/request";

export default {
  name: "MyselfView",
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
      userCookie: Cookies.get('user') ? JSON.parse(Cookies.get('user')) : {},
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
        userIntro: [
          {required: true, message: '请填写用户简介', trigger: 'blur'},
          {min: 0, max: 200, message: '简介字数不得超过二百', trigger: 'change'}
        ]
      },
    };
  },
  methods: {
    updateUser(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          request.put('/user/userUpdateUserByToken', {
            userForm:this.userForm,
            token:this.userCookie.token
          }).then(res => {
            if (res.code == '200') {
              this.$notify.success('您的信息已更新成功')
            } else {
              this.$notify.error(res.msg)
            }
          })
        }else {
          this.$notify.error('用户信息不符合规范')
        }
      });
    },
    resetPwd() {
      this.$router.push('/userResetPwd')
    },
    async loadUserForm() {
      await this.$store.dispatch('getUserFormById',this.userCookie.token)
    },
  },
  async mounted() {
    await this.loadUserForm()
  },
  computed:{
    userForm(){
      return this.$store.getters.getUserForm
    }
  }
}
</script>

<style lang="less" scoped>
.MyselfView {
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