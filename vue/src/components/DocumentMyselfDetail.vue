<template>
  <div class="DocumentMyselfDetail">
    <div class="DocumentMyselfDetailHeader">
      <el-drawer
          title="标题内容"
          :visible.sync="drawer"
          direction="btt"
          size="50%"
      >
        <el-input type="textarea" v-model="DocumentMyselfDetail.documentName" rows="8" readonly="readonly"></el-input>
      </el-drawer>
      <div class="DocumentMyselfDetailId">
        <div class="DocumentMyselfDetailIdName">公文状态:{{ documentStatue.find((item)=>{return item.value===DocumentMyselfDetail.documentStatue}).label }}</div>
        <div class="el-icon-zoom-in DocumentMyselfDetailIdIcon"></div>
      </div>
      <div class="DocumentMyselfDetailRole" @click="$router.push('/documentClassView?documentClassId='+DocumentMyselfDetail.documentClassId)">
        <div class="DocumentMyselfDetailRoleName">
          公文类别:
          {{ DocumentMyselfDetail.documentClassName }}
          <!--          {{-->
          <!--            $store.getters.getRoleList.find((item) => {-->
          <!--              return item.roleId === DocumentMyselfDetail.roleId-->
          <!--            }).roleName-->
          <!--          }}-->
        </div>
        <div class="el-icon-zoom-in DocumentMyselfDetailIdIcon"></div>
      </div>
    </div>
    <div class="DocumentMyselfDetailBody">
      <div class="DocumentMyselfDetailName" @click="drawer = true">
        公文标题:{{ DocumentMyselfDetail.documentName }}
      </div>
    </div>
    <div class="DocumentMyselfDetailFooter">
      <div class="DocumentMyselfDetailUpdate">
        发布时间:{{ DocumentMyselfDetail.documentPostTime }}
      </div>
      <div class="DocumentMyselfDetailButton">
        <el-button size="mini" type="info" slot="reference" round @click="$router.push('/documentPassView?documentId='+DocumentMyselfDetail.documentId)">详情</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "DocumentMyselfDetail",
  props: ["DocumentMyselfDetail"],
  data() {
    return {
      drawer: false,
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
    }
  },
  methods: {
  }
}
</script>

<style lang="less" scoped>
.DocumentMyselfDetail {
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

  .DocumentMyselfDetailHeader {
    height: 30%;
    border-top: 1px solid #c4cbce;
    border-bottom: 1px solid #cdc4c4;

    .DocumentMyselfDetailId {
      width: 50%;
      height: 100%;

      .DocumentMyselfDetailIdName {
        width: 90%;
        text-overflow: ellipsis;
        border-right: none;
      }

      .DocumentMyselfDetailIdIcon {
        width: 10%;
      }
    }

    .DocumentMyselfDetailRole {
      width: 50%;
      height: 100%;

      .DocumentMyselfDetailRoleName {
        width: 90%;
        text-overflow: ellipsis;
        border-right: none;
      }

      .DocumentMyselfDetailIdIcon {
        width: 10%;
      }
    }

  }

  .DocumentMyselfDetailBody {
    height: 30%;
    border-bottom: 1px solid #c4cbce;

    .DocumentMyselfDetailName {
      width: 100%;
      height: 100%;
    }

  }

  .DocumentMyselfDetailFooter {
    height: 40%;

    .DocumentMyselfDetailUpdate {
      width: 60%;
      height: 100%;
    }

    .DocumentMyselfDetailButton {
      width: 40%;
      height: 100%;

      .DocumentMyselfDetailButtonChild {
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