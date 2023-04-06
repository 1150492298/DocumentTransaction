<template>
  <div class="DocumentClassView">
    <AppIntroHeader HeaderClass="公文类别添加"></AppIntroHeader>
    <div class="DocumentClassViewViewbody">
      <el-form :model="documentClassForm" :rules="rules" ref="documentClassForm" label-width="auto"
               class="demo-ruleForm mystify_form"
               size="mini"
               :hide-required-asterisk='hide_required' label-position="top">
        <el-form-item label="类别编号">
          <el-input v-model="documentClassForm.documentClassId" class="inputReadOnly" readonly="readonly"></el-input>
        </el-form-item>
        <el-form-item label="类别名称" prop="documentClassName">
          <el-input v-model="documentClassForm.documentClassName"></el-input>
        </el-form-item>
        <el-form-item label="类别描述" prop="documentClassDescribe">
          <el-input type="textarea" v-model="documentClassForm.documentClassDescribe" rows="4"></el-input>
        </el-form-item>
        <el-form-item label="公文流程">
        </el-form-item>
        <el-steps direction="vertical" :active="documentClassForm.documentProcessCount" finish-status="success"
                  class="DocumentClassStep">
          <el-step v-for="index of documentClassForm.documentProcessCount" :title="documentDepartmentString[index-1]"
                   :description="documentRoleString[index-1]" :key="index"></el-step>
        </el-steps>
      </el-form>
    </div>
  </div>
</template>

<script>
import AppIntroHeader from "@/components/AppIntroHeader";
import request from "@/utils/request";

export default {
  name: "DocumentClassView",
  components: {AppIntroHeader},
  data() {
    return {
      documentClassForm: {
        documentClassId:null,
        documentClassName: '',
        documentClassDescribe: '',
        documentDepartmentRoleString: null,
        documentProcessCount: 0,
      },
      documentDepartmentRoleString: [],
      departmentId: null,
      roleId: null,
      documentDepartmentString: [],
      documentRoleString: [],
      hide_required: true,
      rules: {
        documentClassName: [
          {required: true, message: '请输入公文类别名', trigger: 'blur'},
          {max: 10, message: '长度不得大于10', trigger: 'blur'}
        ],
        documentClassDescribe: [
          {required: true, message: '请填写公文类别描述', trigger: 'blur'},
          {min: 5, max: 100, message: '描述字数不得低于5超过100', trigger: 'change'}
        ]
      }
    }
  },
  methods: {
    async loadDocumentClass() {
      const documentClassId = parseInt(this.$route.query.documentClassId)
      await request.get("/documentClass/getDocumentClassById/" + documentClassId).then(res => {
        if (res.code == '200') {
          this.documentClassForm = res.data
          this.handleDocumentClass()
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
    handleDocumentClass() {
      this.documentDepartmentRoleString = this.documentClassForm.documentDepartmentRoleString.split('-')
      this.loadDepartment()
      this.loadRole()
      for (const i in this.documentDepartmentRoleString) {
        const j = this.documentDepartmentRoleString[i].split(':')
        this.documentDepartmentString.push(this.$store.getters.getDepartmentList.find(item => {
          return item.departmentId === parseInt(j[0])
        }).departmentName)
        this.documentRoleString.push(this.$store.getters.getRoleList.find(item => {
          return item.roleId === parseInt(j[1])
        }).roleName)
      }
    },
    async loadDepartment() {
      await this.$store.dispatch('getDepartmentList')
    },
    async loadRole() {
      await this.$store.dispatch('getRoleList')
    },
  },
  mounted() {
    this.loadDocumentClass()
  },
}
</script>

<style lang="less" scoped>
.DocumentClassView {
  width: 100%;
  height: 100%;
  float: left;

  /deep/ input {
    background-color: #ddded7;
  }

  /deep/ textarea {
    background-color: #ddded7;
    height: 50%;
  }

  .DocumentClassViewViewbody {
    height: 90%;

    .processEdit {
      height: 50px;

      .processInput {
        float: left;
        width: 43%;
        height: 50px;
        border: 1px solid #ddded7;

        /deep/ input {
          height: 50px;

        }
      }

      .processButton {
        margin: 0;
        padding: 0;
        float: left;
        text-align: center;
        width: 14%;
        height: 100%;

        /deep/ button {
          margin: 0;
          padding: 0;
          width: 90%;
          height: 49%;
          background-color: #dcded2;
          border: 1px solid #ddded7;
        }
      }
    }

    .DocumentClassStep {
      margin-bottom: 10%;
      border-top: 1px solid #d8dcbc;
      border-bottom: 1px solid #d8dcbc;
    }
  }
  /deep/ .inputReadOnly {


    input {
      background-color: #cdd2c0;
    }

    textarea {
      background-color: #cdd2c0;
    }
  }
}
</style>