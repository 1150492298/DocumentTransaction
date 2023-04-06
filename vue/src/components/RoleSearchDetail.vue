<template>
  <div class="RoleSearchDetail">
    <div class="RoleSearchDetailHeader">
      <el-drawer
          title="申请理由"
          :visible.sync="drawer"
          direction="btt"
          size="50%"
      >
        <el-input type="textarea" v-model="RoleSearchDetail.searchReason" rows="8" readonly="readonly"></el-input>
      </el-drawer>
      <div class="RoleSearchDetailId" @click="$router.push('/userabout?userId='+RoleSearchDetail.userId)">
        <div class="RoleSearchDetailIdName">用户名:{{ RoleSearchDetail.userName }}</div>
        <div class="el-icon-zoom-in RoleSearchDetailIdIcon"></div>
      </div>
      <div class="RoleSearchDetailRole" @click="$router.push('/roleView?roleId='+RoleSearchDetail.roleId)">
        <div class="RoleSearchDetailRoleName">
          申请角色:
          {{
            $store.getters.getRoleList.find((item) => {
              return item.roleId === RoleSearchDetail.roleId
            }).roleName
          }}
        </div>
        <div class="el-icon-zoom-in RoleSearchDetailIdIcon"></div>
      </div>
    </div>
    <div class="RoleSearchDetailBody">
      <div class="RoleSearchDetailName" @click="drawer = true">
        申请理由:{{ RoleSearchDetail.searchReason }}
      </div>
    </div>
    <div class="RoleSearchDetailFooter">
      <div class="RoleSearchDetailUpdate">
        申请时间:{{ RoleSearchDetail.searchTime }}
      </div>
      <div class="RoleSearchDetailButton">
        <el-popconfirm title="您确定要批准吗" @confirm="RoleSearchComplete"
                       class="RoleSearchDetailButtonChild">
          <el-button size="mini" type="info" slot="reference" round>批准</el-button>
        </el-popconfirm>
        <el-popconfirm title="您确定要拒绝吗" @confirm="roleSearchReject"
                       class="RoleSearchDetailButtonChild">
          <el-button size="mini" type="danger" slot="reference" round>拒绝</el-button>
        </el-popconfirm>
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "RoleSearchDetail",
  props: ["RoleSearchDetail"],
  data() {
    return {
      drawer:false
    }
  },
  methods: {
    RoleSearchComplete() {
      request.put('/roleSearch/roleSearchManage', {
        roleSearch:this.RoleSearchDetail,
        token: this.$parent.roleSearchManageForm.token
      }).then(res => {
        if (res.code == '200') {
          this.$notify.success('角色申请已处理!')
          this.$parent.viewRoleSearch()
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
    roleSearchReject() {
      request.put('/roleSearch/roleSearchReject', {
        roleSearch:this.RoleSearchDetail,
        token: this.$parent.roleSearchManageForm.token
      }).then(res => {
        if (res.code == '200') {
          this.$notify.success('已拒绝该用户申请!')
          this.$parent.viewRoleSearch()
        } else {
          this.$notify.error(res.msg)
        }
      })
    }
  }
}
</script>

<style lang="less" scoped>
.RoleSearchDetail {
  * {
    text-overflow: ellipsis;
    float: left;
    width: 100%;
    overflow: hidden;
    white-space: nowrap;
    line-height: 100%;
    border-right: 1px solid #c6c2b7;
  }

  display: inline-block;
  text-align: left;
  height: 20%;
  font-size: 80%;
  border-bottom: 1px solid #cdc4c4;

  .RoleSearchDetailHeader {
    height: 30%;
    border-top: 1px solid #c4cbce;
    border-bottom: 1px solid #cdc4c4;

    .RoleSearchDetailId {
      width: 50%;
      height: 100%;

      .RoleSearchDetailIdName {
        width: 90%;
        text-overflow: ellipsis;
        border-right: none;
      }

      .RoleSearchDetailIdIcon {
        width: 10%;
      }
    }

    .RoleSearchDetailRole {
      width: 50%;
      height: 100%;
      .RoleSearchDetailRoleName {
        width: 90%;
        text-overflow: ellipsis;
        border-right: none;
      }

      .RoleSearchDetailIdIcon {
        width: 10%;
      }
    }

  }

  .RoleSearchDetailBody {
    height: 30%;
    border-bottom: 1px solid #c4cbce;

    .RoleSearchDetailName {
      width: 100%;
      height: 100%;
    }

  }

  .RoleSearchDetailFooter {
    height: 40%;

    .RoleSearchDetailUpdate {
      width: 60%;
      height: 100%;
    }

    .RoleSearchDetailButton {
      width: 40%;
      height: 100%;

      .RoleSearchDetailButtonChild {
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