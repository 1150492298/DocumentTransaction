<template>
    <div class="Home">
      <div class="router-view">
        <router-view/>
      </div>
      <AppFooter></AppFooter>
    </div>
</template>

<script>
export default {
  name: "Home",
  data(){
    return{
    }
  },
  mounted() {
  }
}
</script>

<style lang="less">
.Home{
  .router-view{
    height: 90vh;
    overflow: scroll;
  }
  .AppFooter{
    position: absolute;
    bottom: 0;
    height: 10%;
  }
}
</style>

<script>
import AppFooter from "@/components/AppFooter";
export default {
  components: {AppFooter}
}
</script>