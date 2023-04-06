<template>
  <div class="RoleDetail">
    <div class="RoleDetailHeader">
      <div class="RoleDetailId">
        Id：{{RoleDetail.roleId}}
      </div>
      <div class="RoleDetailWeight">
        权限等级：{{RoleDetail.roleWeight}}
      </div>
      <div class="RoleDetailName">
        角色名：{{RoleDetail.roleName}}
      </div>
    </div>
    <div class="RoleDetailFooter">
      <div class="RoleDetailDescribe">
        权限介绍：{{RoleDetail.roleDescribe}}
      </div>
      <div class="RoleDetailButton">
        <div class="RoleDetailButtonChild">
          <el-button size="mini" type="info" @click="$router.push('/roleEdit?roleId='+RoleDetail.roleId)" round>编辑</el-button>
        </div>
        <div>
          <el-popconfirm title="您确定要删除这行数据吗" @confirm="delRoleById(RoleDetail.roleId)" class="UserAboutButtonChild">
            <el-button class="RoleDetailButtonChild" size="mini" type="danger" slot="reference" round >删除</el-button>
          </el-popconfirm>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "RoleDetail",
  props: ["RoleDetail"],
  data() {
    return {}
  },
  methods:{
    delRoleById(roleId){
      request.delete('/role/delRoleById/'+parseInt(roleId)).then(res=>{
        if (res.code == '200') {
          this.$notify.success('删除角色成功')
          this.$parent.loadRole()
        } else {
          this.$notify.error(res.msg)
        }
      })
    }
  }
}
</script>

<style lang="less" scoped>
.RoleDetail{
  *{
    text-overflow: ellipsis ;
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
  border-top: 1px solid #cdc4c4;
  border-bottom: 1px solid #cdc4c4;
  .RoleDetailHeader{
    height: 30%;
    border-bottom: 1px solid #c4cbce;
    .RoleDetailId{
      width: 15%;
      height: 100%;
    }
    .RoleDetailName{
      width: 50%;
      height: 100%;
    }
    .RoleDetailWeight{
      text-align: center;
      width: 35%;
      height: 100%;
    }
  }
  .RoleDetailFooter{
    height: 70%;
    .RoleDetailDescribe{
      width: 80%;
      height: 100%;
    }
    .RoleDetailButton{
      width: 20%;
      height: 100%;
      .RoleDetailButtonChild{
        height: 49%;
        margin-top: 1%;
        button{
          line-height: 70%;
          height: 100%;
        }
      }
    }
  }
}
</style>