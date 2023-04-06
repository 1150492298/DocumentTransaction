<template>
  <div class="MailListView">
    <AppIntroHeader HeaderClass="通讯库"></AppIntroHeader>
    <div class="MailListViewBody">
      <div class="MailListSearch">
        <div class="MailListViewInput">
          <el-input
              placeholder="用户名"
              suffix-icon="el-icon-s-check"
              class="userName"
              v-model="userSearchForm.userName"
          >
          </el-input>
          <el-select v-model="userSearchForm.roleId" placeholder="用户角色" class="roleId">
            <el-option
                v-for="item in $store.getters.getRoleList"
                :key="item.roleId"
                :label="item.roleName"
                :value="item.roleId">
            </el-option>
          </el-select>
        </div>
        <div class="MailListViewInput">
          <el-input
              placeholder="用户电话"
              suffix-icon="el-icon-s-management"
              class="userPhone"
              v-model="userSearchForm.userPhone">
          </el-input>
          <el-select v-model="userSearchForm.departmentId" placeholder="用户部门" class="departmentId">
            <el-option
                v-for="item in $store.getters.getDepartmentList"
                :key="item.departmentId"
                :label="item.departmentName"
                :value="item.departmentId">
            </el-option>
          </el-select>
        </div>
        <div class="MailListViewInput">
          <el-date-picker
              v-model="userSearchForm.updateTime"
              align="right"
              type="date"
              placeholder="更新日期"
              value-format="yyyy-MM-dd"
              class="userUpdateTime"
          >
          </el-date-picker>
          <div class="userButton">
            <button class="el-icon-plus" @click="addMailList"></button>
            <button class="el-icon-refresh" @click="resetSearch"></button>
          </div>
        </div>
      </div>
      <div class="MailListDetail">
        <UserMailAbout v-for="(UserAbout,index) in filterMailListData" :key="index" :UserAbout="UserAbout"/>
      </div>
    </div>
  </div>
</template>

<script>

import request from "@/utils/request";
import UserAbout from "@/components/UserAbout";
import Cookies from "js-cookie";
import UserSearchAbout from "@/components/UserSearchAbout";
import UserMailAbout from "@/components/UserMailAbout";

export default {
  name: "MailListView",
  components: {UserMailAbout},
  data() {
    return {
      userSearchForm: {
        userName: '',
        updateTime: '',
        userPhone: '',
        departmentId: '',
        roleId: '',
      },
      MailListData: [],
      userCookie: Cookies.get('user') ? JSON.parse(Cookies.get('user')) : {},
      minCount: parseInt(document.body.clientWidth / 100),
      pagerSmall: true,
    }
  },
  methods: {
    resetSearch() {
      this.userSearchForm = {
        userName: '',
        updateTime: '',
        userPhone: '',
        departmentId: '',
        roleId: '',
      }
      this.viewMailList()
    },
    viewMailList() {
      request.get('user/getUserMailList/' + this.userCookie.token).then(res => {
        if (res.code === '200') {
          this.MailListData = res.data
          console.log(this.MailListData)
        }
      })
    },
    addMailList() {
      this.$router.push("/usersearch")
    },
    pagerTest() {
      this.pagerCount = this.minCount > 5 ? this.minCount : 5
      this.pagerSmall = this.minCount <= 5
    },
  },
  mounted() {
    this.pagerTest()
    this.viewMailList()
  },
  computed: {
    filterMailListData() {
      return this.MailListData.filter(item => {
        let flag1=false,flag2=false,flag3=false,flag4=false,flag5=false
        if (this.userSearchForm.roleId === '' || (item.roleId === this.userSearchForm.roleId)) {
          flag1 = true
        }
        if (this.userSearchForm.departmentId === '' ||item.departmentId === this.userSearchForm.departmentId) {
          flag2= true
        }
        if (this.userSearchForm.userPhone=== '' || item.userPhone.match(this.userSearchForm.userPhone)) {
          flag3 = true
        }
        if (this.userSearchForm.userName=== '' || item.userName.match(this.userSearchForm.userName)) {
          flag4 = true
        }
        if (this.userSearchForm.updateTime === '' || item.updateTime.match(this.userSearchForm.updateTime)) {
          flag5 = true
        }
        return flag1&&flag2&&flag3&&flag4&&flag5
      })
    }
  }
}
</script>

<style lang="less" scoped>
* {
  width: 100%;
  float: left;
}

.MailListView {
  height: 100%;

  /deep/ input {
    background-color: #ddded7;
  }

  .MailListViewBody {
    height: 90%;

    .MailListSearch {
      /deep/ * {
        height: 100%;
      }

      height: 23%;
      border-bottom: 1px solid #cdc4c4;

      .MailListViewInput {
        height: 32%;
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

    .MailListDetail {
      margin-top: 1%;
      height: 63%;
    }

    .document-page {
      margin-top: 2%;
      height: 8%;
    }
  }

}
</style>