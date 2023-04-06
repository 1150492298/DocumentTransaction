<template>
  <div class="RightsDetail" @click="routerChange()" :title="detail.rightDescribe">
    <div class="RightsDetailHeader">
      <i :class="detail.rightIcon"></i>
    </div>
    <div class="RightsDetailBottom">
      {{ detail.rightClass }}
    </div>
  </div>
</template>

<script>
export default {
  name: "RightsDetail",
  props: ['detail'],
  data() {
    return {
      RightsDetailClass: "el-icon-reading",
      RightsDetailBottom: '系统功能'
    }
  },
  methods:{
    routerChange(){
      this.$router.push(this.detail.rightOperate)
    },
  }
}
</script>

<style lang="less" scoped>
.RightsDetail {
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



  .RightsDetailHeader {
    font-size: 200%;
    width: 100%;
    height: 70%;
  }

  .RightsDetailBottom {
    vertical-align: top;
    font-size: 40%;
    width: 100%;
    height: 30%;
  }
}
</style>