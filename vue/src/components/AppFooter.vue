<template>
  <div class="AppFooter">
    <div class="footer-third" :class="FooterSelectedDocument" @click="ChangeToDocument">
      <div class="footer-shadow"></div>
      <div class="el-icon-tickets footer-icon"/>
      <div class="footer-message">公文</div>
    </div>
    <div class="footer-third" :class="FooterSelectedRight" @click="ChangeToRight">
      <div class="footer-shadow"></div>
      <div class="el-icon-share footer-icon"></div>
      <div class="footer-message">功能</div>
    </div>
    <div class="footer-third" :class="FooterSelectedMyself" @click="ChangeToMyself">
      <div class="footer-shadow"></div>
      <div class="el-icon-user-solid footer-icon"></div>
      <div class="footer-message">我的</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "AppFooter",
  data() {
    return {
      FooterSelectedDocument: 'footer-selected',
      FooterSelectedRight: '',
      FooterSelectedMyself: '',
    }
  },
  methods: {
    ChangeToDocument() {
      this.$router.push('/document')
      this.FooterSelectedDocument = 'footer-selected'
      this.FooterSelectedRight = ''
      this.FooterSelectedMyself = ''
    },
    ChangeToRight() {
      this.$router.push('/right')
      this.FooterSelectedDocument = ''
      this.FooterSelectedRight = 'footer-selected'
      this.FooterSelectedMyself = ''
    },
    ChangeToMyself() {
      this.$router.push('/myself')
      this.FooterSelectedDocument = ''
      this.FooterSelectedRight = ''
      this.FooterSelectedMyself = 'footer-selected'
    },

  },
  mounted() {
    this.$router.push('/document')
  }
}
</script>


<style lang="less" scoped>
.AppFooter {
  font-family: "PingFang SC", sans-serif;
  width: 100%;
  background-color: #e7e7de;
  .footer-selected {
    color: #204ed7;
    text-shadow: -2px 1px 3px grey;
  }

  .footer-third {
    position: relative;
    float: left;
    width: 20%;
    height: 100%;
    line-height: 100%;
    margin-left: 10%;
    text-align: center;
    transition: color, text-shadow .3s linear;
    .footer-shadow{
      height: 20%;
    }
    .footer-icon {
      height: 50%;
      font-size: 180%;
    }
    .footer-message {
      font-size: 60%;
      height: 30%;
    }
  }
}
</style>