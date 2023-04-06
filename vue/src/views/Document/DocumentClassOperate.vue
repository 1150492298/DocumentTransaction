<template>
  <div class="DocumentClassOperate">
    <AppIntroHeader HeaderClass="公文流程管理"></AppIntroHeader>
    <div class="DocumentClassOperateViewBody">
      <div class="DocumentClassOperateDetail">
        <DocumentClassDetail v-for="(DocumentClassDetail,index) in DocumentClassDetail" :key="index" :DocumentClassDetail="DocumentClassDetail"/>
      </div>
      <div class="document-page">
        <el-pagination
            :page-size="5"
            :pager-count="pagerCount"
            background
            layout="prev, pager, next"
            :total="documentClassCount"
            :small="pagerSmall"
            @current-change="rolePageChange">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import DocumentClassDetail from "@/components/DocumentClassDetail";
import request from "@/utils/request";

export default {
  name: "DocumentClassOperate",
  components: {DocumentClassDetail},
  data() {
    return {
      documentClassSearchForm: {
        pageNum: 1
      },
      minCount: parseInt(document.body.clientWidth / 100),
      pagerSmall: true,
      pagerCount: 5,
      documentClassCount: 0,
      DocumentClassDetail:[]
    }
  },
  methods:{
    pagerTest() {
      this.pagerCount = this.minCount > 5 ? this.minCount : 5
      this.pagerSmall = this.minCount <= 5
    },
    rolePageChange(page) {
      this.documentClassSearchForm.pageNum = page
      this.loadDocumentClass()
    },
    loadDocumentClass() {
      request.get('/documentClass/pageDocumentClass', {
        params:this.documentClassSearchForm}).then(res => {
        if (res.code === '200') {
          this.DocumentClassDetail = res.data.list
          this.documentClassCount = res.data.total
        }
      })
    },
  },
  mounted() {
    this.pagerTest()
    this.loadDocumentClass()
  },
}
</script>

<style lang="less" scoped>
*{
  width: 100%;
  float: left;
}
.DocumentClassOperate {
  height: 100%;
  /deep/input {
    background-color: #ddded7;
  }

  .DocumentClassOperateViewBody {
    height: 90%;
    .DocumentClassOperateDetail{
      margin-top: 1%;
      height: 89%;
    }
    .document-page{
      margin-top: 2%;
      height: 8%;
    }
  }

}
</style>