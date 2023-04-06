<template>
  <div class="AppHeader">
    <div class="AppHeaderImage">
      <img src="@/assets/logo.png"/>
    </div>
    <div class="AppHeaderIntro">
      您好，{{$store.getters.getUserForm.userName}}
    </div>
    <div class="AppHeaderExit">
      <el-popconfirm title="您确定要注销账户吗" @confirm="exit" >
        <el-button type="info" slot="reference" >注销</el-button>
      </el-popconfirm>
    </div>
  </div>
</template>

<script>
import Cookies from 'js-cookie'
export default {
  name: "AppHeader",
  methods:{
    exit(){
      Cookies.remove('user')
      this.$router.push('/login')
    }
  }
}
</script>

<style lang="less" scoped>
.AppHeader {
  position: relative;
  height: 5%;
  width: 100%;
  background-color: #e7e7de;

  .AppHeaderImage {
    float: left;
    height: 100%;
    width: 20%;

    img {
      float: left;
      height: 100%;
    }
  }

  .AppHeaderIntro {
    float: left;
    text-align: center;
    font-size: 100%;
    height: 100%;
    width: 60%;
  }

  .AppHeaderExit {
    height: 100%;
    float: right;
    width: 20%;

    button {
      line-height: 80%;
      width: 100%;
    }
  }
}
</style>