<template>
  <div id="app">
    <div class="router-view">
      <router-view />
    </div>
  </div>
</template>

<style lang="less" scoped>
body{
  #app {
    float: left;
    background-color: #e8e8e3;
    width: 100%;
    height: 100%;
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
  }
}

</style>

<script>
// import AppFooter from "@/components/AppFooter";
// export default {
//   components: {AppFooter}
// }
</script>