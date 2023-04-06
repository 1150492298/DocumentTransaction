<template>
  <div class="DepartmentApplyDetail">
    <el-drawer
        title="申请理由"
        :visible.sync="drawer"
        direction="btt"
        size="50%"
        >
      <el-input type="textarea" v-model="DepartmentApplyDetail.searchReason" rows="8" readonly="readonly"></el-input>
    </el-drawer>
    <div class="DepartmentApplyDetailHeader">
      <div class="DepartmentApplyDetailId" @click="$router.push('/userabout?userId='+DepartmentApplyDetail.userId)">
        <div class="DepartmentApplyDetailIdName">用户名:{{ DepartmentApplyDetail.userName }}</div>
        <div class="el-icon-zoom-in DepartmentApplyDetailIdIcon"></div>
      </div>
      <div class="DepartmentApplyDetailRole"
           @click="$router.push('/departmentView?departmentId='+DepartmentApplyDetail.departmentId)">
        <div class="DepartmentApplyDetailRoleName">申请部门:{{ DepartmentApplyDetail.departmentName }}</div>
        <div class="el-icon-zoom-in DepartmentApplyDetailIdIcon"></div>
      </div>
    </div>
    <div class="DepartmentApplyDetailBody">
      <div class="DepartmentApplyDetailName" @click="drawer = true">
        申请理由:{{ DepartmentApplyDetail.searchReason }}
      </div>
    </div>
    <div class="DepartmentApplyDetailFooter">
      <div class="DepartmentApplyDetailUpdate">
        申请时间:{{ DepartmentApplyDetail.searchTime }}
      </div>
      <div class="DepartmentApplyDetailButton">
        <el-popconfirm title="您确定要批准通过吗" @confirm="DepartmentApplyComplete"
                       class="DepartmentApplyDetailButtonChild">
          <el-button size="mini" type="info" slot="reference" round>批准</el-button>
        </el-popconfirm>
        <el-popconfirm title="您确定要删除这行数据吗" @confirm="DepartmentApplyReject"
                       class="DepartmentApplyDetailButtonChild">
          <el-button size="mini" type="danger" slot="reference" round>拒绝</el-button>
        </el-popconfirm>
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "DepartmentApplyDetail",
  props: ["DepartmentApplyDetail"],
  data() {
    return {
      drawer:false
    }
  },
  methods: {
    DepartmentApplyComplete() {
      request.put('/departmentSearch/departmentSearchManage', {
        departmentSearch: this.DepartmentApplyDetail,
        token: this.$parent.departmentApplyManageForm.token
      }).then(res => {
        if (res.code == '200') {
          this.$notify.success('角色申请已处理!')
          this.$parent.viewDepartmentApply()
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
    DepartmentApplyReject() {
      request.put('/departmentSearch/departmentSearchReject', {
        departmentSearch: this.DepartmentApplyDetail,
        token: this.$parent.departmentApplyManageForm.token
      }).then(res => {
        if (res.code == '200') {
          this.$notify.success('已拒绝该用户申请!')
          this.$parent.viewDepartmentApply()
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
    alertDepartmentApplyDetail() {
      alert("申请理由"+this.DepartmentApplyDetail.searchReason)
    }
  }
}
</script>

<style lang="less" scoped>
.DepartmentApplyDetail {
  * {
    text-overflow: ellipsis;
    float: left;
    width: 100%;
    overflow: hidden;
    line-height: 100%;
    border-right: 1px solid #c6c2b7;
  }

  display: inline-block;
  text-align: left;
  height: 20%;
  font-size: 80%;
  border-bottom: 1px solid #cdc4c4;


  .DepartmentApplyDetailHeader {
    height: 30%;
    border-top: 1px solid #c4cbce;
    border-bottom: 1px solid #cdc4c4;

    .DepartmentApplyDetailId {
      width: 50%;
      height: 100%;

      .DepartmentApplyDetailIdName {
        width: 90%;
        text-overflow: ellipsis;
        border-right: none;
        white-space: nowrap;
      }

      .DepartmentApplyDetailIdIcon {
        width: 10%;
      }
    }

    .DepartmentApplyDetailRole {
      width: 50%;
      height: 100%;

      .DepartmentApplyDetailRoleName {
        width: 90%;
        text-overflow: ellipsis;
        white-space: nowrap;
        border-right: none;
      }

      .DepartmentApplyDetailIdIcon {
        width: 10%;
      }
    }

  }

  .DepartmentApplyDetailBody {
    height: 30%;
    border-bottom: 1px solid #c4cbce;

    .DepartmentApplyDetailName {
      white-space: nowrap;
      width: 100%;
      height: 100%;
    }

  }

  .DepartmentApplyDetailFooter {
    height: 40%;

    .DepartmentApplyDetailUpdate {
      width: 60%;
      height: 100%;
    }

    .DepartmentApplyDetailButton {
      width: 40%;
      height: 100%;

      .DepartmentApplyDetailButtonChild {
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