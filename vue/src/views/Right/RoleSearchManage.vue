<template>
  <div class="RoleSearchManageView">
    <AppIntroHeader HeaderClass="权限申请处理"></AppIntroHeader>
    <div class="RoleSearchManageViewBody">
      <div class="RoleSearchManageSearch">
        <div class="RoleSearchManageViewInput">
          <el-input
              placeholder="用户名"
              suffix-icon="el-icon-s-check"
              class="userName"
              v-model="roleSearchManageForm.userName"
          >
          </el-input>
          <el-select v-model="roleSearchManageForm.roleId" placeholder="请求角色" class="roleId">
            <el-option
                v-for="item in $store.getters.getRoleList"
                :key="item.roleId"
                :label="item.roleName"
                :value="item.roleId">
            </el-option>
          </el-select>
        </div>
        <div class="RoleSearchManageViewInput">
          <el-date-picker
              v-model="roleSearchManageForm.searchTime"
              align="right"
              type="date"
              placeholder="请求日期"
              value-format="yyyy-MM-dd"
              class="userUpdateTime"
          >
          </el-date-picker>
          <div class="userButton">
            <button class="el-icon-search" @click="viewRoleSearch"></button>
            <button class="el-icon-refresh" @click="resetSearch"></button>
          </div>
        </div>
      </div>
      <div class="RoleSearchManageDetail">
        <RoleSearchDetail v-for="(RoleSearchDetail,index) in roleSearchData" :key="index" :RoleSearchDetail="RoleSearchDetail"/>
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
import RoleSearchDetail from "@/components/RoleSearchDetail";
import Cookies from "js-cookie";

export default {
  name: "RoleSearchManageView",
  components: {RoleSearchDetail},
  data() {
    return {
      userCookie: null,
      roleSearchManageForm: {
        userName: '',
        searchTime: null,
        roleId: '',
        token: null,
        pageNum: 1
      },
      roleSearchData: [],
      minCount: parseInt(document.body.clientWidth / 100),
      pagerSmall: true,
      pagerCount: 5,
      userCount: 0,
    }
  },
  methods: {
    resetSearch() {
      this.roleSearchManageForm = {
        userName: '',
        searchTime: null,
        roleId: '',
        pageNum: 1,
        token: null
      }
      this.viewRoleSearch()
    },
    viewRoleSearch() {
      this.loadCookie()
      request.get('roleSearch/pageRoleSearch', {
        params: this.roleSearchManageForm
      }).then(res => {
        if (res.code === '200') {
          this.roleSearchData = res.data.list
          this.userCount = res.data.total
        }
      })
    },
    pagerTest() {
      this.pagerCount = this.minCount > 5 ? this.minCount : 5
      this.pagerSmall = this.minCount <= 5
    },
    userPageChange(page) {
      this.roleSearchManageForm.pageNum = page
      this.viewRoleSearch()
    },
    async loadRole() {
      await this.$store.dispatch('getRoleList')
    },
    loadCookie(){
      this.userCookie=Cookies.get('user') ? JSON.parse(Cookies.get('user')) : {}
      this.roleSearchManageForm.token=this.userCookie.token
    }
  },
  mounted() {
    this.loadRole()
    this.pagerTest()
    this.viewRoleSearch()
  },
}
</script>

<style lang="less" scoped>
* {
  width: 100%;
  float: left;
}

.RoleSearchManageView {
  height: 100%;

  /deep/ input {
    background-color: #ddded7;
  }

  .RoleSearchManageViewBody {
    height: 90%;

    .RoleSearchManageSearch {
      /deep/ * {
        height: 100%;
      }

      height: 15%;

      .RoleSearchManageViewInput {
        height: 48%;
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
          width: 49%;
          margin-left: 1%;
          background-color: #dcded2;
          border: 1px solid #dcded2;
        }
      }
    }

    .RoleSearchManageDetail {
      margin-top: 1%;
      height: 70%;
    }

    .document-page {
      margin-top: 2%;
      height: 8%;
    }
  }

}
</style>