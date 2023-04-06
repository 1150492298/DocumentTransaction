<template>
  <div class="UserAddView">
    <AppIntroHeader HeaderClass="用户添加"></AppIntroHeader>
    <div class="UserAddViewbody">
      <el-form :model="userForm" :rules="rules" ref="userForm" label-width="auto"
               class="demo-ruleForm mystify_form" size="mini"
               :hide-required-asterisk='hide_required'>
        <el-form-item label="用户姓名" prop="userName">
          <el-input v-model="userForm.userName"></el-input>
        </el-form-item>
        <el-form-item label="用户密码" prop="userPwd">
          <el-input v-model="userForm.userPwd"></el-input>
        </el-form-item>
        <el-form-item label="用户电话" prop="userPhone">
          <el-input v-model="userForm.userPhone"></el-input>
        </el-form-item>
        <el-form-item label="用户简介" prop="userIntro">
          <el-input type="textarea" v-model="userForm.userIntro" rows="4"></el-input>
        </el-form-item>
        <el-form-item label="用户性别" prop="userSex">
          <el-select v-model="userForm.userSex" placeholder="请选择">
            <el-option
                v-for="item in userSex"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="用户部门" prop="departmentId">
          <el-select v-model="userForm.departmentId" placeholder="请选择">
            <el-option
                v-for="item in $store.getters.getDepartmentList"
                :key="item.departmentId"
                :label="item.departmentName"
                :value="item.departmentId"
                >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="用户角色" prop="roleId">
          <el-select v-model="userForm.roleId" placeholder="请选择">
            <el-option
                v-for="item in $store.getters.getRoleList"
                :key="item.roleId"
                :label="item.roleName"
                :value="item.roleId"
                >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="save('userForm')">添加</el-button>
          <el-button @click="resetUserForm">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "UserAddView",
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
        roleId: null,
        userName: '',
        userPwd: '',
        userSex: null,
        userPhone: '',
        userIntro: '',
        departmentId: null
      },
      userSex: [
        {
          value: 1,
          label: '男'
        }, {
          value: 0,
          label: '女'
        }
      ],
      departmentId: [
        {
          value: 1,
          label: '水电部'
        }, {
          value: 0,
          label: '财务部'
        }
      ],
      roleId: [
        {
          value: 1,
          label: '普通用户'
        }, {
          value: 0,
          label: '最高管理员'
        }, {
          value: 2,
          label: '公文审核员'
        }, {
          value: 3,
          label: '流程批准员'
        }, {
          value: 4,
          label: '游客'
        }, {
          value: 5,
          label: '最高管理员'
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
          {required: true, message: '请选择用户性别', trigger: ['blur','change']},
        ],
        departmentId: [
          {required: true, message: '请选择用户部门', trigger: ['blur','change']},
        ],
        userIntro: [
          {required: true, message: '请填写用户简介', trigger: 'blur'},
          {min: 0, max: 200, message: '简介字数不得超过二百', trigger: 'change'}
        ],
        roleId: [
          {required: true, message: '请选择用户角色', trigger: ['blur','change']},
        ],
        userPwd: [
          {required: true, message: '请输入用户密码', trigger: 'blur'},
          {min: 3, max: 20, message: '密码不得小于3位，不得大于20位', trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    save(formName) {
      this.$refs[formName].validate((valid)=> {
        if (valid) {
          request.post('/user/save', this.userForm).then(res => {
            if (res.code == '200') {
              this.$notify.success('新增用户成功')
            } else {
              this.$notify.error(res.msg)
            }
          })
        } else {
          this.$notify.error('信息不合规范')
        }
      })
    },
    resetUserForm() {
      this.userForm = {
        roleId: '',
        userName: '',
        userPwd: '',
        userSex: '',
        userPhone: '',
        userIntro: '',
        departmentId: ''
      }
    },
    async loadDepartment() {
      await this.$store.dispatch('getDepartmentList')
    },
    async loadRole() {
      await this.$store.dispatch('getRoleList')
    },
  },
  mounted() {
    this.loadRole()
    this.loadDepartment()
  }
}
</script>

<style lang="less" scoped>
.UserAddView {
  width: 100%;
  height: 100%;
  float: none;

  /deep/ input {
    background-color: #ddded7;
  }

  /deep/ textarea {
    background-color: #ddded7;
  }

  .UserAddViewbody {
    margin-top: 2%;
  }

}
</style>