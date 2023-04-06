<template>
  <div class="DocumentClassEdit">
    <AppIntroHeader HeaderClass="公文类别添加"></AppIntroHeader>
    <div class="DocumentClassEditViewbody">
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
          <div class="processEdit">
            <el-select v-model="departmentId" placeholder="请选择部门" class="processInput">
              <el-option
                  v-for="item in $store.getters.getDepartmentList"
                  :key="item.departmentId"
                  :label="item.departmentName"
                  :value="item.departmentId"
              >
              </el-option>
            </el-select>
            <el-select v-model="roleId" placeholder="请选择角色" class="processInput">
              <el-option
                  v-for="item in $store.getters.getRoleList"
                  :key="item.roleId"
                  :label="item.roleName"
                  :value="item.roleId"
              >
              </el-option>
            </el-select>
            <div class="processButton">
              <button class="el-icon-plus" @click="addDocumentClass" type="button"></button>
              <button class="el-icon-minus" @click="deleteDocumentClass" type="button"></button>
            </div>
          </div>
        </el-form-item>
        <el-steps direction="vertical" :active="documentClassForm.documentProcessCount" finish-status="success"
                  class="DocumentClassStep">
          <el-step v-for="index of documentClassForm.documentProcessCount" :title="documentDepartmentString[index-1]"
                   :description="documentRoleString[index-1]" :key="index"></el-step>
        </el-steps>
        <el-form-item>
          <el-button type="primary" @click="updateDocumentClass('documentClassForm')">更新</el-button>
          <el-button @click="resetDocumentClass()">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import AppIntroHeader from "@/components/AppIntroHeader";
import request from "@/utils/request";

export default {
  name: "DocumentClassEdit",
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
  computed: {},
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
    updateDocumentClass(documentClassForm) {
      this.$refs[documentClassForm].validate((valid) => {
        if (valid) {
          if(this.documentDepartmentRoleString.length===0){
            this.$notify.error("公文流程不得为空")
          }else{
            this.documentClassForm.documentDepartmentRoleString=this.documentDepartmentRoleString.join('-')
            request.put('/documentClass/updateDocumentClassById', this.documentClassForm).then(res => {
              if (res.code == '200') {
                this.$notify.success('更新公文流程成功')
                this.$router.back()
              } else {
                this.$notify.error(res.msg)
              }
            })
          }
        }
      })
    },
    addDocumentClass() {
      if (this.documentClassForm.documentProcessCount === 10) {
        this.$notify.error("流程长度不得大于10")
        return
      }
      if (this.departmentId == null || this.roleId == null) {
        this.$notify.error("部门或用户不得为空")
        return
      }
      let nowString = this.departmentId + ':' + this.roleId;
      if (this.documentDepartmentRoleString.indexOf(nowString) !== -1) {
        this.$notify.error("已存在相同流程，无法再添加")
        return
      }
      this.documentDepartmentRoleString.push(nowString)
      this.documentClassForm.documentProcessCount++
      this.documentDepartmentString.push(this.$store.getters.getDepartmentList.find(item => {
        return item.departmentId === this.departmentId
      }).departmentName)
      this.documentRoleString.push(this.$store.getters.getRoleList.find(item => {
        return item.roleId === this.roleId
      }).roleName)
    },
    deleteDocumentClass() {
      if (this.documentDepartmentRoleString.length === 0) {
        this.$notify.success("已全部删除")
        return
      }
      this.documentDepartmentRoleString.pop()
      this.documentDepartmentString.pop()
      this.documentRoleString.pop()
      this.documentClassForm.documentProcessCount--
    },
    resetDocumentClass() {
      this.documentClassForm = {
        documentClassName: '',
        documentClassDescribe: '',
        documentDepartmentRoleString: null,
        documentProcessCount: 0,
      }
      this.documentDepartmentRoleString=[]
      this.departmentId = null
      this.roleId = null
      this.documentDepartmentString = []
      this.documentRoleString = []
      this.loadDocumentClass()
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
.DocumentClassEdit {
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

  .DocumentClassEditViewbody {
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