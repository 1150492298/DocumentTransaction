<template>
  <div class="DocumentPostAdd">
    <AppIntroHeader HeaderPost="公文发布"></AppIntroHeader>
    <div class="DocumentPostAddViewbody">
      <el-form :model="documentPostForm" :rules="rules" ref="documentPostForm" label-width="auto"
               class="demo-ruleForm mystify_form"
               size="mini"
               :hide-required-asterisk='hide_required' label-position="top">
        <el-form-item label="公文名称" prop="documentName">
          <el-input v-model="documentPostForm.documentName"></el-input>
        </el-form-item>
        <el-form-item label="公文内容" prop="documentAbout">
          <el-input type="textarea" v-model="documentPostForm.documentAbout" rows="8"></el-input>
        </el-form-item>
        <el-form-item label="文件上传">
          <el-upload
              class="upload-demo"
              ref="upload"
              :action="'http://localhost:8082/api/document/uploadFile?token='+this.userCookie.token"
              :limit="1"
              :on-success="handleSuccess"
              :on-exceed="handleExceed"
              :file-list="fileList"
              :auto-upload="false">
            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
            <div slot="tip" class="el-upload__tip">文件大小不得超过1m</div>
          </el-upload>
        </el-form-item>
        <el-form-item label="公文流程" prop="documentClassId">
          <div class="processAdd">
            <el-select v-model="documentPostForm.documentClassId" placeholder="公文类型" class="DocumentSearchInput3">
              <el-option
                  v-for="item in $store.getters.getDocumentClassList"
                  :key="item.documentClassId"
                  :label="item.documentClassName"
                  :value="item.documentClassId"
              >
              </el-option>
            </el-select>
          </div>
        </el-form-item>
        <el-steps direction="vertical" finish-status="success"
                  class="DocumentPostStep">
          <el-step v-for="index of documentClass.documentProcessCount" :title="documentDepartmentString[index-1]"
                   :description="documentRoleString[index-1]" :key="index"></el-step>
        </el-steps>
        <el-form-item>
          <el-button type="primary" @click="submitUpload('documentPostForm')">提交</el-button>
          <el-button @click="resetDocumentPost()">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import AppIntroHeader from "@/components/AppIntroHeader";
import request from "@/utils/request";
import Cookies from "js-cookie";

export default {
  name: "DocumentPostAdd",
  components: {AppIntroHeader},
  data() {
    return {
      documentPostForm: {
        documentName: '',
        documentAbout: '',
        documentClassId: null,
        documentContent: null
      },
      fileList: [],
      userCookie: Cookies.get('user') ? JSON.parse(Cookies.get('user')) : {},
      documentClass: {
        documentProcessCount: 0
      },
      documentDepartmentRoleString: [],
      documentDepartmentString: [],
      documentRoleString: [],
      hide_required: true,
      rules: {
        documentName: [
          {required: true, message: '请输入公文名', trigger: 'blur'},
          {max: 50, message: '长度不得大于50', trigger: 'blur'}
        ],
        documentAbout: [
          {required: true, message: '请填写公文内容', trigger: 'blur'},
          {max: 500, message: '描述字数不得低于5超过200', trigger: 'blur'}
        ],
        documentClassId: [
          {required: true, message: '请选择公文类别', trigger: 'blur'},
        ]
      }
    }
  },
  watch: {
    'documentPostForm.documentClassId': {
      handler() {
        this.documentDepartmentRoleString = []
        this.documentDepartmentString = []
        this.documentRoleString = []
        this.documentClass = this.$store.getters.getDocumentClassList.find(item => {
          return item.documentClassId === this.documentPostForm.documentClassId
        })
        this.documentDepartmentRoleString = this.documentClass.documentDepartmentRoleString.split('-')
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
      }
    },
  },
  methods: {
    // updateDocumentPost() {
    //   request.post('/document/PostDocument', {
    //     documentPostForm: this.documentPostForm,
    //     token: this.userCookie.token
    //   }).then(res => {
    //     if (res.code == '200') {
    //       this.$notify.success('公文已提交，请静待审核')
    //       this.$router.back()
    //     } else {
    //       this.$notify.error(res.msg)
    //     }
    //   })
    // },
    resetDocumentPost() {
      this.documentPostForm = {
        documentName: '',
        documentAbout: '',
        documentClassId: null,
        documentContent: null
      }
      this.documentClass = {
        documentProcessCount: 0
      }
      this.documentDepartmentRoleString = []
      this.documentDepartmentString = []
      this.documentRoleString = []
      this.fileList = []
    },
    submitUpload(documentPostForm) {
      this.$refs[documentPostForm].validate((valid) => {
        if (valid) {
          this.$refs.upload.submit();
        } else {
          this.$notify.error('信息不合规范')
        }
      })
    },
    handleSuccess(res) {
      if (res.code == 200) {
        this.documentPostForm.documentContent = res.data.documentContent
        this.documentPostForm.documentContentName=res.data.documentContentName
        request.post('/document/PostDocument', {
          documentPostForm: this.documentPostForm,
          token: this.userCookie.token
        }).then(res => {
          if (res.code == '200') {
            this.$notify.success('公文已提交，请静待审核')
            this.$router.back()
          } else {
            this.$notify.error(res.msg)
          }
        })
      } else {
        this.$notify.error(res.msg)
      }
    },
    handleExceed(files, fileList) {
      this.$message.warning(`只能选择上传一个文件`);
    },
    async loadDocumentClass() {
      await this.$store.dispatch('getDocumentClassList')
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
  created() {
    this.documentPostForm.documentClassId=1
  }
}
</script>

<style lang="less" scoped>
.DocumentPostAdd {
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

  .DocumentPostAddViewbody {
    height: 90%;

    .processAdd {
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

    .DocumentPostStep {
      margin-bottom: 10%;
      border-top: 1px solid #d8dcbc;
      border-bottom: 1px solid #d8dcbc;
    }
  }
}
</style>