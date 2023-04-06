<template>
  <div class="DocumentMyselfView">
    <AppIntroHeader HeaderClass="我的公文"></AppIntroHeader>
    <div class="UserManageViewBody">
      <div class="UserManageSearch">
        <div class="UserManageViewInput">
          <el-input
              placeholder="公文名"
              suffix-icon="el-icon-s-check"
              class="userName"
              v-model="documentSearchForm.documentName"
          >
          </el-input>
          <el-select v-model="documentSearchForm.documentStatue" placeholder="公文状态" class="roleId">
            <el-option
                v-for="item in documentStatue"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </div>
        <div class="UserManageViewInput">
          <el-date-picker
              v-model="documentSearchForm.documentPostTime"
              align="right"
              type="date"
              placeholder="发布日期"
              value-format="yyyy-MM-dd"
              class="userUpdateTime"
          >
          </el-date-picker>
          <el-select v-model="documentSearchForm.documentClassId" placeholder="公文类别" class="userUpdateTime">
            <el-option
                v-for="item in $store.getters.getDocumentClassList"
                :key="item.documentClassId"
                :label="item.documentClassName"
                :value="item.documentClassId"
            >
            </el-option>
          </el-select>
          <div class="userButton">
            <button class="el-icon-search" @click="viewUser"></button>
            <button class="el-icon-refresh" @click="resetSearch"></button>
          </div>
        </div>
      </div>
      <div class="UserManageDetail">
        <DocumentMyselfDetail v-for="(DocumentMyselfDetail,index) in documentData" :key="index" :DocumentMyselfDetail="DocumentMyselfDetail"/>
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
import UserAbout from "@/components/UserAbout";
import Cookies from "js-cookie";
import DocumentMyselfDetail from "@/components/DocumentMyselfDetail";

export default {
  name: "DocumentMyselfView",
  components: {DocumentMyselfDetail, UserAbout},
  data() {
    return {
      userCookie: null,
      documentSearchForm: {
        documentStatue: '',
        documentName: '',
        documentPostTime: '',
        documentClassId: '',
        pageNum: 1,
        token: null
      },
      documentStatue: [
        {
          value: 1,
          label: '审核中'
        }, {
          value: 2,
          label: '已通过'
        }, {
          value: 3,
          label: '被驳回'
        }
      ],
      documentData: [],
      minCount: parseInt(document.body.clientWidth / 100),
      pagerSmall: true,
      pagerCount: 5,
      userCount: 0,
    }
  },
  methods: {
    resetSearch() {
      this.documentSearchForm = {
        documentStatue: '',
        documentName: '',
        documentPostTime: '',
        documentClassId: '',
        pageNum: 1,
        token: null
      }
      this.viewUser()
    },
    viewUser() {
      this.loadCookie()
      request.get('document/getDocumentByUserToken', {
        params: this.documentSearchForm
      }).then(res => {
        if (res.code === '200') {
          this.documentData = res.data.list
          this.userCount = res.data.total
        }
      })
    },
    pagerTest() {
      this.pagerCount = this.minCount > 5 ? this.minCount : 5
      this.pagerSmall = this.minCount <= 5
    },
    userPageChange(page) {
      this.documentSearchForm.pageNum = page
      this.viewUser()
    },
    loadCookie() {
      this.userCookie = Cookies.get('user') ? JSON.parse(Cookies.get('user')) : {}
      this.documentSearchForm.token = this.userCookie.token
    }
  },
  mounted() {
    this.pagerTest()
    this.viewUser()
  },
}
</script>

<style lang="less" scoped>
* {
  width: 100%;
  float: left;
}

.DocumentMyselfView {
  height: 100%;

  /deep/ input {
    background-color: #ddded7;
  }

  .UserManageViewBody {
    height: 90%;

    .UserManageSearch {
      /deep/ * {
        height: 100%;
      }

      height: 15%;
      border-bottom: 1px solid #cdc4c4;

      .UserManageViewInput {
        height: 48%;
        margin-bottom: 1px;

        .userName {
          height: 100%;
          width: 65%;
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
          width: 35%;
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

    .UserManageDetail {
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