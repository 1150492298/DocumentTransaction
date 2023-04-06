<template>
  <div class="UserSearchAbout">
    <div class="UserAboutBody">
      <div class="UserAboutName" @click="$router.push('/userabout?userId='+UserAbout.userId)">
        用户名:{{ UserAbout.userName }}
      </div>
      <div class="UserAboutPhone">
        电话:{{ UserAbout.userPhone }}
      </div>
      <div class="UserAboutSex">
        性别:{{ UserAbout.userSex == 1 ? '男' : '女' }}
      </div>
    </div>
    <div class="UserAboutHeader">
      <div class="UserAboutDepartment" @click="$router.push('/departmentView?departmentId='+UserAbout.departmentId)">
        部门:
        {{ UserAbout.departmentName}}
      </div>
      <div class="UserAboutRole" @click="$router.push('/roleView?roleId='+UserAbout.roleId)">
        角色:
        {{ UserAbout.roleName}}
      </div>
    </div>
    <div class="UserAboutFooter">
      <div class="UserAboutUpdate">
        更新时间:{{ UserAbout.updateTime }}
      </div>
      <div class="UserAboutButton">
        <el-popconfirm title="确定要添加到通讯录？" @confirm="addAddressList" class="UserAboutButtonChild">
          <el-button size="mini" type="info" slot="reference" round>添加到通讯录
          </el-button>
        </el-popconfirm>
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
import Cookies from "js-cookie";

export default {
  name: "UserSearchAbout",
  props: ["UserAbout"],
  data() {
    return {
      userCookie: Cookies.get('user') ? JSON.parse(Cookies.get('user')) : {},
    }
  },
  methods: {
    addAddressList(){
      request.put('/user/addUserMailList', {
        addId:this.UserAbout.userId,
        token:this.userCookie.token
      }).then(res => {
        if (res.code == '200') {
          this.$notify.success('通讯录已成功添加')
        } else {
          this.$notify.error(res.msg)
        }
      })
    }
  }
}
</script>

<style lang="less" scoped>
.UserSearchAbout {
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

  .UserAboutHeader {
    height: 30%;
    border-bottom: 1px solid #c4cbce;


    .UserAboutRole {
      width: 50%;
      height: 100%;
    }

    .UserAboutDepartment {
      width: 50%;
      height: 100%;
    }
  }

  .UserAboutBody {
    height: 30%;
    border-bottom: 1px solid #c4cbce;

    .UserAboutName {
      width: 40%;
      height: 100%;
    }

    .UserAboutSex {
      width: 20%;
      height: 100%;
    }

    .UserAboutPhone {
      width: 40%;
      height: 100%;
    }
  }

  .UserAboutFooter {
    height: 40%;

    .UserAboutUpdate {
      width: 60%;
      height: 100%;
    }

    .UserAboutButton {
      width: 40%;
      height: 100%;

      .UserAboutButtonChild {
        line-height: 50%;
        margin-top: 2px;
        height: 80%;
        width: 100%;

        button {
          height: 100%;
          line-height: 45%;
        }
      }
    }
  }
}
</style>