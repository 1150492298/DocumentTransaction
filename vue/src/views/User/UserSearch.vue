<template>
  <div class="UserSearch">
    <AppIntroHeader HeaderClass="用户管理"></AppIntroHeader>
    <div class="UserManageViewBody">
      <div class="UserManageSearch">
        <div class="UserManageViewInput">
          <el-input
              placeholder="用户名"
              suffix-icon="el-icon-s-check"
              class="userName"
              v-model="userSearchForm.userName"
          >
          </el-input>
          <el-select v-model="userSearchForm.roleId" placeholder="用户角色" class="roleId">
            <el-option
                v-for="item in $store.getters.getRoleList"
                :key="item.roleId"
                :label="item.roleName"
                :value="item.roleId">
            </el-option>
          </el-select>
        </div>
        <div class="UserManageViewInput">
          <el-input
              placeholder="用户电话"
              suffix-icon="el-icon-s-management"
              class="userPhone"
              v-model="userSearchForm.userPhone">
          </el-input>
          <el-select v-model="userSearchForm.departmentId" placeholder="用户部门" class="departmentId">
            <el-option
                v-for="item in $store.getters.getDepartmentList"
                :key="item.departmentId"
                :label="item.departmentName"
                :value="item.departmentId">
            </el-option>
          </el-select>
        </div>
        <div class="UserManageViewInput">
          <el-date-picker
              v-model="userSearchForm.updateTime"
              align="right"
              type="date"
              placeholder="更新日期"
              value-format="yyyy-MM-dd"
              class="userUpdateTime"
          >
          </el-date-picker>
          <div class="userButton">
            <button class="el-icon-search" @click="viewUser"></button>
            <button class="el-icon-refresh" @click="resetSearch"></button>
          </div>
        </div>
      </div>
      <div class="UserManageDetail">
        <UserSearchAbout v-for="(UserAbout,index) in userData" :key="index" :UserAbout="UserAbout"/>
      </div>
      <div class="document-page">
        <el-pagination
            :page-size="5"
            :pager-count="pagerCount"
            background
            layout="prev, pager, next"
            :total="userCount"
            :small="pagerSmall"
            @current-change="userPageChange">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>

import request from "@/utils/request";
import UserAbout from "@/components/UserAbout";
import UserSearchAbout from "@/components/UserSearchAbout";

export default {
  name: "UserSearch",
  components: {UserSearchAbout},
  data() {
    return {
      userSearchForm: {
        userName: '',
        updateTime: '',
        userPhone: '',
        departmentId: '',
        roleId: '',
        pageNum: 1
      },
      userData:[],
      minCount: parseInt(document.body.clientWidth / 100),
      pagerSmall: true,
      pagerCount: 5,
      userCount: 0,
    }
  },
  methods:{
    resetSearch(){
      if(this.userSearchForm!=={
        userName: '',
        updateTime: '',
        userPhone: '',
        departmentId: '',
        roleId: '',
        pageNum: 1
      }){
        this.userSearchForm={
          userName: '',
          updateTime: '',
          userPhone: '',
          departmentId: '',
          roleId: '',
          pageNum: 1
        }
        this.viewUser()
      }
    },
    viewUser(){
      request.get('user/pageUsers', {
        params: this.userSearchForm
      }).then(res => {
        if (res.code === '200') {
          this.userData = res.data.list
          this.userCount = res.data.total
        }
      })
    },
    pagerTest() {
      this.pagerCount = this.minCount > 5 ? this.minCount : 5
      this.pagerSmall = this.minCount <= 5
    },
    userPageChange(page) {
      this.userSearchForm.pageNum = page
      this.viewUser()
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
    this.pagerTest()
    this.viewUser()
  },
}
</script>

<style lang="less" scoped>
*{
  width: 100%;
  float: left;
}
.UserSearch {
  height: 100%;
  /deep/input {
    background-color: #ddded7;
  }

  .UserManageViewBody {
    height: 90%;
    .UserManageSearch{
      /deep/ *{
        height: 100%;
      }
      height: 23%;
      border-bottom: 1px solid #cdc4c4;

      .UserManageViewInput {
        height: 32%;
        margin-bottom: 1px;

        .userName {
          height: 100%;
          width: 65%;
        }

        .roleId {
          height: 100%;
          float: right;
          width: calc(35% - 2px);
        }

        .departmentId {
          height: 100%;
          float: right;
          width: calc(35% - 2px);
        }

        .userPhone {
          height: 100%;
          width: 65%;
        }

        .userUpdateTime {
          width: 70%;
          height: 100%;
        }

      }

      .userButton {
        width: 29%;
        height: 100%;
        float: right;
        font-size: 30%;
        margin-left: 1%;

        button {
          width:49%;
          margin-left: 1%;
          background-color: #dcded2;
          border: 1px solid #dcded2;
        }
      }
    }
    .UserManageDetail{
      margin-top: 1%;
      height: 63%;
    }
    .document-page{
      margin-top: 2%;
      height: 8%;
    }
  }

}
</style>