<template>
  <div class="UserAbout">
    <div class="UserAboutHeader">
      <div class="UserAboutId">
        用户id:{{ UserAbout.userId }}
      </div>
      <div class="UserAboutDepartment">
        部门:
        {{ UserAbout.departmentName}}
      </div>
      <div class="UserAboutRole">
        角色:
        {{ UserAbout.roleName}}
      </div>
    </div>
    <div class="UserAboutBody">
      <div class="UserAboutName">
        用户名:{{ UserAbout.userName }}
      </div>
      <div class="UserAboutPhone">
        电话:{{ UserAbout.userPhone }}
      </div>
      <div class="UserAboutSex">
        性别:{{ UserAbout.userSex == 1 ? '男' : '女' }}
      </div>
    </div>
    <div class="UserAboutFooter">
      <div class="UserAboutUpdate">
        更新时间:{{ UserAbout.updateTime }}
      </div>
      <div class="UserAboutButton">
        <el-button size="mini" type="info" class="UserAboutButtonChild"
                   @click="$router.push('/userEdit?userId='+UserAbout.userId)" round>修改
        </el-button>
        <el-popconfirm title="您确定要删除这行数据吗" @confirm="delUserById(UserAbout.userId)" class="UserAboutButtonChild">
          <el-button size="mini" type="danger" slot="reference" round>删除</el-button>
        </el-popconfirm>
      </div>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "UserAbout",
  props: ["UserAbout"],
  data() {
    return {
    }
  },
  methods: {
    delUserById(userId) {
      request.delete('/user/delUserById/' + parseInt(userId)).then(res => {
        if (res.code == '200') {
          this.$notify.success('删除用户信息成功')
          this.$parent.viewUser()
        } else {
          this.$notify.error(res.msg)
        }
      })
    }
  }
}
</script>

<style lang="less" scoped>
.UserAbout {
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

    .UserAboutId {
      width: 30%;
      height: 100%;
    }

    .UserAboutRole {
      width: 35%;
      height: 100%;
    }

    .UserAboutDepartment {
      text-align: center;
      width: 35%;
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