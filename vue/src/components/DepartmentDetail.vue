<template>
  <div class="DepartmentDetail">
    <div class="DepartmentDetailHeader">
      <div class="DepartmentDetailName">
        部门名：{{DepartmentDetail.departmentName}}
      </div>
      <div class="DepartmentDetailButton">
        <div class="DepartmentDetailButtonChild">
          <el-button size="mini" type="info" @click="$router.push('/departmentEdit?departmentId='+DepartmentDetail.departmentId)" round>编辑</el-button>
        </div>
      </div>
    </div>
    <div class="DepartmentDetailFooter">
      <div class="DepartmentDetailDescribe">
        部门介绍：{{DepartmentDetail.departmentIntro}}
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: "DepartmentDetail",
  props: ["DepartmentDetail"],
  data() {
    return {}
  },
  methods:{
  }
}
</script>

<style lang="less" scoped>
.DepartmentDetail{
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
  .DepartmentDetailHeader{
    height: 30%;
    border-bottom: 1px solid #c4cbce;

    .DepartmentDetailName{
      width: 80%;
      height: 100%;
    }
    .DepartmentDetailButton{
      width: 20%;
      height: 100%;
      .DepartmentDetailButtonChild{
        height: 100%;
        button{
          line-height: 70%;
          height: 100%;
        }
      }
    }
  }
  .DepartmentDetailFooter{
    height: 70%;
    .DepartmentDetailDescribe{
      width: 100%;
      height: 100%;
    }
  }
}
</style>