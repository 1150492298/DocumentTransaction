<template>
  <div class="DocumentDetail">
    <div class="DocumentDetailHeader">
      <el-drawer
          title="标题内容"
          :visible.sync="drawer"
          direction="btt"
          size="50%"
      >
        <el-input type="textarea" v-model="DocumentDetail.documentName" rows="8" readonly="readonly"></el-input>
      </el-drawer>
      <div class="DocumentDetailId" @click="$router.push('/userabout?userId='+DocumentDetail.userId)">
        <div class="DocumentDetailIdName">用户名:{{ DocumentDetail.userName }}</div>
        <div class="el-icon-zoom-in DocumentDetailIdIcon"></div>
      </div>
      <div class="DocumentDetailRole" @click="$router.push('/documentClassView?documentClassId='+DocumentDetail.documentClassId)">
        <div class="DocumentDetailRoleName">
          公文类别:
          {{ DocumentDetail.documentClassName }}
          <!--          {{-->
          <!--            $store.getters.getRoleList.find((item) => {-->
          <!--              return item.roleId === DocumentDetail.roleId-->
          <!--            }).roleName-->
          <!--          }}-->
        </div>
        <div class="el-icon-zoom-in DocumentDetailIdIcon"></div>
      </div>
    </div>
    <div class="DocumentDetailBody">
      <div class="DocumentDetailName" @click="drawer = true">
        公文名:{{ DocumentDetail.documentName }}
      </div>
    </div>
    <div class="DocumentDetailFooter">
      <div class="DocumentDetailUpdate">
        发布时间:{{ DocumentDetail.documentPostTime }}
      </div>
      <div class="DocumentDetailButton">
        <el-button size="mini" type="info" slot="reference" round @click="$router.push('/documentPassView?documentId='+DocumentDetail.documentId)">详情</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "DocumentDetail",
  props: ["DocumentDetail"],
  data() {
    return {
      drawer: false
    }
  },
  methods: {
  }
}
</script>

<style lang="less" scoped>
.DocumentDetail {
  text-align: left;
  height: 20%;
  font-size: 80%;
  border-bottom: 1px solid #cdc4c4;

  * {
    text-overflow: ellipsis;
    float: left;
    width: 100%;
    overflow: hidden;
    white-space: nowrap;
    line-height: 100%;
    border-right: 1px solid #c6c2b7;
  }

  .DocumentDetailHeader {
    height: 30%;
    border-top: 1px solid #c4cbce;
    border-bottom: 1px solid #cdc4c4;

    .DocumentDetailId {
      width: 50%;
      height: 100%;

      .DocumentDetailIdName {
        width: 90%;
        text-overflow: ellipsis;
        border-right: none;
      }

      .DocumentDetailIdIcon {
        width: 10%;
      }
    }

    .DocumentDetailRole {
      width: 50%;
      height: 100%;

      .DocumentDetailRoleName {
        width: 90%;
        text-overflow: ellipsis;
        border-right: none;
      }

      .DocumentDetailIdIcon {
        width: 10%;
      }
    }

  }

  .DocumentDetailBody {
    height: 30%;
    border-bottom: 1px solid #c4cbce;

    .DocumentDetailName {
      width: 100%;
      height: 100%;
    }

  }

  .DocumentDetailFooter {
    height: 40%;

    .DocumentDetailUpdate {
      width: 60%;
      height: 100%;
    }

    .DocumentDetailButton {
      width: 40%;
      height: 100%;

      .DocumentDetailButtonChild {
        line-height: 50%;
        margin-top: 2px;
        height: 80%;
        width: 48%;
        margin-left: 2%;

        button {
          height: 100%;
          line-height: 45%;
        }
      }
    }
  }
}
</style>