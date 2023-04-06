<template>
  <div class="DocumentExamineView">
    <AppIntroHeader HeaderClass="公文批阅"></AppIntroHeader>
    <div class="DocumentExamineViewBody">
      <div class="DocumentExamineSearch">
        <div class="DocumentExamineViewInput">
          <el-input
              placeholder="公文名"
              suffix-icon="el-icon-s-check"
              class="userName"
              v-model="documentExamineForm.userName"
          >
          </el-input>
        </div>
        <div class="DocumentExamineViewInput">
          <el-date-picker
              v-model="documentExamineForm.documentPostTime"
              align="right"
              type="date"
              placeholder="请求日期"
              value-format="yyyy-MM-dd"
              class="userUpdateTime"
          >
          </el-date-picker>
          <div class="userButton">
            <button class="el-icon-search" @click="viewRoleSearch"></button>
            <button class="el-icon-refresh" @click="resetSearch"></button>
          </div>
        </div>
      </div>
      <div class="DocumentExamineDetailView">
        <DocumentExamineDetail v-for="(DocumentExamineDetail,index) in documentExamineData" :key="index" :DocumentExamineDetail="DocumentExamineDetail"/>
      </div>
      <div class="document-page">
        <el-pagination
            :page-size="5"
            :pager-count="pagerCount"
            background
            layout="prev, pager, next"
            :total="userCount"
            :small="pagerSmall"
            @current-change="userPageChange">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>

import request from "@/utils/request";
import RoleSearchDetail from "@/components/RoleSearchDetail";
import Cookies from "js-cookie";
import DocumentExamineDetail from "@/components/DocumentExamineDetail";

export default {
  name: "DocumentExamineView",
  components: {DocumentExamineDetail, RoleSearchDetail},
  data() {
    return {
      userCookie: null,
      documentExamineForm: {
        userName: '',
        documentPostTime: null,
        pageNum: 1
      },
      documentExamineData: [],
      minCount: parseInt(document.body.clientWidth / 100),
      pagerSmall: true,
      pagerCount: 5,
      userCount: 0,
    }
  },
  methods: {
    resetSearch() {
      this.documentExamineForm = {
        userName: '',
        documentPostTime: null,
        pageNum: 1,
        token:null
      }
      this.viewRoleSearch()
    },
    viewRoleSearch() {
      this.loadCookie()
      request.get('/document/getDocumentExamine', {
        params: this.documentExamineForm
      }).then(res => {
        if (res.code === '200') {
          this.documentExamineData = res.data.list
          this.userCount = res.data.total
        }
      })
    },
    pagerTest() {
      this.pagerCount = this.minCount > 5 ? this.minCount : 5
      this.pagerSmall = this.minCount <= 5
    },
    userPageChange(page) {
      this.documentExamineForm.pageNum = page
      this.viewRoleSearch()
    },
    loadCookie(){
      this.userCookie=Cookies.get('user') ? JSON.parse(Cookies.get('user')) : {}
      this.documentExamineForm.token=this.userCookie.token
    }
  },
  mounted() {
    this.pagerTest()
    this.viewRoleSearch()
  },
}
</script>

<style lang="less" scoped>
* {
  width: 100%;
  float: left;
}

.DocumentExamineView {
  height: 100%;

  /deep/ input {
    background-color: #ddded7;
  }

  .DocumentExamineViewBody {
    height: 90%;

    .DocumentExamineSearch {
      /deep/ * {
        height: 100%;
      }

      height: 15%;

      .DocumentExamineViewInput {
        height: 48%;
        margin-bottom: 1px;

        .userName {
          height: 100%;
          width: 100%;
        }

        .roleId {
          height: 100%;
          float: right;
          width: calc(35% - 2px);
        }

        .departmentId {
          height: 100%;
          float: right;
          width: calc(35% - 2px);
        }

        .userPhone {
          height: 100%;
          width: 65%;
        }

        .userUpdateTime {
          width: 70%;
          height: 100%;
        }

      }

      .userButton {
        width: 29%;
        height: 100%;
        float: right;
        font-size: 30%;
        margin-left: 1%;

        button {
          width: 49%;
          margin-left: 1%;
          background-color: #dcded2;
          border: 1px solid #dcded2;
        }
      }
    }

    .DocumentExamineDetailView {
      margin-top: 1%;
      height: 70%;
    }

    .document-page {
      margin-top: 2%;
      height: 8%;
    }
  }

}
</style>