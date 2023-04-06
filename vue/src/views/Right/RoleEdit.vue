<template>
  <div class="RoleEdit">
    <AppIntroHeader HeaderClass="权限管理"></AppIntroHeader>
    <div class="RoleAddViewBody">
      <el-form :model="roleForm" :rules="rules" ref="roleForm" label-width="auto"
               class="demo-ruleForm mystify_form" size="mini"
               :hide-required-asterisk='hide_required'>
        <el-form-item label="角色编号">
          <el-input class="inputReadOnly" type="textarea" v-model="roleForm.roleId" rows="1"
                    readonly="readonly"></el-input>
        </el-form-item>
        <el-form-item label="角色名称" prop="roleName">
          <el-input v-model="roleForm.roleName"></el-input>
        </el-form-item>
        <el-form-item label="角色权重" prop="roleWeight">
          <el-input v-model="roleForm.roleWeight"></el-input>
        </el-form-item>
        <el-form-item label="角色简介" prop="roleDescribe">
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
          <el-button type="primary" @click="updateRole('roleForm')">更新角色</el-button>
          <el-button @click="resetForm">重置信息</el-button>
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
  name: "RoleEdit",
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
        roleId: '',
        roleName: '',
        roleDescribe: '',
        roleWeight: '',
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
    };
  },
  created() {
    this.loadRole()
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
          request.put('/role/updateRoleById', this.roleForm).then(res => {
            if (res.code == '200') {
              request.post('/roleRelation/saveRoleRelation', {
                roleRelationForm:this.newRoleList,
                roleId:this.roleForm.roleId
              }).then(res => {
                if (res.code == '200') {
                  this.$notify.success('更新角色信息成功')
                  this.$router.back()
                } else {
                  this.$notify.error(res.msg)
                }
              })
            }else {
              this.$notify.error(res.msg)
            }
          })
        } else {
          this.$notify.error('角色信息不符合规范')
        }
      })
    },
    resetForm() {
      this.loadRole()
      this.$notify.success('刷新角色信息界面')
    },
    handleChange(val) {
    },
    async loadRole() {
      const roleId = parseInt(this.$route.query.roleId)
      await request.get("/roleRelation/getRightByRoleId/" + roleId).then(res => {
        if (res.code == '200') {
          this.roleRightForm = res.data
        } else {
          this.$notify.error(res.msg)
        }
      })
      await request.get("/right/list").then(res => {
        this.rightForm.RightsData = res
      })
      await request.get("/role/getRoleById/" + roleId).then(res => {
        if (res.code == '200') {
          this.roleForm = res.data
        } else {
          this.$notify.error(res.msg)
        }
      })
      this.handleRight()
    },
    handleRight() {

      for (const i in this.rightForm.RightsData) {
        for (const j in this.roleRightForm) {
          if (this.rightForm.RightsData[i].rightId == this.roleRightForm[j].rightId) {
            this.rightForm.RightsData[i].rightStatue = true
          }
        }
      }

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
  }
}
</script>

<style lang="less" scoped>
.RoleEdit {
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

  /deep/ .inputReadOnly {
    input {
      background-color: #cdd2c0;
    }

    textarea {
      background-color: #cdd2c0;
    }
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

  .RoleAddViewBody {
    margin-top: 2%;
  }

}
</style>