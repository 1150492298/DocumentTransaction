<template>
  <div class="DocumentClassDetail">
    <div class="DocumentClassDetailHeader">
      <div class="DocumentClassDetailName">
        类别名：{{DocumentClassDetail.documentClassName}}
      </div>
      <div class="DocumentClassDetailButton">
        <div class="DocumentClassDetailButtonChild">
          <el-button size="mini" type="info" @click="$router.push('/documentClassEdit?documentClassId='+DocumentClassDetail.documentClassId)" round>编辑</el-button>
        </div>
      </div>
    </div>
    <div class="DocumentClassDetailFooter">
      <div class="DocumentClassDetailDescribe">
        类别介绍：{{DocumentClassDetail.documentClassDescribe}}
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: "DocumentClassDetail",
  props: ["DocumentClassDetail"],
  data() {
    return {}
  },
  methods:{
  }
}
</script>

<style lang="less" scoped>
.DocumentClassDetail{
  *{
    text-overflow: ellipsis ;
    float: left;
    width: 100%;
    overflow: hidden;
    line-height: 100%;
    border-right: 1px solid #c6c2b7;
  }
  display: inline-block;
  text-align: left;
  height: 20%;
  font-size: 80%;
  border-top: 1px solid #cdc4c4;
  border-bottom: 1px solid #cdc4c4;
  .DocumentClassDetailHeader{
    height: 30%;
    border-bottom: 1px solid #c4cbce;

    .DocumentClassDetailName{
      width: 80%;
      height: 100%;
    }
    .DocumentClassDetailButton{
      width: 20%;
      height: 100%;
      .DocumentClassDetailButtonChild{
        height: 100%;
        button{
          line-height: 70%;
          height: 100%;
        }
      }
    }
  }
  .DocumentClassDetailFooter{
    height: 70%;
    .DocumentClassDetailDescribe{
      width: 100%;
      height: 100%;
    }
  }
}
</style>