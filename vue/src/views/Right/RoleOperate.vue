<template>
  <div class="RoleOperateView">
    <AppIntroHeader HeaderClass="权限管理"></AppIntroHeader>
    <div class="RoleOperateViewBody">
      <div class="RoleOperateDetail">
        <RoleDetail v-for="(RoleDetail,index) in roleData.list" :key="index" :RoleDetail="RoleDetail"/>
      </div>
      <div class="document-page">
        <el-pagination
            :page-size="5"
            :pager-count="pagerCount"
            background
            layout="prev, pager, next"
            :total="roleData.total"
            :small="pagerSmall"
            @current-change="rolePageChange">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import RoleDetail from "@/components/RoleDetail";

export default {
  name: "RightOperate",
  components: {RoleDetail},
  data() {
    return {
      roleSearchForm: {
        pageNum: 1
      },
      minCount: parseInt(document.body.clientWidth / 100),
      pagerSmall: true,
      pagerCount: 5,
      roleCount: 0,
    }
  },
  methods:{
    pagerTest() {
      this.pagerCount = this.minCount > 5 ? this.minCount : 5
      this.pagerSmall = this.minCount <= 5
    },
    rolePageChange(page) {
      this.roleSearchForm.pageNum = page
      this.loadRole()
    },
    loadRole() {
      this.$store.dispatch('pageRoleList',this.roleSearchForm)
    },
  },
  mounted() {
    this.pagerTest()
    this.loadRole()
  },
  computed:{
    roleData(){
      return this.$store.state.roleList
    }
  }
}
</script>

<style lang="less" scoped>
*{
  width: 100%;
  float: left;
}
.RoleOperateView {
  height: 100%;
  /deep/input {
    background-color: #ddded7;
  }

  .RoleOperateViewBody {
    height: 90%;
    .RoleOperateDetail{
      margin-top: 1%;
      height: 89%;
    }
    .document-page{
      margin-top: 2%;
      height: 8%;
    }
  }

}
</style>