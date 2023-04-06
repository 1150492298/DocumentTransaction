<template>
  <div class="DepartmentManageView">
    <AppIntroHeader HeaderClass="部门管理"></AppIntroHeader>
    <div class="DepartmentManageViewInput">
      <el-input
          placeholder="部门名"
          suffix-icon="el-icon-s-check"
          class="Department"
          v-model="departmentManageForm.departmentName"
      >
      </el-input>
      <div class="DepartmentButton">
        <button class="el-icon-search" @click="pageDepartment"></button>
        <button class="el-icon-refresh" @click="resetDepartment"></button>
      </div>
    </div>
    <div class="DepartmentManageViewBody">
      <div class="DepartmentManageDetail">
        <DepartmentDetail v-for="(DepartmentDetail,index) in pageDepartmentList.list" :key="index"
                          :DepartmentDetail="DepartmentDetail"/>
      </div>
      <div class="document-page">
        <el-pagination
            :page-size="5"
            :pager-count="pagerCount"
            background
            layout="prev, pager, next"
            :total="pageDepartmentList.total"
            :small="pagerSmall"
            @current-change="rolePageChange">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import DepartmentDetail from "@/components/DepartmentDetail";

export default {
  name: "RightOperate",
  components: {DepartmentDetail},
  data() {
    return {
      departmentManageForm: {
        pageNum: 1,
        departmentName: null
      },
      minCount: parseInt(document.body.clientWidth / 100),
      pagerSmall: true,
      pagerCount: 5,
      roleCount: 0,
    }
  },
  methods: {
    pagerTest() {
      this.pagerCount = this.minCount > 5 ? this.minCount : 5
      this.pagerSmall = this.minCount <= 5
    },
    rolePageChange(page) {
      this.departmentManageForm.pageNum = page
      this.pageDepartment()
    },
    pageDepartment() {
      this.$store.dispatch('pageDepartmentList', this.departmentManageForm)
    },
    resetDepartment() {
      this.departmentManageForm = {
        pageNum: 1,
        departmentName: null
      }
      this.pageDepartment()
    }
  },
  mounted() {
    this.pagerTest()
    this.pageDepartment()
  },
  computed: {
    pageDepartmentList() {
      return this.$store.state.pageDepartmentList
    }
  }
}
</script>

<style lang="less" scoped>
* {
  width: 100%;
  float: left;
}

.DepartmentManageView {
  height: 100%;

  /deep/ input {
    background-color: #ddded7;
    height: 100%;
  }
  .DepartmentManageViewInput{
    height: 8%;
    .Department{
      height: 100%;
      width: 70%;
    }
    .DepartmentButton{
      height: 100%;
      width: 30%;
      button {
        height: 100%;
        width:49%;
        margin-left: 1%;
        background-color: #dcded2;
        border: 1px solid #dcded2;
      }
    }
  }

  .DepartmentManageViewBody {
    height: 80%;

    .DepartmentManageDetail {
      margin-top: 1%;
      height: 89%;
    }

    .document-page {
      margin-top: 2%;
      height: 8%;
    }
  }

}
</style>