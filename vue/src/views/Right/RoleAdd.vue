<template>
  <div class="RoleAdd">
    <AppIntroHeader HeaderClass="角色添加"></AppIntroHeader>
    <div class="RoleAddViewbody">
      <el-form :model="roleForm" :rules="rules" ref="roleForm" label-width="auto" class="demo-ruleForm mystify_form"
               size="mini"
               :hide-required-asterisk='hide_required' label-position="top">
        <el-form-item label="角色名称" prop="roleName">
          <el-input v-model="roleForm.roleName"></el-input>
        </el-form-item>
        <el-form-item label="角色权重" prop="roleWeight">
          <el-input v-model="roleForm.roleWeight"></el-input>
        </el-form-item>
        <el-form-item label="角色描述" prop="roleDescribe">
          <el-input type="textarea" v-model="roleForm.roleDescribe" rows="4"></el-input>
        </el-form-item>
        <el-form-item label="角色权限">
        </el-form-item>
        <div class="RightsView">
          <el-collapse v-model="rightForm.activeNames" @change="handleChange" class="collapseMain">
            <el-collapse-item name="1">
              <template slot="title">
                权限相关<i class="el-icon-cpu collapseItemI"></i>
              </template>
              <EditRoleRightsDetail v-for="item in rightForm.RightRight"
                                    :detail.sync="item"
              />
            </el-collapse-item>
            <el-collapse-item name="2">
              <template slot="title">
                公文相关<i class="el-icon-set-up collapseItemI"></i>
              </template>
              <EditRoleRightsDetail v-for="item in rightForm.DocumentRight"
                                    :detail.sync="item"
              />
            </el-collapse-item>
            <el-collapse-item name="3">
              <template slot="title">
                用户相关<i class="el-icon-s-custom collapseItemI"></i>
              </template>
              <EditRoleRightsDetail v-for="item in rightForm.UserRight"
                                    :detail.sync="item"
              />
            </el-collapse-item>
            <el-collapse-item name="4">
              <template slot="title">
                部门相关<i class="el-icon-s-grid collapseItemI"></i>
              </template>
              <EditRoleRightsDetail v-for="item in rightForm.DepartmentRight"
                                    :detail.sync="item"
              />
            </el-collapse-item>
          </el-collapse>
        </div>
        <el-form-item>
          <el-button type="primary" @click="updateRole('roleForm')">添加角色</el-button>
          <el-button @click="resetRole()">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import AppIntroHeader from "@/components/AppIntroHeader";
import request from "@/utils/request";
import EditRoleRightsDetail from "@/components/EditRoleRightsDetail";

export default {
  name: "RoleAdd",
  components: {EditRoleRightsDetail, AppIntroHeader},
  data() {
    const checkWeight = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('请填写权限等级，权限等级越高的用户权限越大'));
      }
      const r = /^\+?[1-9][0-9]*$/;
      if (!r.test(value)) {
        callback(new Error('请输入数字值'));
      } else {
        if (value < 0) {
          callback(new Error('权限等级必须大于零'));
        } else if (value > 998) {
          callback(new Error('权限等级不得大于998'));
        } else {
          callback();
        }
      }
    }
    return {
      roleForm: {
        roleName: '',
        roleDescribe: '',
        roleWeight: ''
      },
      rightForm: {
        RightsData: [],
        RightRight: [],
        DocumentRight: [],
        UserRight: [],
        DepartmentRight: [],
        activeNames: ['1']
      },
      roleRightForm: [],
      hide_required: true,
      rules: {
        roleName: [
          {required: true, message: '请输入角色名称', trigger: 'blur'},
          {max: 10, message: '长度不得大于10', trigger: 'blur'}
        ],
        roleWeight: [
          {validator: checkWeight, trigger: 'blur'}
        ],
        roleDescribe: [
          {required: true, message: '请填写角色描述', trigger: 'blur'},
          {min: 0, max: 200, message: '描述字数不得超过200', trigger: 'change'}
        ]
      }
    }
  },
  computed: {
    newRoleList() {
      return [...this.rightForm.DepartmentRight, ...this.rightForm.DocumentRight, ...this.rightForm.RightRight, ...this.rightForm.UserRight].filter((item) => {
        return item.rightStatue === true
      }).map(item => item.rightId)
    }
  },
  methods: {
    updateRole(roleForm) {
      this.$refs[roleForm].validate((valid) => {
        if (valid) {
          request.post('/roleRelation/addRoleRelation', {
            roleForm:this.roleForm,
            roleRelationForm: this.newRoleList
          }).then(res => {
            if (res.code == '200') {
              this.$notify.success('新添角色信息成功')
              this.$router.back()
            } else {
              this.$notify.error(res.msg)
            }
          })
        }
      })
    }
    ,
    resetRole() {
      this.roleForm = {}
    },
    handleChange(val) {
    },
    async loadRole() {
      await request.get("/right/list").then(res => {
        this.rightForm.RightsData = res
      })
      this.handleRight()
    },
    handleRight() {

      this.rightForm.RightRight = this.rightForm.RightsData.filter((item) => {
        return item.secondId === 1
      })
      this.rightForm.DocumentRight = this.rightForm.RightsData.filter((item) => {
        return item.secondId === 2
      })
      this.rightForm.UserRight = this.rightForm.RightsData.filter((item) => {
        return item.secondId === 3
      })
      this.rightForm.DepartmentRight = this.rightForm.RightsData.filter((item) => {
        return item.secondId === 4
      })
    }
  },
  created() {
    this.loadRole()
  },
}
</script>

<style lang="less" scoped>
.RoleAdd {
  width: 100%;
  height: 100%;
  float: none;

  /deep/ input {
    background-color: #ddded7;
  }

  /deep/ textarea {
    background-color: #ddded7;
    height: 50%;
  }

  .RightsView {
    /deep/ .collapseMain {
      * {
        background-color: transparent !important;
      }

      .collapseItemI {
        margin-left: 10px;
      }

      .RightsDetail:hover {
        background-color: #c6c6b9 !important;
      }
    }
  }

  .RoleAddViewbody {
    margin-top: 10%;
  }
}
</style>