<template>
  <div class="DepartmentApplyManageView">
    <AppIntroHeader HeaderClass="部门申请处理"></AppIntroHeader>
    <div class="DepartmentApplyManageViewBody">
      <div class="DepartmentApplyManageSearch">
        <div class="DepartmentApplyManageViewInput">
          <el-input
              placeholder="用户名"
              suffix-icon="el-icon-s-check"
              class="userName"
              v-model="departmentApplyManageForm.userName"
          >
          </el-input>
          <el-select v-model="departmentApplyManageForm.departmentId" placeholder="请求部门" class="roleId">
            <el-option
                v-for="item in $store.getters.getDepartmentList"
                :key="item.departmentId"
                :label="item.departmentName"
                :value="item.departmentId"
            >
            </el-option>
          </el-select>
        </div>
        <div class="DepartmentApplyManageViewInput">
          <el-date-picker
              v-model="departmentApplyManageForm.searchTime"
              align="right"
              type="date"
              placeholder="请求日期"
              value-format="yyyy-MM-dd"
              class="userUpdateTime"
          >
          </el-date-picker>
          <div class="userButton">
            <button class="el-icon-search" @click="viewDepartmentApply"></button>
            <button class="el-icon-refresh" @click="resetSearch"></button>
          </div>
        </div>
      </div>
      <div class="DepartmentApplyManageDetail">
        <DepartmentApplyDetail v-for="(DepartmentApplyDetail,index) in departmentApplyData" :key="index" :DepartmentApplyDetail="DepartmentApplyDetail"/>
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
import DepartmentApplyDetail from "@/components/DepartmentApplyDetail";
import Cookies from "js-cookie";

export default {
  name: "DepartmentApplyManageView",
  components: {DepartmentApplyDetail},
  data() {
    return {
      userCookie: null,
      departmentApplyManageForm: {
        userName: '',
        searchTime: null,
        departmentId: '',
      },
      departmentApplyData: [],
      minCount: parseInt(document.body.clientWidth / 100),
      pagerSmall: true,
      pagerCount: 5,
      userCount: 0,
    }
  },
  methods: {
    resetSearch() {
      this.departmentApplyManageForm = {
        userName: '',
        searchTime: null,
        departmentId: '',
      }
      this.viewDepartmentApply()
    },
    viewDepartmentApply() {
      request.get('departmentSearch/pageDepartmentSearch', {
        params: this.departmentApplyManageForm
      }).then(res => {
        if (res.code === '200') {
          this.departmentApplyData = res.data.list
          this.userCount = res.data.total
        }
      })
    },
    pagerTest() {
      this.pagerCount = this.minCount > 5 ? this.minCount : 5
      this.pagerSmall = this.minCount <= 5
    },
    userPageChange(page) {
      this.departmentApplyManageForm.pageNum = page
      this.viewDepartmentApply()
    },
    loadCookie(){
      this.userCookie=Cookies.get('user') ? JSON.parse(Cookies.get('user')) : {}
      this.departmentApplyManageForm.token=this.userCookie.token
    },
    async loadDepartment() {
      await this.$store.dispatch('getDepartmentList')
    },
  },
  mounted() {
    this.loadCookie()
    this.loadDepartment()
    this.pagerTest()
    this.viewDepartmentApply()
  },
}
</script>

<style lang="less" scoped>
* {
  width: 100%;
  float: left;
}

.DepartmentApplyManageView {
  height: 100%;

  /deep/ input {
    background-color: #ddded7;
  }

  .DepartmentApplyManageViewBody {
    height: 90%;

    .DepartmentApplyManageSearch {
      /deep/ * {
        height: 100%;
      }

      height: 15%;
      //border-bottom: 1px solid #cdc4c4;

      .DepartmentApplyManageViewInput {
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

    .DepartmentApplyManageDetail {
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