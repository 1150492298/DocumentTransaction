<template>
  <div class="DocumentExamineDetail">
    <div class="DocumentExamineDetailHeader">
      <el-drawer
          title="标题内容"
          :visible.sync="drawer"
          direction="btt"
          size="50%"
      >
        <el-input type="textarea" v-model="DocumentExamineDetail.documentName" rows="8" readonly="readonly"></el-input>
      </el-drawer>
      <div class="DocumentExamineDetailId" @click="$router.push('/userabout?userId='+DocumentExamineDetail.userId)">
        <div class="DocumentExamineDetailIdName">用户名:{{ DocumentExamineDetail.userName }}</div>
        <div class="el-icon-zoom-in DocumentExamineDetailIdIcon"></div>
      </div>
      <div class="DocumentExamineDetailRole" @click="$router.push('/documentClassView?documentClassId='+DocumentExamineDetail.documentClassId)">
        <div class="DocumentExamineDetailRoleName">
          公文类别:
          {{ DocumentExamineDetail.documentClassName }}
          <!--          {{-->
          <!--            $store.getters.getRoleList.find((item) => {-->
          <!--              return item.roleId === DocumentExamineDetail.roleId-->
          <!--            }).roleName-->
          <!--          }}-->
        </div>
        <div class="el-icon-zoom-in DocumentExamineDetailIdIcon"></div>
      </div>
    </div>
    <div class="DocumentExamineDetailBody">
      <div class="DocumentExamineDetailName" @click="drawer = true">
        公文标题:{{ DocumentExamineDetail.documentName }}
      </div>
    </div>
    <div class="DocumentExamineDetailFooter">
      <div class="DocumentExamineDetailUpdate">
        发布时间:{{ DocumentExamineDetail.documentPostTime }}
      </div>
      <div class="DocumentExamineDetailButton">
        <el-button size="mini" type="info" slot="reference" round @click="$router.push('/documentExamineView?documentId='+DocumentExamineDetail.documentId)">详情</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "DocumentExamineDetail",
  props: ["DocumentExamineDetail"],
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
.DocumentExamineDetail {
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

  .DocumentExamineDetailHeader {
    height: 30%;
    border-top: 1px solid #c4cbce;
    border-bottom: 1px solid #cdc4c4;

    .DocumentExamineDetailId {
      width: 50%;
      height: 100%;

      .DocumentExamineDetailIdName {
        width: 90%;
        text-overflow: ellipsis;
        border-right: none;
      }

      .DocumentExamineDetailIdIcon {
        width: 10%;
      }
    }

    .DocumentExamineDetailRole {
      width: 50%;
      height: 100%;

      .DocumentExamineDetailRoleName {
        width: 90%;
        text-overflow: ellipsis;
        border-right: none;
      }

      .DocumentExamineDetailIdIcon {
        width: 10%;
      }
    }

  }

  .DocumentExamineDetailBody {
    height: 30%;
    border-bottom: 1px solid #c4cbce;

    .DocumentExamineDetailName {
      width: 100%;
      height: 100%;
    }

  }

  .DocumentExamineDetailFooter {
    height: 40%;

    .DocumentExamineDetailUpdate {
      width: 60%;
      height: 100%;
    }

    .DocumentExamineDetailButton {
      width: 40%;
      height: 100%;

      .DocumentExamineDetailButtonChild {
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