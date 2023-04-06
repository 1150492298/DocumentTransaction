<template>
  <div class="DocumentManageDetail">
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
      <div class="DocumentStatue">
        状态:{{ documentStatue.find((item)=>{return item.value===DocumentDetail.documentStatue}).label }}
      </div>
    </div>
    <div class="DocumentDetailFooter">
      <div class="DocumentDetailUpdate">
        发布时间:{{ DocumentDetail.documentPostTime }}
      </div>
      <div class="DocumentDetailButton">
        <div class="DocumentDetailButtonChild">
          <el-button size="mini" type="info" slot="reference" round @click="$router.push('/documentPassView?documentId='+DocumentDetail.documentId)">详情</el-button>
        </div>
        <el-popconfirm title="您确定要删除该公文吗" @confirm="deleteDocumentById"
                       class="DocumentDetailButtonChild">
          <el-button size="mini" type="danger" slot="reference" round>删除</el-button>
        </el-popconfirm>
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "DocumentManageDetail",
  props: ["DocumentDetail"],
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
    deleteDocumentById(){
      request.delete('/document/delDocumentById/' + parseInt(this.DocumentDetail.documentId)).then(res => {
        if (res.code == '200') {
          this.$notify.success('删除公文成功!')
          this.$parent.loadDocument()
        } else {
          this.$notify.error(res.msg)
        }
      })
    }
  }
}
</script>

<style lang="less" scoped>
.DocumentManageDetail {
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
      width: 70%;
      height: 100%;
    }
    .DocumentStatue{
      width: 29%;
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

      .DocumentDetailButtonChild{
        height: 100%;
        width: 49%;
        margin-left: 1%;
        button{
          line-height: 70%;
          height: 100%;
        }
      }
    }
  }
}
</style>