<template>
  <div class="DocumentView">
    <AppHeader/>
    <div class="demo-input-size Document-Search">
      <el-input
          size="mini"
          placeholder="发布人"
          suffix-icon="el-icon-s-check"
          class="DocumentSearchInput"
          v-model="searchParams.userName"
      >
      </el-input>
      <el-input
          size="mini"
          placeholder="公文名"
          suffix-icon="el-icon-s-management"
          class="DocumentSearchInput"
          v-model="searchParams.documentName">
      </el-input>
      <div class="DocumentSearchInput2">
        <el-select v-model="searchParams.documentClassId" placeholder="公文类型" class="DocumentSearchInput3"
                   @focus="loadDepartment">
          <el-option
              v-for="item in $store.getters.getDocumentClassList"
              :key="item.documentClassId"
              :label="item.documentClassName"
              :value="item.documentClassId">
          </el-option>
        </el-select>
        <div class="DocumentSearchInput6">
          <div class="DocumentSearchInput5">
            <button class="el-icon-search" @click="loadDocument"></button>
          </div>
          <div class="DocumentSearchInput5">
            <button class="el-icon-refresh" @click="resetDocument"></button>
          </div>
        </div>
        <div class="block">
          <el-date-picker
              v-model="searchParams.documentPostTime"
              align="right"
              type="date"
              placeholder="选择日期"
              value-format="yyyy-MM-dd"
              class="DocumentSearchInput4"
          >
          </el-date-picker>
        </div>
      </div>
    </div>
    <DocumentDetail v-for="(DocumentDetail,index) in documentData" :key="index" :DocumentDetail="DocumentDetail"/>
    <div class="document-page">
      <el-pagination
          :page-size="5"
          :pager-count="pagerCount"
          background
          layout="prev, pager, next"
          :total="documentCount"
          :small="pagerSmall"
          @current-change="documentPageChange">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import DocumentDetail from "@/components/DocumentDetail";
import AppHeader from "@/components/AppHeader";
import request from "../../utils/request";
import Cookies from "js-cookie";

export default {
  name: "DocumentView",
  components: {
    AppHeader,
    DocumentDetail
  },
  methods: {
    async loadDocument() {
      await request.get('document/pageDocuments', {
        params: this.searchParams
      }).then(res => {
        if (res.code === '200') {
          this.documentData = res.data.list
          this.documentCount = res.data.total
        }
      })
    },
    async loadDepartment() {
      await this.$store.dispatch('getDepartmentList')
    },
    async loadUserForm() {
      await this.$store.dispatch('getUserFormById', this.userCookie.token)
    },
    async loadRole() {
      await this.$store.dispatch('getRoleList')
    },
    async loadDocumentClass() {
      await this.$store.dispatch('getDocumentClassList')
    },
    resetDocument() {
      this.searchParams = {
        userName: '',
        documentName: '',
        DocumentPostTime: '',
        documentClassId: '',
        documentStatue:2,
        pageNum: 1
      }
      this.loadDocument()
    },
    pagerTest() {
      this.pagerCount = this.minCount > 5 ? this.minCount : 5
      this.pagerSmall = this.minCount <= 5
    },
    documentPageChange(page) {
      this.searchParams.pageNum = page
      this.loadDocument()
    },
  },
  data() {
    return {
      minCount: parseInt(document.body.clientWidth / 100),
      pagerSmall: true,
      pagerCount: 5,
      documentCount: 0,
      userCookie: Cookies.get('user') ? JSON.parse(Cookies.get('user')) : {},
      searchParams: {
        userName: '',
        documentName: '',
        DocumentPostTime: '',
        documentClassId: 3,
        documentStatue:2,
        pageNum: 1
      },
      documentData: [],
    }
  },
  watch: {},
  mounted() {
    this.pagerTest()
    this.loadDocument()
    this.loadRole()
    this.loadUserForm()
    this.loadDepartment()
    this.loadDocumentClass()
  },
}
</script>

<style lang="less" scoped>
.DocumentView {
  height: 100%;
  width: 100%;
  position: relative;

  .DocumentDetail {
    height: 12%;
    border-bottom: 1px solid #cdc4c4;
  }

  .Document-Search {
    height: 19%;
    padding-bottom: 2px;
    border-bottom: 1px solid #cdc4c4;

    /deep/ input {
      background-color: #ddded7;
    }

    .DocumentSearchInput {
      margin-bottom: 3px;
      height: calc(50% - 19px);

      /deep/ input {
        height: 100%;
      }
    }

    .DocumentSearchInput2 {
      height: 36px;

      .DocumentSearchInput3 {
        float: left;
        width: 30%;
        margin-right: 3px;
        height: 100%;
      }

      .DocumentSearchInput4 {
        float: left;
        width: 49%;
      }

      .DocumentSearchInput6 {
        width: 20%;
        height: 100%;
        float: right;

        .DocumentSearchInput5 {
          height: 100%;
          margin: auto;
          width: 50%;
          line-height: 100%;
          float: left;
          font-size: 30%;

          button {
            height: 90%;
            width: 90%;
            margin-left: 3px;
            background-color: #dcded2;
            border: 1px solid #ddded7;
          }
        }
      }
    }
  }

  .document-page {
    width: 100%;
    height: 10%;
    position: absolute;
    bottom: 0;
  }
}
</style>
