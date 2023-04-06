<template>
  <div class="AppIntroHeader">
    <el-page-header @back="goBack" :content="HeaderClass">
    </el-page-header>
  </div>
</template>

<script>
export default {
  props:['HeaderClass'],
  name: "AppIntroHeader",
  methods: {
    goBack() {
      this.$router.back()
    }
  }
}
</script>

<style lang="less" scoped>
  .AppIntroHeader{
    width: 100%;
    height: 10%;
  }
</style>