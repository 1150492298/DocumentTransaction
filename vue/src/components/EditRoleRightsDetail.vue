<template>
  <div class="EditRoleRightsDetail" @click="checkChange" :title="detail.rightDescribe">
    <div class="EditRoleRightsDetailRadio">
      <el-checkbox v-model="RoleCheck" @change="checkChange"></el-checkbox>
    </div>
    <div class="EditRoleRightsDetailHeader">
      <i :class="detail.rightIcon"></i>
    </div>
    <div class="EditRoleRightsDetailBottom">
      {{ detail.rightClass }}
    </div>
  </div>
</template>

<script>
export default {
  name: "EditRoleRightsDetail",
  props: ['detail'],
  data() {
    return {
      EditRoleRightsDetailClass: "el-icon-reading",
      EditRoleRightsDetailBottom: '系统功能',
      RoleCheck:false
    }
  },
  computed:{
    // RoleCheck(){
    //   return this.detail.rightStatue
    // }
  },
  methods:{
    checkChange(){
      this.detail.rightStatue=!this.detail.rightStatue
      this.RoleCheck=!this.RoleCheck
    }
  },
  mounted() {
    this.RoleCheck=this.detail.rightStatue
  }
}
</script>

<style lang="less" scoped>
.EditRoleRightsDetail {
  border-radius: 20%;
  float: left;
  width: 60px;
  height: 60px;
  margin: 20px;
  margin-left: 0;
  border: 1px solid #a1a0a0;
  background-color: #e2e2dd !important;
  text-align: center;
  vertical-align: top;
  position: relative;

  .EditRoleRightsDetailRadio{
    position: absolute;
    top:-5%;
    right: 5%;

    /* 设置选中后对勾框的边框和背景颜色 */
    /deep/ .el-checkbox__input.is-checked .el-checkbox__inner, .myRedCheckBox .el-checkbox__input.is-indeterminate .el-checkbox__inner {
      border-color: #afe2e2 !important;
      background-color:#afe2e2!important;
      color: black!important;
    }

    /deep/ .el-checkbox__input .el-checkbox__inner, .myRedCheckBox .el-checkbox__input.is-indeterminate .el-checkbox__inner {
      border-color: #afe2e2 !important;
      background-color: #dcdcc8 !important;
    }

  }


  .EditRoleRightsDetailHeader {
    font-size: 200%;
    width: 100%;
    height: 70%;
  }

  .EditRoleRightsDetailBottom {
    vertical-align: top;
    font-size: 40%;
    width: 100%;
    height: 30%;
  }
}
</style>