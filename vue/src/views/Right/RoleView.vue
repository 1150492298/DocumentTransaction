<template>
  <div class="RoleView">
    <AppIntroHeader HeaderClass="权限详情"></AppIntroHeader>
    <div class="RoleAddViewBody">
      <el-form :model="roleForm" label-width="auto"
               class="demo-ruleForm mystify_form" size="mini"
               :hide-required-asterisk='hide_required'>
        <el-form-item label="角色编号">
          <el-input class="inputReadOnly" readonly="readonly" type="textarea" v-model="roleForm.roleId" rows="1"></el-input>
        </el-form-item>
        <el-form-item label="角色名称">
          <el-input class="inputReadOnly" readonly="readonly" v-model="roleForm.roleName"></el-input>
        </el-form-item>
        <el-form-item label="角色权重">
          <el-input class="inputReadOnly" readonly="readonly" v-model="roleForm.roleWeight"></el-input>
        </el-form-item>
        <el-form-item label="角色简介">
          <el-input class="inputReadOnly" readonly="readonly" type="textarea" v-model="roleForm.roleDescribe" rows="4"></el-input>
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
      </el-form>
    </div>
  </div>
</template>

<script>
import AppIntroHeader from "@/components/AppIntroHeader";
import request from "@/utils/request";
import EditRoleRightsDetail from "@/components/EditRoleRightsDetail";

export default {
  name: "RoleView",
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
.RoleView {
  width: 100%;
  height: 100%;
  float: none;
  overflow: scroll;

  /deep/ input {
    background-color: #cdd2c0;
  }

  /deep/ textarea {
    background-color: #cdd2c0;
  }

  ///deep/ .inputReadOnly {
  //  input {
  //    background-color: #cdd2c0;
  //  }
  //
  //  textarea {
  //    background-color: #cdd2c0;
  //  }
  //}

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