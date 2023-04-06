<template>
  <div class="UserAboutView">
    <AppIntroHeader HeaderClass="用户信息"></AppIntroHeader>
    <div class="UserAddViewbody">
      <el-form :model="userForm"  label-width="auto"
               class="demo-ruleForm mystify_form" size="mini"
               :hide-required-asterisk='hide_required'>
        <el-form-item label="用户账号">
          <el-input  type="textarea" v-model="userForm.userAccount" rows="1" readonly="readonly"></el-input>
        </el-form-item>
        <el-form-item label="创建时间">
          <el-input  v-model="userForm.createTime" readonly="readonly"></el-input>
        </el-form-item>
        <el-form-item label="用户编号">
          <el-input  v-model="userForm.userId" readonly="readonly"></el-input>
        </el-form-item>
        <el-form-item label="用户姓名">
          <el-input v-model="userForm.userName" readonly="readonly"></el-input>
        </el-form-item>
        <el-form-item label="用户电话">
          <el-input v-model="userForm.userPhone" readonly="readonly"></el-input>
        </el-form-item>
        <el-form-item label="用户性别">
          <el-select v-model="userForm.userSex" :disabled="true" class="inputReadOnly">
            <el-option
                v-for="item in sex"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="用户部门">
          <el-select v-model="userForm.departmentId" :disabled="true" class="inputReadOnly">
            <el-option
                v-for="item in $store.getters.getDepartmentList"
                :key="item.departmentId"
                :label="item.departmentName"
                :value="item.departmentId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="用户角色">
          <el-select v-model="userForm.roleId" :disabled="true" class="inputReadOnly">
            <el-option
                v-for="item in $store.getters.getRoleList"
                :key="item.roleId"
                :label="item.roleName"
                :value="item.roleId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="用户简介">
          <el-input type="textarea" v-model="userForm.userIntro" rows="4" readonly="readonly"></el-input>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import AppIntroHeader from "@/components/AppIntroHeader";
import request from "@/utils/request";

export default {
  name: "UserAboutView",
  components: {AppIntroHeader},
  data() {
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
    };
  },
  created() {
    this.getUserById()
    this.$notify.success('成功查看用户信息')
  },
  methods: {

    getUserById() {
      const userId = parseInt(this.$route.query.userId)
      request.get("/user/adminGetUserById/" + userId).then(res => {
        if (res.code == '200') {
          this.userForm = res.data
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
  }

}
</script>

<style lang="less" scoped>
.UserAboutView {
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

  /deep/.inputReadOnly{
    input{
      color: #010713;
      background-color: #cdd2c0;
    }
    textarea {
      color: #010713;
      background-color: #cdd2c0;
    }
  }

  ///deep/.inputReadOnly{
  //  input{
  //    background-color: #cdd2c0;
  //  }
  //  textarea {
  //    background-color: #cdd2c0;
  //  }
  //}

  .UserAddViewbody {
    margin-top: 2%;
  }

}
</style>