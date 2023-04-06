<template>
  <div class="RightsView">
    <el-collapse v-model="activeNames" @change="handleChange" class="collapseMain">
      <el-collapse-item name="1">
        <template slot="title">
          权限相关<i class="el-icon-cpu collapseItemI"></i>
        </template>
        <RightsDetail v-for="item in RightRight"
                      :detail="item"
        />
      </el-collapse-item>
      <el-collapse-item name="2">
        <template slot="title">
          公文相关<i class="el-icon-set-up collapseItemI"></i>
        </template>
        <RightsDetail v-for="item in DocumentRight"
                      :detail="item"
        />
      </el-collapse-item>
      <el-collapse-item name="3">
        <template slot="title">
          用户相关<i class="el-icon-s-custom collapseItemI"></i>
        </template>
        <RightsDetail v-for="item in UserRight"
                      :detail="item"
        />
      </el-collapse-item>
      <el-collapse-item name="4">
        <template slot="title">
          部门相关<i class="el-icon-s-grid collapseItemI"></i>
        </template>
        <RightsDetail v-for="item in DepartmentRight"
                      :detail="item"
        />
      </el-collapse-item>
    </el-collapse>
  </div>
</template>

<script>
import RightsDetail from "@/components/RightsDetail";
import request from "@/utils/request";

export default {
  name: "RightsView",
  components: {RightsDetail},
  data() {
    return {
      RightsData: [],
      RightRight: [],
      DocumentRight: [],
      UserRight: [],
      DepartmentRight: [],
      activeNames: ['1'],
      roleRightForm:[]
    }
  },
  created() {
    this.load()
  },
  methods: {
    handleChange(val) {
    },
    async load() {
      await request.get("/roleRelation/getRightDetailByRoleId/" + parseInt(this.$store.getters.getUserForm.roleId)).then(res => {
        if (res.code == '200') {
          this.RightsData = res.data
          this.handleRight()
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
    //   await request.get("/right/list").then(res => {
    //     this.RightsData = res
    //     this.handleRight()
    //   })
    // },
    handleRight() {
      this.RightRight = this.RightsData.filter((item) => {
        return item.secondId === 1
      })
      this.DocumentRight = this.RightsData.filter((item) => {
        return item.secondId === 2
      })
      this.UserRight = this.RightsData.filter((item) => {
        return item.secondId === 3
      })
      this.DepartmentRight = this.RightsData.filter((item) => {
        return item.secondId === 4
      })
    }
  }
}
</script>

<style lang="less" scoped>
.RightsView {
  /deep/ .collapseMain {
    * {
      background-color: transparent !important;
    }

    .collapseItemI {
      margin-left: 10px;
    }

    .RightsDetail:hover{
      background-color: #c6c6b9 !important;
    }
  }
}
</style>

