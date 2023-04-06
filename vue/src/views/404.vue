<template>
  <div class="view404">
    <div class="view404Text">
      404
    </div>
    <div>
      未找到页面!
    </div>
    <div>
      <el-button type="text" @click="$router.push('/')">返回主页</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "view404"
}
</script>

<style lang="less" scoped>
  .view404{
    margin-top: 150px;
    font-size: 40px;
    button{
      font-size: 20px;
    }
}
</style>